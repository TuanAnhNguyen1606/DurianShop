package com.example.exe201.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="blog")
public class Blog {
    @Id
    @Column(name="id")
    private int blogID;
    @Column(name = "title")
    private String blogTitle;
    @Column(name = "summary")
    private String blogSummary;
    @Column(name = "author")
    private String blogAuthor;
    @Column(name = "created_at")
    private LocalDate blogCreatedAt;
    @Column(name="thumbnail_url")
    private String blogThumbnail;
}
