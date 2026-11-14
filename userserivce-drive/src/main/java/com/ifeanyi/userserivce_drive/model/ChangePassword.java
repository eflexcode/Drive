package com.ifeanyi.userserivce_drive.model;

import lombok.Data;

@Data
public class ChangePassword {
    private String username;
    private String oldPassword;
    private String newPassword;
}
