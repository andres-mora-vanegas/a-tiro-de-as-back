package com.atirodeas.crud.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atirodeas.crud.models.Product;
import com.atirodeas.crud.services.ProductService;

@RequestMapping("api/v1/products")
@CrossOrigin(origins = "*")
@RestController
public class ProductController {

	private final String DELETED_SUCCESS = "Producto eliminado correctamente";
	private final String UPDATE_SUCCESS = "Producto actualizado correctamente";

	@Autowired
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<Product>> get() {
		List<Product> productList = productService.getAllProducts();
		return ResponseEntity.ok(productList);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") String id) {
		return ResponseEntity.ok(productService.getProduct(id));
	}

	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {

		return ResponseEntity.ok(productService.addProduct(product));
	}

	@PatchMapping(value = "{id}")
	public ResponseEntity<String> update(@RequestBody Product product, @PathVariable("id") String id) {
		productService.updateProduct(product, id);
		return ResponseEntity.ok(UPDATE_SUCCESS);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> remove(@PathVariable("id") String id) {
		productService.removeProduct(id);
		return ResponseEntity.ok(DELETED_SUCCESS);
	}
}
