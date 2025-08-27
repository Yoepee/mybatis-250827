package com.back.domain.post.post.dto;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class Post {
    private int id;
    private String title;
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
