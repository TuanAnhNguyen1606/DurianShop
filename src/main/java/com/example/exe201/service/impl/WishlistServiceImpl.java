package com.example.exe201.service.impl;

import com.example.exe201.model.User;
import com.example.exe201.model.Wishlist;
import com.example.exe201.repository.WishlistRepository;
import com.example.exe201.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;


    @Override
    public List<Wishlist> getWishlistByUserID(User user) {
        return wishlistRepository.findWishlistsByUser(user);
    }

    @Override
    public void addWishlist(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }

    @Override
    public void deleteWishlist(int wishlistID) {
        wishlistRepository.deleteById(wishlistID);
    }
}
