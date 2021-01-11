package me.yh.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private Long id;
    private String writer;
    private String title;
    private LocalDateTime createDate;
}