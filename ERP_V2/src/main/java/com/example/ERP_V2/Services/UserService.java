package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    List<User> getAllUsers();
    void updateUser(int id, User updatedUser);
    void insertDummyData();

}
