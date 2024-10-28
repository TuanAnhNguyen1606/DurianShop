package com.example.exe201.service;

import com.example.exe201.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();
    List<Comment> findCommentsByProductID(int productID);

    void addComment(Comment comment);
}
