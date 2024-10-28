package com.example.exe201.service;

import com.example.exe201.model.Blog;
import com.example.exe201.model.BlogDetail;


import java.util.List;
import java.util.Optional;

public interface BlogService {

    public List<Blog> getAllBlogs();
    public Optional<Blog> getBlogDetail(int blogID);
    public List<BlogDetail> getBlogDetails(int blogId);

}
