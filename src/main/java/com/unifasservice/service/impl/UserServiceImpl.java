package com.unifasservice.service.impl;

import com.unifasservice.converter.UserDetailConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.*;
import com.unifasservice.dto.payload.response.CodePassResponse;
import com.unifasservice.dto.payload.response.DataMailResponse;
import com.unifasservice.dto.payload.response.UserRegisterResponse;
import com.unifasservice.dto.payload.response.UserDetailResponse;
import com.unifasservice.repository.CartRepository;
import com.unifasservice.security.JwtTokenUtil;
import com.unifasservice.converter.UserLoginConverter;
import com.unifasservice.dto.payload.response.UserLoginResponse;
import com.unifasservice.entity.Cart;
import com.unifasservice.entity.User;
import com.unifasservice.repository.UserRepository;
import com.unifasservice.converter.UserRegisterConverter;
import com.unifasservice.service.MailService;
import com.unifasservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.MessagingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.unifasservice.utilities.RandomStringGenerator.generateRandomString;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final UserLoginConverter userLoginConverter;
    private final UserRegisterConverter userRegisterConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final MailService mailService;
    private final UserDetailConverter userDetailConverter;


    public CommonResponse login(UserLoginRequest login) {
        try {
            String email = login.getEmail();
            String password = login.getPassword();



        if (email == null || password == null) {
            throw new IllegalArgumentException("Username and password are required.");
        }

            User user = userRepository.findByEmail(email);

            if (user != null) {
                if (passwordEncoder.matches(password, user.getPassword())) {
                    UserLoginResponse loginResponse = userLoginConverter.userToUserLoginDTO(user);
                    String token = jwtTokenUtil.generateToken(user);
                    loginResponse.setToken(token);
                    loginResponse.setRole(user.getRole());

                    return getCommonResponse("Logged in Successfully !", HttpStatus.OK, loginResponse);
                } else {
                    throw new RuntimeException("Wrong password");
                }
            } else {
                throw new RuntimeException("User not found");
            }
        } catch (IllegalArgumentException e) {
            return getCommonResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        } catch (RuntimeException e) {
            return getCommonResponse(e.getMessage(), HttpStatus.UNAUTHORIZED, null);
        }
    }



    @Override
    public CommonResponse register(UserRegisterRequest userRegisterRequest) {

        try {
            String password = userRegisterRequest.getPassword();
            String hashedPassword = passwordEncoder.encode(password);


            User userNameCheck = userRepository.findByUsername(userRegisterRequest.getUsername());
            if (userNameCheck != null) {
                return getCommonResponse("This account already exists", HttpStatus.BAD_REQUEST, null);
            }

            User emailCheck = userRepository.findByEmail(userRegisterRequest.getEmail());
            if (emailCheck != null) {
                return getCommonResponse("Email exists, please enter another email!", HttpStatus.BAD_REQUEST, null);
            }


            User user = userRegisterConverter.convertDTORequestToEntity(userRegisterRequest);
            user.setPassword(hashedPassword);

            user.setDeleted(false);

            Cart cartUser = new Cart();
            cartUser.setUser(user);
            user.setCart(cartUser);
            User userEntity = userRepository.save(user);

            cartUser.setUser(userEntity);
            cartRepository.save(cartUser);

            UserRegisterResponse responseDTO = userRegisterConverter.convertEntityResponseToDTO(user);

            return getCommonResponse("Register Success!", HttpStatus.CREATED, responseDTO);

        } catch (Exception e) {
            e.printStackTrace();

            return getCommonResponse("Register Failure!", HttpStatus.BAD_REQUEST, null);
        }
    }


    private CommonResponse getCommonResponse(String message, HttpStatus status, Object data) {
        CommonResponse commonResponse = CommonResponse.builder()
                .message(message)
                .statusCode(status)
                .data(data)
                .build();
        return commonResponse;

    }

    @Override
    public CodePassResponse createCodePass(ForgetPassRequest forgetPassRequest) {
        User emailCheck = userRepository.findByEmail(forgetPassRequest.getEmail());
        if (emailCheck != null) {
            String randomString = generateRandomString(6);
            CodePassResponse responseDto = new CodePassResponse();
            responseDto.setCodePass(randomString);
            responseDto.setEmail(emailCheck.getEmail());
            try {
                DataMailResponse dataMail = new DataMailResponse();
                dataMail.setTo(forgetPassRequest.getEmail());
                dataMail.setSubject("Yêu cầu đặt lại mật khẩu");
                Map<String, Object> props = new HashMap<>();
                props.put("codePass", randomString);
                dataMail.setProps(props);
                mailService.sendHtmlMail(dataMail, "forgot-password");
            } catch (MessagingException exp){
                exp.printStackTrace();
            } catch (javax.mail.MessagingException e) {
                throw new RuntimeException(e);
            }
            return responseDto;
        }
        return null;
    }

    @Override
    public boolean changePass(ChangePassRequest changePassRequest) {
        User emailCheck = userRepository.findByEmail(changePassRequest.getEmail());
        if (emailCheck != null) {
            String hashCode = passwordEncoder.encode(changePassRequest.getNewPass());
            emailCheck.setPassword(hashCode);
            userRepository.save(emailCheck);
            return true;
        }
        return false;
    }

    @Override
    public UserDetailResponse getUserByUsername(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("Username not found");
        }
        return userDetailConverter.toDto(user);
    }

    @Override
    public UserDetailResponse updateUserByUsername(String username, UserDetailRequest userDetailRequest) throws Exception {
        User currentUser = userRepository.findByUsername(username);
        if (currentUser == null) {
            throw new Exception("Username not found");
        }
        currentUser.setDateOfBirth(userDetailRequest.getDateOfBirth());
        currentUser.setFullName(userDetailRequest.getFullName());
        User user = userRepository.save(currentUser);
        UserDetailResponse response = userDetailConverter.toDto(user);
        return response;
    }

    @Override
    public UserDetailResponse changePasswordByUsername(String username,
                                                       UserPasswordRequest userPasswordRequest) throws Exception {
        User currentUser = userRepository.findByUsername(username);
        if (currentUser == null) {
            throw new Exception("Không tìm thấy tên tài khoản");
        }

        if (!passwordEncoder.matches(userPasswordRequest.getCurrentPassword(), currentUser.getPassword())) {
            throw new Exception("Mật khẩu hiện tại không đúng");
        }
        if (passwordEncoder.matches(userPasswordRequest.getNewPassword(), currentUser.getPassword())) {
            throw new Exception("Mật khẩu mới phải khác mật khẩu hiện tại");
        }
        if (!userPasswordRequest.getNewPassword().equals(userPasswordRequest.getConfirmationPassword())) {
            throw new Exception("Mật khẩu xác thực không đúng");
        }
        currentUser.setPassword(passwordEncoder.encode(userPasswordRequest.getNewPassword()));

        User user = userRepository.save(currentUser);
        UserDetailResponse response = userDetailConverter.toDto(user);
        return response;
    }

}
