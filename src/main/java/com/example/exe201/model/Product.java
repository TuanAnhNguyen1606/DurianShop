package com.example.exe201.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    private int productID;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "description")
    private String productDescription;
    @Column(name = "price")
    private double productPrice;
    @Column(name="weight")
    private double productWeight;
    @Column(name = "stock_quantity")
    private int stock;
    @Column(name = "category_id")
    private int categoryID;
    @Column(name = "image_url")
    private String image;
    @Column(name ="quantity", nullable = true)
    private Integer quantity;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    @Column(name ="view_count")
    private int viewCount;
}
