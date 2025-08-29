package com.back.domain.post.post.service;

import com.back.domain.post.post.dto.Post;
import com.back.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll("","");
    }

    public List<Post> findAll(String orderBy, String direction) {
        return postRepository.findAll(orderBy, direction);
    }

    public Post findById(int id) {
        return postRepository.findById(id);
    }

    public int create(String title, String content, int memberId) {
        Post post = new Post(title, content, memberId);
        postRepository.create(post);
        return post.getId();
    }

    public int getLastInsertId() {
        return postRepository.getLastInsertId();
    }

    public int update(int id, String title, String content) {
        Post post = findById(id);
        post.setTitle(title);
        post.setContent(content);
        int updatedRows = postRepository.update(post);
        if (updatedRows != 1){
            throw new IllegalStateException("게시물 수정 실패");
        }
        return updatedRows;
    }

    public void deleteById(int id) {
        postRepository.deleteById(id);
    }

    public List<Post> search(String type, String keyword) {
        return postRepository.findByType(type, keyword);
    }

    public int deleteByIds(List<Integer> ids) {
        return postRepository.deleteByIds(ids);
    }

    public Post findByIdWithAuthorName(int id) {
        return postRepository.findByIdWithAuthorName(id);
    }
}
