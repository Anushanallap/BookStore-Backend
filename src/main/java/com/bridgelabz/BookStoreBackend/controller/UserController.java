package com.bridgelabz.BookStoreBackend.controller;


import com.bridgelabz.BookStoreBackend.dto.ResponseDTO;
import com.bridgelabz.BookStoreBackend.dto.UserDTO;
import com.bridgelabz.BookStoreBackend.dto.VerifyUser;
import com.bridgelabz.BookStoreBackend.services.MyEmailService;
import com.bridgelabz.BookStoreBackend.services.UserService;
import com.bridgelabz.BookStoreBackend.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MyEmailService emailService;

    @GetMapping(value = {"", "/"})
    ResponseEntity<ResponseDTO> getUsers(){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Get Call Success",
                userService.getUsers()), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDTO> getUserbyId(@PathVariable("userId") Long userId) {
        return new ResponseEntity<ResponseDTO>( new
                ResponseDTO("Get Call By Id Success",
                userService.getUserById(userId)), HttpStatus.OK);

    }

    @PostMapping("/adduser")
    ResponseEntity<Response> addUser(@RequestBody UserDTO userDTO){
        Response response = userService.addUser(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> userLogin(@RequestParam(name = "email") String email_id, @RequestParam String psw) {
        Response resp = userService.loginUser(email_id, psw);
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

//    @PostMapping("/forgotpsw")
//    public ResponseEntity<Response> forgotPassword(@RequestParam(name = "token") String token, @RequestParam String psw) {
//        Response resp = userService.forgotPassword(token, psw);
//        return new ResponseEntity<Response>(resp, HttpStatus.OK);
//    }

//    @PostMapping("/verifyuser")
//    ResponseEntity<Response> verifyUser(@RequestBody VerifyUser verifyUser){
//        Response response = userService.verifyUser(verifyUser);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @PostMapping("send")
//    public String sendEmail(HttpServletRequest req) {
//        emailService.sendOTPMessage("anusha.n468@gmail.com", "Email testing", "you registered successfully");
//        return "hello";
//    }

    @PutMapping("/updateuser/{token}")
    ResponseEntity<Response> updateUser(@PathVariable("token") String token,
                                        @RequestBody UserDTO userDTO){
        Response resp = userService.updateUser(token, userDTO);
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{token}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("token") String token) {
        userService.deleteUser(token);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("deleted adressBook data with personId :", token),
                HttpStatus.OK);
    }
}
