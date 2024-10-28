package com.example.exe201.service.impl;

import com.example.exe201.model.Blog;
import com.example.exe201.model.BlogDetail;
import com.example.exe201.repository.BlogDetailRepository;
import com.example.exe201.repository.BlogRepository;
import com.example.exe201.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogDetailRepository blogDetailRepository;
    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll(Sort.by(Sort.Direction.DESC, "blogCreatedAt"));
    }
    @Override
    public Optional<Blog> getBlogDetail(int blogID) {
        return blogRepository.findById(blogID);
    }
    @Override
    public List<BlogDetail> getBlogDetails(int blogId) {
        return blogDetailRepository.findByBlogID(blogId);
    }
}
