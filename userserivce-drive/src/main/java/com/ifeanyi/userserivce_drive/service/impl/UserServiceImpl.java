package com.ifeanyi.userserivce_drive.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ifeanyi.userserivce_drive.entity.User;
import com.ifeanyi.userserivce_drive.model.*;
import com.ifeanyi.userserivce_drive.repository.UserRepository;
import com.ifeanyi.userserivce_drive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public StandardResponse createUser(CreateUserPayload userPayload) {

        if (userPayload.getFullname().trim().isEmpty() || userPayload.getUsername().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username and fullname cannot be empty string");
        }

        User user = new User();
        BeanUtils.copyProperties(userPayload, user);
        Date date = new Date();
        BCrypt.withDefaults().hashToString(12, userPayload.getPassword().toCharArray());
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        userRepository.save(user);
        return new StandardResponse("user created", HttpStatus.CREATED.value(), date);
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no user found with id: " + id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no user found with username: " + username));

    }

    @Override
    public User updateUser(UpdateUserPayload updateUserPayload) {
        User user = new User();
        BeanUtils.copyProperties(updateUserPayload, user);
        Date date = new Date();
        user.setUpdatedAt(date);

        return userRepository.save(user);
    }

    @Override
    public StandardResponse changePassword(ChangePassword changePassword) {
        User user = getUserByUsername(changePassword.getUsername());
        BCrypt.Result result = BCrypt.verifyer().verify(changePassword.getOldPassword().toCharArray(), user.getPassword());
        if (!result.verified) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid password");
        }

        user.setPassword(changePassword.getNewPassword());
        userRepository.save(user);
        return new StandardResponse("username changed", HttpStatus.OK.value(), new Date());
    }

    @Override
    public StandardResponse changeUsername(ChangeUsername changeUsername) {
        if (changeUsername.getNewUsername().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username cannot be empty string");
        }
        User user = getUserByUsername(changeUsername.getOldUsername());
        BCrypt.Result result = BCrypt.verifyer().verify(changeUsername.getPassword().toCharArray(), user.getPassword());
        if (!result.verified) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid password");
        }

        user.setUsername(changeUsername.getNewUsername());
        userRepository.save(user);
        return new StandardResponse("username changed", HttpStatus.OK.value(), new Date());
    }

    @Override
    public User updateProfilePic(MultipartFile multipartFile,Integer userId) {
        String filename = System.currentTimeMillis() + ".png";
        String filePath = "/home/ifeanyi/Desktop/" + filename;

        try {
//            FileWriter fileWriter = new FileWriter(file, true);
            InputStream inputStream = multipartFile.getInputStream();
            Path destinationPath = Paths.get(filePath);

            Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User user = getUser(userId);
        user.setPictureUrl("http://localhost:8080/profile-picture/"+filename);
        return userRepository.save(user);
    }

    @Override
    public byte[] loadProfilePic(String filename) {
        String filePath = "/home/ifeanyi/Desktop/" + filename;
        try {
            Path path = Paths.get(filePath).normalize();

            if (!Files.exists(path) || !Files.isRegularFile(path)) {
                throw new RuntimeException("Profile picture not found");
            }

            return Files.readAllBytes(path);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load profile picture", e);
        }

    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.delete(getUser(id));
    }

}
