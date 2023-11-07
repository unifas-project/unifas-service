package com.unifas.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @NotBlank(message = "Username is required")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Pattern(regexp = "^[0-9]*$", message = "Phone number should only contain digits")
    @Size(min = 10, max = 15, message = "Phone number length should be between 10 and 15 digits")
    @Column(name="PHONE_NUMBER")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address should not exceed 255 characters")
    @Column(name="ADDRESS")
    private String address;


    @Column(name = "ROLE_USER")
    private String role;

    @Column(name="IS_DELETED")
    private boolean isDeleted;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Address> addressList ;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviews ;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orderList ;


}
