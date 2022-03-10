package com.bridgelabz.BookStoreBackend.repo;

import com.bridgelabz.BookStoreBackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT * FROM cart_service where user_id = :user_id", nativeQuery = true)
    List<Cart> findAllCartsByUserId(Long user_id);
}
