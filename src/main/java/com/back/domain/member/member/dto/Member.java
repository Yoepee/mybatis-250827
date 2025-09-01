package com.back.domain.member.member.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
public class Member {
    private int id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String username;
    private String password;
    private String name;
    private String email;

    public Member(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public boolean matchPassword(String password) {
        return this.password.substring("{noop}".length()).equals(password);
    }
}
