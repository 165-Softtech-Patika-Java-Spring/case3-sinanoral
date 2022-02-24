package com.patika.dao;

import com.patika.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {

    @Query("from Comment where product.id = ?1")
    List<Comment> getCommentsByProductId(Long id);

//    List<Comment> getCommentsByUserId(Long id);
}
