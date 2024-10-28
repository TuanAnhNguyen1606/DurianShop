package com.example.exe201.service.impl;

import com.example.exe201.model.Comment;
import com.example.exe201.repository.CommentRepository;
import com.example.exe201.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    @Override
    public List<Comment> findCommentsByProductID(int productID) {
        return commentRepository.findCommentsByProductID(productID);
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

}
