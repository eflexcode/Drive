package com.ifeanyi.userserivce_drive.controller;

import com.ifeanyi.userserivce_drive.entity.User;
import com.ifeanyi.userserivce_drive.model.*;
import com.ifeanyi.userserivce_drive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserRestController {

    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public StandardResponse createUser(@RequestBody CreateUserPayload userPayload) {
        return userService.createUser(userPayload);
    }

    @GetMapping("get-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable(name = "id") Integer id) {
        return userService.getUser(id);
    }

    @GetMapping("/get-by-username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsername(@PathVariable(name = "username") String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody UpdateUserPayload updateUserPayload) {
        return userService.updateUser(updateUserPayload);
    }

    @PutMapping("/change-password")
    @ResponseStatus(HttpStatus.OK)
    public StandardResponse changePassword(@RequestBody ChangePassword changePassword) {
        return userService.changePassword(changePassword);
    }

    @PostMapping("/upload-profile-picture")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("id") Integer id) {
        return new ResponseEntity<>(userService.updateProfilePic(file, id), HttpStatus.OK);
    }

    @GetMapping("/profile-picture/{filename}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> loadProfilePic(
            @PathVariable String filename) {

        byte[] image = userService.loadProfilePic(filename);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

    @DeleteMapping("/delete-by-id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping("/change-username")
    @ResponseStatus(HttpStatus.OK)
    public StandardResponse changeUsername(@RequestBody ChangeUsername changeUsername) {
        return userService.changeUsername(changeUsername);
    }

}
