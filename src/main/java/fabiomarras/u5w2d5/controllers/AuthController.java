package fabiomarras.u5w2d5.controllers;

import fabiomarras.u5w2d5.entities.User;
import fabiomarras.u5w2d5.exceptions.BadRequestException;
import fabiomarras.u5w2d5.payloads.NewUserRequestDTO;
import fabiomarras.u5w2d5.payloads.UserLoginDTO;
import fabiomarras.u5w2d5.payloads.UserLoginSuccessDTO;
import fabiomarras.u5w2d5.services.AuthService;
import fabiomarras.u5w2d5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/autho")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public UserLoginSuccessDTO login(@RequestBody UserLoginDTO body){

        return new UserLoginSuccessDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    public User newUser(@RequestBody @Validated NewUserRequestDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return userService.save(body);
        }
    }
}
