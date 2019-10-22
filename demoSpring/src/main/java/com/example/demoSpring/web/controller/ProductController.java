package com.example.demoSpring.web.controller;
import com.example.demoSpring.dao.ProductDao;
import com.example.demoSpring.dao.ProductDaoImpl;
import com.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @Value("${hello}")
    private String hello = "Hello";

    //List<Product>

    @RequestMapping(value="/Produits", method=RequestMethod.GET)
    public String listeProduits(Model model) {

        model.addAttribute("products", productDao.findAll());

        return "Produits";

    }

    @GetMapping(value="/Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id) {
        return productDao.findById(id);
    }

    //----------------------------------------------------------------------------------------------------
    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDaoImpl productDao) {
        this.productDao = productDao;
    }
}
