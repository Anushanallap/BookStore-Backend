package com.bridgelabz.BookStoreBackend.repo;

import com.bridgelabz.BookStoreBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {

    @Query(value = "SELECT * FROM user_registration where email_id=:email_Id", nativeQuery = true)
    public Optional<User> findByEmailid(String email_Id);
}
