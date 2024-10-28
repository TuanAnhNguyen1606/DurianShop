package com.example.exe201.repository;

import com.example.exe201.model.BlogDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDetailRepository extends JpaRepository<BlogDetail, Integer> {
    List<BlogDetail> findByBlogID(int blogId);
}
