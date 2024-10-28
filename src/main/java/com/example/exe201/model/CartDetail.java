package com.example.exe201.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="cart_items")
public class CartDetail {
    @Id
    @Column(name="id")
    private int cartDetailID;
    @Column(name="cart_id")
    private int cartID;
    @Column(name="product_id")
    private int productID;
}
