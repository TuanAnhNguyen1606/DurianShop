package com.example.exe201.controller;
import com.example.exe201.model.Category;
import com.example.exe201.model.Comment;
import com.example.exe201.model.Product;
import com.example.exe201.model.User;
import com.example.exe201.service.CategoryService;
import com.example.exe201.service.ProductService;
import com.example.exe201.service.impl.CommentServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/Store")
public class StoreController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentServiceImpl commentService;
    @GetMapping
    public String store(@RequestParam(name= "categoryID", required = false) Integer categoryID,
                        @RequestParam(name = "productName", required = false) String productName,
                        Model model){
        List<Product> products = new ArrayList<>();
        if (productName != null && !productName.trim().isEmpty()) {
            products = productService.findProductsByName(productName);
        } else if (categoryID == null || productName == null) {
            products = productService.getAllProducts();
        } else {
            products = productService.getProductByCategoryID(categoryID);
        }
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories", categoryList);
        model.addAttribute("products", products);
        model.addAttribute("selectedCategoryID", categoryID);
        return "shop";
    }
    @GetMapping("/ProductDetail")
    public String productDetail(Model model,
                                @RequestParam(name = "productID", required = false) Integer productID,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        if (productID != null) {
            Product product = productService.findProductByID(productID);
            if (product != null) {
                String cookieName = "viewedProduct_" + productID;
                Cookie[] cookies = request.getCookies();
                boolean hasViewed = false;
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookieName.equals(cookie.getName())) {
                            hasViewed = true;
                            break;
                        }
                    }
                }
                if (!hasViewed) {
                    productService.incrementViewCount(productID);
                    Cookie viewCookie = new Cookie(cookieName, "true");
                    viewCookie.setMaxAge(30 * 24 * 60 * 60);
                    response.addCookie(viewCookie);
                }
                model.addAttribute("pro", product);
                List<Comment> commentList = commentService.findCommentsByProductID(productID);
                model.addAttribute("commentList", commentList);
                return "product-single";
            } else {
                return "404"; // Sản phẩm không tồn tại
            }
        } else {
            return "404"; // Không có productID
        }
    }


    @PostMapping("/add-comment")
    public String addComment(@RequestParam(name = "productID") Integer productID,
                             @RequestParam(name = "content") String content,
                             RedirectAttributes redirectAttributes, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user != null){
            Comment comment = Comment.builder()
                    .user(user)
                    .commentText(content)
                    .productID(productID)
                    .commentedDate(LocalDate.now())
                    .build();
            commentService.addComment(comment);
            redirectAttributes.addFlashAttribute("commentSuccess", "Bạn đã bình luận thành công");
            return "redirect:/Store/ProductDetail?productID=" + productID;
        }
        return "redirect:/Account/Login";

    }



}
