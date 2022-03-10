package com.bridgelabz.BookStoreBackend.dto;


import com.bridgelabz.BookStoreBackend.model.Cart;
import lombok.Data;

import java.util.List;

@Data
public class CartsResp {

    private String message;
    private List<Cart> data;

    public CartsResp(String message, List<Cart> data) {
        this.message = message;
        this.data = data;
    }
}
