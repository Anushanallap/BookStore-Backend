package com.bridgelabz.BookStoreBackend.dto;


import lombok.Data;

@Data
public class CartServiceDTO {

    public String user_id;

    public Long book_id;

    public Long quantity;
}
