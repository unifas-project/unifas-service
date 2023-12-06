package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.ReviewResponse;
import com.unifasservice.entity.Review;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewConverter {
    UserConverter userConverter;
    public ReviewResponse reviewToReviewResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .time(review.getTime())
                .star(review.getStar())
                .user(userConverter.userToUserResponse(review.getUser()))
                .build();
    }
    public List<ReviewResponse> listReviewToListReviewResponse(List<Review> reviews) {
        List<ReviewResponse> list = new ArrayList<>();
        for (Review review : reviews) {
            list.add(reviewToReviewResponse(review));
        }
        return list;
    }
}
