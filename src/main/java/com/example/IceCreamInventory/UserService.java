package com.example.IceCreamInventory;


import com.example.IceCreamInventory.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
