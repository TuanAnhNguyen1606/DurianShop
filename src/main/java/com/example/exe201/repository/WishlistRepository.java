package com.example.exe201.repository;

import com.example.exe201.model.User;
import com.example.exe201.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> findWishlistsByUser(User user);
}
