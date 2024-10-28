package com.example.exe201.service.impl;


import com.example.exe201.model.BlogComment;
import com.example.exe201.repository.BlogCommentRepository;
import com.example.exe201.service.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogCommentServiceImpl implements BlogCommentService {

    @Autowired
    private BlogCommentRepository blogCommentRepository;
    @Override
    public List<BlogComment> getAllBlogComments() {
        return blogCommentRepository.findAll();
    }

    @Override
    public List<BlogComment> getAllBlogCommentsByBlogID(int blogID) {
        return blogCommentRepository.findByBlogBlogID(blogID);
    }
    @Override
    public void addBlogComment(BlogComment blogComment){
        blogCommentRepository.save(blogComment);
    }
}
