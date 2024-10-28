package com.example.exe201.service;

import com.example.exe201.model.Product;
import com.example.exe201.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product findProductByID(int productID){
        return productRepository.findById(productID).orElse(null);
    }
    public List<Product> getProductByCategoryID(int categoryID){
        return productRepository.findByCategoryID(categoryID);
    }
    public List<Product> findProductsByName(String productName){
        return productRepository.findProductByProductNameContaining(productName);
    }
    public void incrementViewCount(int productID) {
        Product product = productRepository.findById(productID).orElse(null);
        if (product != null) {
            product.setViewCount(product.getViewCount() + 1);
            productRepository.save(product);
        }
    }

}
