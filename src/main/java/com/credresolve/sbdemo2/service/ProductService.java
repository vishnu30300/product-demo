package com.credresolve.sbdemo2.service;

import com.credresolve.sbdemo2.dao.ProductRepository;
import com.credresolve.sbdemo2.exception.InvalidProductException;
import com.credresolve.sbdemo2.exception.ProductNotFoundException;
import com.credresolve.sbdemo2.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository prp;
    public ProductService(ProductRepository prp){
        this.prp = prp;
    }
    public Product addProduct(Product p) {
        if(p.getPrice()<0){
            throw new InvalidProductException("wrong price");
        }
        return prp.save(p);
    }
    public Product getById(int id) {
        return prp.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id " + id));
    }
    public List<Product> getAllProducts(){
        return prp.findAll();
    }

    public Product updateProduct(int id, Product np){
        Product p1 = getById(id);
        p1.setPname(np.getPname());
        p1.setPrice(np.getPrice());
        return prp.save(p1);
    }
    public List<Product> getByPrice(double price) {
        return prp.findByPriceLessThanEqual(price);
    }
    public void deleteById(int pid){
        prp.deleteById(pid);
    }
    public List<Product> getAllProductsByPages(Pageable pg){
        return prp.findAll(pg).getContent();
    }


}
