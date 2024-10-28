package com.example.exe201.repository;

import com.example.exe201.model.Comment;
import com.example.exe201.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentsByProductID(int productID);
}
