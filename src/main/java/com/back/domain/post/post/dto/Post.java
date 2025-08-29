package com.back.domain.post.post.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor // ★ MyBatis가 이걸로 생성 후 setter로 채움
public class Post {
    private int id;
    private String title;
    private String content;
    private int memberId;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public Post(String title, String content, int memberId) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }
}
