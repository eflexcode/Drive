package com.ifeanyi.userserivce_drive.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponse {

    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("time_stamp")
    private Date timeStamp;

}
