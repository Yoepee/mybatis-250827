package com.back.domain.post.post.dto;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
public class Post {
    private int id;
    private String title;
    private String content;
    private int memberId;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
