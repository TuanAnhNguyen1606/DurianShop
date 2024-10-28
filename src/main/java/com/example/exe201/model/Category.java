package com.example.exe201.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="categories")
public class Category {
    @Id
    @Column(name = "id")
    private int categoryID;
    @Column(name = "category_name")
    private String categoryName;

}
