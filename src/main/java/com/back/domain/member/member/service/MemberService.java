package com.back.domain.member.member.service;

import com.back.domain.member.member.dto.Member;
import com.back.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(int id) {
        return memberRepository.findById(id);
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public int create(String username, String password, String name, String email) {
        if (findByUsername(username) != null) return -1;
        Member member = new Member(username, password, name, email);
        memberRepository.create(member);
        return member.getId();
    }

    public void deleteById(int id) {
        memberRepository.deleteById(id);
    }

    public void update(int id, String username, String password, String name, String email) {
        Member member = findById(id);
        member.setUsername(username);
        member.setPassword(password);
        member.setName(name);
        member.setEmail(email);
        int updatedRows = memberRepository.update(member);
        if (updatedRows != 1){
            throw new IllegalStateException("게시물 수정 실패");
        }
    }

    public List<Member> search(String type, String keyword) {
        return memberRepository.search(type, keyword);
    }

    public int deleteByIds(List<Integer> ids) {
        return memberRepository.deleteByIds(ids);
    }
}
