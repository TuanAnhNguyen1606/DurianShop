package com.example.exe201.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int wishlistID;
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "product_id")
    private Product product;
    @Column(name="added_at")
    private LocalDate addedDate;
}
