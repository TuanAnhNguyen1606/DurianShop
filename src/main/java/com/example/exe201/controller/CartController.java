package com.example.exe201.controller;

import com.example.exe201.model.CartItem;
import com.example.exe201.model.Product;
import com.example.exe201.model.User;
import com.example.exe201.repository.ProductRepository;
import com.example.exe201.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;

@Controller
@RequestMapping("/Cart")
public class CartController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String cart(HttpSession session, Model model){
        HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");
        if(cart == null){
            cart = new HashMap<>();
        }
        double totalAmount = cart.values().stream().mapToDouble(item -> item.getProduct().getProductPrice() * item.getQuantity()).sum();
        model.addAttribute("cart", cart);
        model.addAttribute("totalAmount", totalAmount);
        return "cart";
    }
    @GetMapping("/add-to-cart")
    public String addToCart(HttpSession session, @RequestParam(name = "productID") int productID,
                            @RequestParam(name = "quantity", defaultValue = "1") int quantity,
                            Model model, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/Account/Login";
        }
        HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        Product product = productService.findProductByID(productID);
        if (quantity > product.getStock()) {
            redirectAttributes.addFlashAttribute("errorQuantity", "Số lượng không được lớn hơn số lượng trong kho.");
            return "redirect:/Store/ProductDetail?productID=" + productID; // Redirect về trang sản phẩm (hoặc trang nào đó bạn muốn)
        }
        CartItem cartItem = cart.get(productID);
        if (cartItem == null) {
            cartItem = new CartItem(product, quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cart.put(productID, cartItem);
        int totalQuantity = cart.values().stream().mapToInt(CartItem::getQuantity).sum();
        if(totalQuantity > product.getStock()){
            totalQuantity = product.getStock();
        }
        double totalAmount = cart.values().stream().mapToDouble(item -> item.getProduct().getProductPrice() * item.getQuantity()).sum();
        session.setAttribute("cart", cart);
        model.addAttribute("cart", cart);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("totalQuantity", totalQuantity);
        return "cart";
    }


    @GetMapping("/delete-from-cart")
    public String deleteFromCart(HttpSession session, @RequestParam(name = "productID") int productID, Model model){
        User user = (User) session.getAttribute("user");
        if(user== null){
            return "redirect:/Account/Login";
        }
        HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        cart.remove(productID);
        double totalAmount = cart.values().stream().mapToDouble(item -> item.getProduct().getProductPrice() * item.getQuantity()).sum();
        session.setAttribute("cart", cart);
        model.addAttribute("cart", cart);
        model.addAttribute("totalAmount", totalAmount);
        return "cart";
    }
}
