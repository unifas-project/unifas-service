package com.unifasservice.controller;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/colors")
@CrossOrigin("*")
public class ColorController {

    private final ColorService colorService;
    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllColors() {
        CommonResponse colors = colorService.findAllColors();
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    @GetMapping("/colors")
    public ResponseEntity<CommonResponse> getAllColor (){
        return colorService.getAllColor();
    }

}
