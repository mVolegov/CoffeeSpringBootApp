package com.mvoleg.coffeespringbootapp.dto;

import java.util.Map;

public class UserOrderDTO {

    private String username;
    private Map<Long, Integer> cart;

    public UserOrderDTO() {
    }

    public UserOrderDTO(String username, Map<Long, Integer> cart) {
        this.username = username;
        this.cart = cart;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<Long, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Long, Integer> cart) {
        this.cart = cart;
    }
}
