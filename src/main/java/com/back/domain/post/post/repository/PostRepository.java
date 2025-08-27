package com.back.domain.post.post.repository;

import com.back.domain.post.post.dto.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostRepository {
    @Select("""
            <script>
            SELECT * FROM post
            <if test="!orderBy.isEmpty() and orderBy == ''">
            ORDER BY
                     <choose>
                            <when test="orderBy.equals('title')">
                                title 
                             </when>
                             <when test="orderBy.equals('createDate')">
                                createDate 
                             </when>
                            <when test="orderBy.equals('modifyDate')">
                                modifyDate 
                             </when>
                    </choose>
                    <if test="!orderBy.isEmpty() and direction.toUpperCase() == 'DESC'">
                        DESC
                    </if>
            </if>
            </script>
            """)
    List<Post> findAll(@Param("orderBy") String orderBy, @Param("direction")String direction);

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
                    <where>
                        <choose>
                             <when test="type.equals('title')">
                                title LiKE CONCAT('%', #{keyword}, '%')
                             </when>
                             <when test="type.equals('content')">
                                content LiKE CONCAT('%', #{keyword}, '%')
                             </when>
                             <otherwise>
                                (
                                    title LiKE CONCAT('%', #{keyword}, '%')
                                    OR
                                    content LiKE CONCAT('%', #{keyword}, '%')
                                )
                            </otherwise>
                        </choose>
                    </where>
        </script>
    """)
    List<Post> findByType(@Param("type")String type,  @Param("keyword")String keyword);
}
