package com.back.domain.member.member.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Getter
@Builder
@ToString
public class Member {
    private int id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String username;
    private String password;
    private String name;
    private String email;

    public boolean matchPassword(String password) {
        return this.password.substring("{noop}".length()).equals(password);
    }
}
