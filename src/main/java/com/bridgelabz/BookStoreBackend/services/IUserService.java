package com.bridgelabz.BookStoreBackend.services;

import com.bridgelabz.BookStoreBackend.dto.UserDTO;
import com.bridgelabz.BookStoreBackend.dto.VerifyUser;
import com.bridgelabz.BookStoreBackend.model.User;
import com.bridgelabz.BookStoreBackend.util.Response;

import java.util.List;
import java.util.Optional;

public interface IUserService {


    List<User> getUsers();

    List<User> findByName(String name);

    Optional<User> getUserByEmailId(String emailId);

    Optional<User> getUserById(Long userId);

    List<User>sortByName(String name);

    Response addUser(UserDTO userDTO);

    Response loginUser(String email, String password);

    Response forgotPassword(String token, String psw);

    Response verifyUser(VerifyUser User);

    Response updateUser(String token, UserDTO userDTO);

    void deleteUser(String token);
}
