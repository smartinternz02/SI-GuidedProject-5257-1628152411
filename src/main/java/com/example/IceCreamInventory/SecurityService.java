package com.example.IceCreamInventory;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
