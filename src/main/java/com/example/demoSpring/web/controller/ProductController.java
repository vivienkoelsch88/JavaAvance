package com.example.demoSpring.web.controller;
import com.example.demoSpring.dao.ProductDao;
import com.example.demoSpring.dao.ProductDaoImpl;
import com.example.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api(description="Une Api de test lien = https://openclassrooms.com/fr/courses/4668056-construisez-des-microservices/5123565-documentez-votre-microservice-avec-swagger-2")
@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    //List<Product>

    @ApiOperation(value="Ici, la liste de tout les produits")
    @RequestMapping(value="/Produits", method=RequestMethod.GET)
    public List<Product> listeProduits(Model model) {

        model.addAttribute("products", productDao.findAll());

        return productDao.findAll();
        //return "Produits";

    }

    @ApiOperation(value="Ici, un seul produit")
    @GetMapping(value="/Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id) {
        return productDao.findById(id);
    }

    @ApiOperation(value="Ici, on retire un produit")
    @DeleteMapping(value="/RemoveProduits/{id}")
    public Product supprimerUnProduit(@PathVariable int id) {
        return productDao.delete(id);
    }

    @ApiOperation(value="Ici, on ajoute un produit")
    @RequestMapping(value="/AddProduits", method=RequestMethod.POST)
    public ResponseEntity<Void> ajouterProduit(@RequestBody Product product) {

        Product productAdded =  productDao.save(product);

        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    //----------------------------------------------------------------------------------------------------
    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDaoImpl productDao) {
        this.productDao = productDao;
    }
}
