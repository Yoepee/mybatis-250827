package com.back.domain.post.post.dto;

import com.back.domain.member.member.dto.Member;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
public class PostWithAuthor {
    private int id;
    private String title;
    private String content;
    private int memberId;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    private Member author;
}
