package com.ifeanyi.userserivce_drive.service;

import com.ifeanyi.userserivce_drive.entity.User;
import com.ifeanyi.userserivce_drive.model.*;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    StandardResponse createUser(CreateUserPayload userPayload);
    User getUser(Integer id);
    User getUserByUsername(String username);
    User updateUser(UpdateUserPayload updateUserPayload);
    StandardResponse changePassword(ChangePassword changePassword);
    StandardResponse changeUsername(ChangeUsername changeUsername);
    User updateProfilePic(MultipartFile file,Integer userId);
    byte[] loadProfilePic(String filename);
    void deleteUser(Integer   id);

}
