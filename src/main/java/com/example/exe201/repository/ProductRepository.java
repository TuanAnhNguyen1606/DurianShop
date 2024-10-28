package com.example.exe201.repository;

import com.example.exe201.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryID(int categoryID);
    List<Product> findProductByProductNameContaining(String productName);
    

}
