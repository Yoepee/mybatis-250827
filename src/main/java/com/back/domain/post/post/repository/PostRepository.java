package com.back.domain.post.post.repository;

import com.back.domain.post.post.dto.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostRepository {
    List<Post> findAll(@Param("orderBy") String orderBy, @Param("direction") String direction);

    Post findById(int id);

    int create(Post post);

    int getLastInsertId();

    int update(Post post);

    int deleteById(int id);

    List<Post> findByType(@Param("type") String type, @Param("keyword") String keyword);

    int deleteByIds(List<Integer> ids);
}
