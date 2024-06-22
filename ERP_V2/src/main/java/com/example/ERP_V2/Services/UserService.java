package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.UserDTO;
import com.example.ERP_V2.Model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    List<UserDTO> getAllUsers(Integer pageNumber, Integer pageSize);
    void updateUser(String id, User updatedUser);
    void insertDummyData();

    void deactivateUser(String id);

    void reactivateUser(String id);
}
