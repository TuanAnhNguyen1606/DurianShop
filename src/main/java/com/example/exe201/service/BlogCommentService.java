package com.example.exe201.service;

import com.example.exe201.model.BlogComment;

import java.util.List;

public interface BlogCommentService {

    List<BlogComment> getAllBlogComments();

    List<BlogComment> getAllBlogCommentsByBlogID(int blogID);

    void addBlogComment(BlogComment blogComment);
}
