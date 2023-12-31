package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회
    @Query(value=
            "select * " +
            "from Comment " +
            "where article_id = :articleId" // :붙이고 매개변수에 @Param 붙여야 매개변수와 연결
            , nativeQuery = true) // nativeQuery=true 하면 JPQL 대신 기본 SQL 사용 가능
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(@Param("nickname") String nickname);
}
