package com.back.domain.post.post.repository;

import com.back.domain.post.post.dto.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostRepository {
    @Select("""
            <script>
            SELECT * FROM post
            </script>
            """)
    List<Post> findAll();

    @Select("""
            <script>
            SELECT * FROM post
            WHERE id = #{id}
            </script>
            """)
    Post findById(int id);

    @Insert("""
            <script>
            INSERT INTO post(createDate, modifyDate, title, content)
            VALUES (NOW(), NOW(), #{title}, #{content})
            </script>
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(Post post);

    @Select("""
                SELECT LAST_INSERT_ID();
            """)
    int getLastInsertId();

    @Update("""
            <script>
            UPDATE post
            SET title = #{title},
                content = #{content},
                modifyDate = NOW()
            WHERE id = #{id}
            </script>
            """)
    int update(Post post);

    @Delete("""
                <script>
                DELETE FROM post
                WHERE id = #{id}
                </script>
            """)
    int deleteById(int id);

    @Select("""
        <script>
                SELECT * FROM post
                WHERE ${type} LIKE '%${keyword}%'
        </script>
    """)
    List<Post> findByType(@Param("type")String type,  @Param("keyword")String keyword);
}
