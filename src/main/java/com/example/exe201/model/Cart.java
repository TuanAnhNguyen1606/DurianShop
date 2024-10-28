package com.example.exe201.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="cart")
public class Cart {
    @Id
    @Column(name = "id")
    private int cartID;
    @Column(name = "user_id")
    private Long userID;
    @Column(name ="created_at")
    private LocalDate createdAt;
    @Column(name="updated_at")
    private LocalDate updatedAt;

}
