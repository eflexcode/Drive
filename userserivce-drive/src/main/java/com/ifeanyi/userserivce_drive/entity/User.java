package com.ifeanyi.userserivce_drive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "users")
public class User {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    @JsonIgnore
    private String password;
    @JsonProperty("fullname")
    private String fullname;
    @JsonProperty("picture_url")
    private String pictureUrl;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("latitude")
    private Long latitude;
    @JsonProperty("longitude")
    private Long longitude;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("updated_at")
    private Date updatedAt;

}
