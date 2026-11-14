package com.ifeanyi.userserivce_drive.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateUserPayload {
    private String username;
    @JsonProperty("password")
    @JsonIgnore
    private String password;
    @JsonProperty("fullname")
    private String fullname;
    @JsonProperty("phone_number")
    private String phoneNumber;
}
