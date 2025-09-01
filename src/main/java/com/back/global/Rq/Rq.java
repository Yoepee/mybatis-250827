package com.back.global.Rq;

import com.back.domain.member.member.dto.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final HttpSession session;

    public String getCurrentUrl() {
        String url = request.getRequestURL().toString();
        String queryString = request.getQueryString();

        if (queryString != null && !queryString.isEmpty()) {
            url += "?" + queryString;
        }

        return url;
    }

    public boolean isLogined(){
        return getLoginedMemberId() > 0;
    }

    public boolean isLogout() {
        return !isLogined();
    }

    public int getLoginedMemberId(){
        Integer loginedMemberId = (Integer) session.getAttribute("loginedMemberId");

        if (loginedMemberId == null) return 0;

        return loginedMemberId;
    }

    public String getLoginedMemberUsername(){
        String loginedMemberUsername = (String) session.getAttribute("loginedMemberUsername");
        return loginedMemberUsername;
    }
    public String getLoginedMemberName(){
        String loginedMemberName = (String) session.getAttribute("loginedMemberName");
        return loginedMemberName;
    }
    public String getLoginedMemberEmail(){
        String loginedMemberEmail = (String) session.getAttribute("loginedMemberEmail");
        return loginedMemberEmail;
    }
    public Member getLoginedMember(){
        int id = getLoginedMemberId();
        String usename = getLoginedMemberUsername();
        String name = getLoginedMemberName();
        String email = getLoginedMemberEmail();

        return Member.builder()
                .id(id)
                .username(usename)
                .name(name)
                .email(email)
                .build();
    }

    public void setLoginDone(Member member) {
        session.setAttribute("loginedMemberId", member.getId());
        session.setAttribute("loginedMemberUsername", member.getUsername());
        session.setAttribute("loginedMemberName", member.getName());
        session.setAttribute("loginedMemberEmail", member.getEmail());
    }

    public void setLogoutDone() {
        session.removeAttribute("loginedMemberId");
        session.removeAttribute("loginedMemberUsername");
        session.removeAttribute("loginedMemberName");
        session.removeAttribute("loginedMemberEmail");
    }
}
