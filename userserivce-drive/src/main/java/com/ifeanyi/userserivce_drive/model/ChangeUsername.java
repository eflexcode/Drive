package com.ifeanyi.userserivce_drive.model;

import lombok.Data;

@Data
public class ChangeUsername {

    private String oldUsername;
    private String newUsername;
    private String password;

}
