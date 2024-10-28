package com.example.exe201.service;

import com.example.exe201.model.User;
import com.example.exe201.model.Wishlist;

import java.util.List;

public interface WishlistService {
    List<Wishlist> getWishlistByUserID(User user);

    void addWishlist(Wishlist wishlist);

    void deleteWishlist(int wishlistID);
}
