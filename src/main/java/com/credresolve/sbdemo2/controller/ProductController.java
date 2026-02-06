package com.credresolve.sbdemo2.controller;

import com.credresolve.sbdemo2.model.Product;
import com.credresolve.sbdemo2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService psr;
    public ProductController(ProductService psr){
        this.psr = psr;
    }
    @PostMapping("/add")
    public void add(@RequestBody Product p){
        psr.addProduct(p);
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return psr.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getById(@PathVariable int id){
        return psr.getById(id);
    }
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product p){
        return psr.updateProduct(id, p);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        psr.deleteById(id);
    }
    @GetMapping("/listPages")
    public List<Product> getProductsByPage(@RequestParam(required = false, defaultValue = "1") int pageNo,
                                           @RequestParam(required = false, defaultValue = "3") int pageSize,
                                           @RequestParam String sortBy,
                                           @RequestParam String sortDir){
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("ASC")){
            sort = Sort.by(sortBy).ascending();
        }
        else{
            sort = Sort.by(sortBy).descending();
        }
        Sort.by(sortBy).descending();
        PageRequest.of(pageNo, pageSize);
        return psr.getAllProductsByPages(PageRequest.of(pageNo,pageSize,sort));
    }
}
