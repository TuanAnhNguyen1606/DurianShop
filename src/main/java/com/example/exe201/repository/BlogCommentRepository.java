package com.example.exe201.repository;

import com.example.exe201.model.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogCommentRepository extends JpaRepository<BlogComment, Integer> {
    List<BlogComment> findByBlogBlogID(int blogID);
}
