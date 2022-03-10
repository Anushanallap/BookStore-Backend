package com.bridgelabz.BookStoreBackend.controller;


import com.bridgelabz.BookStoreBackend.dto.CartServiceDTO;
import com.bridgelabz.BookStoreBackend.dto.ResponseDTO;
import com.bridgelabz.BookStoreBackend.model.Cart;
import com.bridgelabz.BookStoreBackend.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @Autowired
    ICartService cartService;

    @PostMapping("/add")
    ResponseEntity<ResponseDTO> addToCart(@RequestBody CartServiceDTO cartDTO){

        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Post call success",
                cartService.addCart(cartDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{cartId}")
    ResponseEntity<ResponseDTO> removeFromCart(@PathVariable("cartId") Long cartId){
        cartService.removeFromCart(cartId);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Delete call success",""), HttpStatus.OK);
    }

    @DeleteMapping("/removeall/{token}")
    ResponseEntity<ResponseDTO> removeFromCart(@PathVariable("token") String token){
        cartService.removeAllCarts(token);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Delete call success",""), HttpStatus.OK);
    }



    @PutMapping("/update/{token}")
    ResponseEntity<ResponseDTO> updateCart(@PathVariable("token") String token, @RequestParam("cart_id")Long cartId,
                                           @RequestParam("quantity") Long quantity){
        cartService.updateQuantity(token, cartId, quantity);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Post call success",
                ""), HttpStatus.OK);
    }

    @GetMapping("get/{token}")
    List<Cart> findAllCartsByUser(@PathVariable("token") String token){
        List<Cart> carts = cartService.findAllCarts(token);
        return carts;
    }

    @GetMapping("getbooklist/{token}")
    List<Long> findAllCartBookIds(@PathVariable("token") String token){
        List<Long> bookIdList = new ArrayList<>();

        List<Cart> carts = cartService.findAllCarts(token);
        bookIdList = carts.stream().filter(i -> i.getBook().getBook_id() != null).map(pm -> pm.getBook().getBook_id()).collect(Collectors.toList());

        return bookIdList;
    }
}
