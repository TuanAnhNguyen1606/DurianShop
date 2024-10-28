package com.example.exe201.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "blogdetail")
public class BlogDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "content_type")
    private String contentType;
    @ManyToOne
    @JoinColumn(name = "blog_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Blog blog; // Tham chiếu đến Blog
    @Column(name = "content")
    private String content;
    @Column(name = "blog_id") // Có thể giữ lại nếu bạn cần blogID riêng
    private int blogID; // Nếu bạn cần lưu trữ ID này
    @Column(name = "image_url")
    private String imageURL;
}

    // Getters và setters}
