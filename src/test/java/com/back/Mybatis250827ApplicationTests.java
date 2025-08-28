package com.back;

import com.back.domain.post.post.dto.Post;
import com.back.domain.post.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class Mybatis250827ApplicationTests {
    @Autowired
    private PostService postService;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("게시물 다건 조회")
    void t1() {
        List<Post> posts = postService.findAll();

        assertThat(posts).hasSize(2);
    }

    @Test
    @DisplayName("게시물 단건 조회")
    void t2() {
        Post post = postService.findById(1);

        assertThat(post.getTitle()).isEqualTo("제목 1");
        assertThat(post.getContent()).isEqualTo("내용 1");
    }

    @Test
    @DisplayName("게시물 생성")
    void t3() {
        int id =postService.create("제목 3", "내용 3");

        Post post = postService.findById(id);

        assertThat(post.getTitle()).isEqualTo("제목 3");
        assertThat(post.getContent()).isEqualTo("내용 3");
    }

    @Test
    @DisplayName("게시물 생성 V2")
    void t4() {
        postService.create("제목 3", "내용 3");
        int id = postService.getLastInsertId();

        Post post = postService.findById(id);

        assertThat(post.getTitle()).isEqualTo("제목 3");
        assertThat(post.getContent()).isEqualTo("내용 3");
    }

    @Test
    @DisplayName("게시물 삭제")
    void t5() {
        postService.deleteById(1);
        List<Post> posts = postService.findAll();

        assertThat(posts).hasSize(1);
    }

    @Test
    @DisplayName("게시물 수정")
    void t6() {
        Post post = postService.findById(1);
        assertThat(post).isNotNull();

        postService.update(1, "제목 3", "내용 3");
        Post updatedPost = postService.findById(1);

        assertThat(updatedPost.getTitle()).isEqualTo("제목 3");
        assertThat(updatedPost.getContent()).isEqualTo("내용 3");
    }

    @Test
    @DisplayName("제목검색")
    void t7() {
        List<Post> posts = postService.findAll();
        assertThat(posts).hasSize(2);

        List<Post> posts1 = postService.search("title", "제목 1");
        assertThat(posts1).hasSize(1);

        List<Post> posts2 = postService.search("title", "제목");
        assertThat(posts2).hasSize(2);
    }

    @Test
    @DisplayName("내용검색")
    void t8() {
        List<Post> posts = postService.findAll();
        assertThat(posts).hasSize(2);

        List<Post> posts1 = postService.search("content", "내용 1");
        assertThat(posts1).hasSize(1);

        List<Post> posts2 = postService.search("content", "내용");
        assertThat(posts2).hasSize(2);
    }

    @Test
    @DisplayName("내용검색")
    void t9() {
        List<Post> posts = postService.findAll();
        assertThat(posts).hasSize(2);

        List<Post> posts1 = postService.search("", "내용 1");
        assertThat(posts1).hasSize(1);

        List<Post> posts2 = postService.search("", "제목 1");
        assertThat(posts2).hasSize(1);
    }

    @Test
    @DisplayName("정렬된 게시물 조회 - 제목 오름차순")
    void t10() {
        List<Post> posts = postService.findAll("title", "asc");
        assertThat(posts).hasSize(2);
        assertThat(posts.get(0).getTitle()).isEqualTo("제목 1");
        assertThat(posts.get(1).getTitle()).isEqualTo("제목 2");
    }

    @Test
    @DisplayName("정렬된 게시물 조회 - 제목 내림차순")
    void t11() {
        List<Post> posts = postService.findAll("title", "desc");
        assertThat(posts).hasSize(2);
        assertThat(posts.get(0).getTitle()).isEqualTo("제목 2");
        assertThat(posts.get(1).getTitle()).isEqualTo("제목 1");
    }

    @Test
    @DisplayName("정렬된 게시물 조회 - 생성일 내림차순")
    void t12() {
        postService.create("제목 3", "내용 3");
        List<Post> posts = postService.findAll("createDate", "desc");
        assertThat(posts).hasSize(3);
        assertThat(posts.get(0).getTitle()).isEqualTo("제목 3");
        assertThat(posts.get(1).getTitle()).isEqualTo("제목 2");
        assertThat(posts.get(2).getTitle()).isEqualTo("제목 1");
    }

    @Test
    @DisplayName("게시물 특정 항목 수정")
    void t13() {
        Post post = postService.findById(1);
        assertThat(post).isNotNull();

        postService.update(1, "", "내용 3");
        Post updatedPost = postService.findById(1);

        assertThat(updatedPost.getTitle()).isEqualTo("제목 1");
        assertThat(updatedPost.getContent()).isEqualTo("내용 3");
    }

    @Test
    @DisplayName("다중 게시물 삭제")
    void t14() {
        // 추가 게시물 생성
        int id3 = postService.create("제목 3", "내용 3");
        int id4 = postService.create("제목 4", "내용 4");

        // 다중 삭제
        int deletedCount = postService.deleteByIds(Arrays.asList(id3, id4));
        assertThat(deletedCount).isEqualTo(2);

        // 삭제 확인
        List<Post> posts = postService.findAll();
        assertThat(posts).hasSize(2); // 기존 2개만 남음
    }
}
