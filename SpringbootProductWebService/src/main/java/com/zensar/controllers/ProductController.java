package com.zensar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.entities.Product;
import com.zensar.services.ProductService;
@CrossOrigin
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
    @GetMapping("/products")
	public List<Product> getAllProducts() {

		return productService.findallProduct();

	}
    
    
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") int prod_id) {

		return productService.findProductsById(prod_id);

	}
    
    @PostMapping("/products/add")
    public String add(@RequestBody Product product)
    {
    	productService.addproduct(product);
    	return "new product" +product.getProductId()+"is added sucessfully";
    }
    @PostMapping("/addproduct")
    public String addProduct(@RequestParam("id") int prod_id,@RequestParam("name")String name,@RequestParam("brand")String brand,@RequestParam("price")float price)
    {
    	Product prod=new Product(prod_id, name, brand, price);
    	productService.addproduct(prod);
    	return "new product" +prod.getProductId()+"is added sucessfully";
    }
    
    @PutMapping("/product/update")
	public String update(@RequestBody Product product) {
		if (productService.findProductsById(product.getProductId()) != null) {
			productService.updateProduct(product);
			return " product" + product.getProductId() + "is updated sucessfully";
		} else {
			return " product" + product.getProductId() + "not found";
		}

	}
    @DeleteMapping("/product/delete")
    public String delete(@RequestBody Product product) {
		if (productService.findProductsById(product.getProductId()) != null) {
			productService.removeProduct(product);;
			return " product" + product.getProductId() + "is deleted sucessfully";
		} else {
			return " product" + product.getProductId() + "not found";
		}

	}
}
