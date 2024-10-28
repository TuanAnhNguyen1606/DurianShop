package com.example.exe201.controller;

import com.example.exe201.model.Product;
import com.example.exe201.model.User;
import com.example.exe201.model.Wishlist;
import com.example.exe201.service.ProductService;
import com.example.exe201.service.impl.WishlistServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/Account")
public class WishlistController {
    @Autowired
    private WishlistServiceImpl wishlistService;
    @Autowired
    private ProductService productService;
    @GetMapping("/MyWishlist")
    public String wishList(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if(user== null){
            return "redirect:/Account/Login";
        }
        List<Wishlist> wishlistList = wishlistService.getWishlistByUserID(user);
        model.addAttribute("wishlistList", wishlistList);
        return "wishlist";
    }
    @GetMapping("/AddToWishlist")
    public String addToWishlist(HttpSession session, Model model, @RequestParam(name="productID") int productID){
        User user = (User) session.getAttribute("user");
        if(user== null){
            return "redirect:/Account/Login";
        }
        Product product = productService.findProductByID(productID);
        Wishlist wishlist = Wishlist.builder()
                .user(user)
                .addedDate(LocalDate.now())
                .product(product)
                .build();
        wishlistService.addWishlist(wishlist);
        return "redirect:/Account/MyWishlist";
    }
    @GetMapping("/DeleteWishlist")
    public String deleteWishlist(HttpSession session, Model model, @RequestParam(name="wishlistID") int wishlistID){
        User user = (User) session.getAttribute("user");
        if(user== null){
            return "redirect:/Account/Login";
        }
        wishlistService.deleteWishlist(wishlistID);
        return "redirect:/Account/MyWishlist";
    }
}
