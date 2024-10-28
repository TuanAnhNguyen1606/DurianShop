package com.example.exe201.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "blog_comment")
public class BlogComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int blogCommentID;
    @ManyToOne
    @JoinColumn(name = "blog_id", referencedColumnName = "id", insertable = true, updatable = false)
    private Blog blog;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", insertable = true, updatable = false)
    private User user;
    @Column(name = "content")
    private String commentContent;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "parent_id")
    private Integer parentID;

}
