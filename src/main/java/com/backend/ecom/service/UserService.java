package com.backend.ecom.service;

import com.backend.ecom.Exception.AuthenticationFailException;
import com.backend.ecom.Exception.CustomException;
import com.backend.ecom.dto.ResponeDto;
import com.backend.ecom.dto.SignInDto;
import com.backend.ecom.dto.SignInResponseDto;
import com.backend.ecom.dto.SignUpDto;
import com.backend.ecom.model.AuthenticaitonToken;
import com.backend.ecom.model.User;
import com.backend.ecom.repository.UserRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    @Autowired
    AuthenticationService authenticationService;
    public ResponeDto signup(SignUpDto signUpDto) {
        // Implement signup logic here and return appropriate response in ResoneDto formate
        // first check the user already exists or not
        // first save the user in the data base
        if( Objects.nonNull(userRepo.findByEmail(signUpDto.getEmail()))){
            throw new CustomException("user already exists");
        }
        // hash the password
        String encryptedPassword = signUpDto.getPassword();
        try{
            encryptedPassword = hashPassword(signUpDto.getPassword());
        }catch(NoSuchAlgorithmException e){
            throw new CustomException(e.getMessage());
        }

        //create that exact user and make that password as encypted passsswod
        User user = new User(signUpDto.getFirstName(),signUpDto.getLastName(),signUpDto.getEmail(),encryptedPassword);
        userRepo.save(user);
        // create a token

        return new ResponeDto("ok","dummy response");

    }

    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }


    public SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationFailException, CustomException {

        User user = userRepo.findByEmail(signInDto.getEmail());
        if(!Objects.nonNull(user)){
            throw new AuthenticationFailException("user not present");
        }
        try {
            // check if password is right
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                // passwords do not match
                throw  new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new CustomException(e.getMessage());
        }

        AuthenticaitonToken token = authenticationService.getToken(user);

        if(!Objects.nonNull(token)) {
            // token not present
            throw new CustomException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }

        return new SignInResponseDto ("success", token.getToken());
    }
}
