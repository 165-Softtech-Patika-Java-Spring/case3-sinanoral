package com.patika.model.responseDto;

import lombok.Data;

@Data
public class CommentDto {
    private String context;
    private Long productId;
    private Long userId;
}
