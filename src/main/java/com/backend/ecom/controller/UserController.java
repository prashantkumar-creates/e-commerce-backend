package com.backend.ecom.controller;


import com.backend.ecom.Exception.AuthenticationFailException;
import com.backend.ecom.Exception.CustomException;
import com.backend.ecom.dto.ResponeDto;
import com.backend.ecom.dto.SignInDto;
import com.backend.ecom.dto.SignInResponseDto;
import com.backend.ecom.dto.SignUpDto;
import com.backend.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;
    // User related API endpoints go here

    @PostMapping("/signup")
    public ResponeDto signup(@RequestBody SignUpDto signUpDto){
        // Implement signup logic here
        return userService.signup(signUpDto);
    }

    @PostMapping("/signIn")
    public SignInResponseDto  signIn(@RequestBody SignInDto signInDto) throws CustomException, AuthenticationFailException {
        return userService.signIn(signInDto);
    }
}