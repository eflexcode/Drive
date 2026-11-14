package com.ifeanyi.userserivce_drive.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class UpdateUserPayload {

    @JsonProperty("fullname")
    private String fullname;
    @JsonProperty("picture_url")
    private String pictureUrl;

    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("latitude")
    private Integer latitude;
    @JsonProperty("longitude")
    private Integer longitude;
    @JsonProperty("updated_at")
    private Date updatedAt;

}
