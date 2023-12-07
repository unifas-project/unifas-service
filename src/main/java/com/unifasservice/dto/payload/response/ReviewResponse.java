package com.unifasservice.dto.payload.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReviewResponse {

    private long id;

    private String content;

    private LocalDateTime time;

    private int star;

    private UserResponse user;
}
