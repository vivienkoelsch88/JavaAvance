package com.example.demoSpring.dao;
import com.example.model.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> findAll();
    public Product findById(int id);
    public Product save(Product product);
    public Product delete(int id);
}
