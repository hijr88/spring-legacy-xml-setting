package me.yh.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Member {
    private Long no;
    private String id;
    private String name;
    private String password;
    private LocalDateTime date;
    private List<Post> posts;
}