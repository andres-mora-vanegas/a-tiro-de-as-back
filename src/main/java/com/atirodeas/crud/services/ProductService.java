package com.atirodeas.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atirodeas.crud.dao.ProductDAOImpl;
import com.atirodeas.crud.models.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductDAOImpl productDAO;

	@Autowired
	public ProductService(ProductDAOImpl productDAO) {
		this.productDAO = productDAO;
	}

	public List<Product> getAllProducts() {
		return productDAO.getAllProducts();
	}

	public Product getProduct(String id) {
		return productDAO.getProductById(id);
	}

	public Product getProductByName(String name) {
		return productDAO.getProductByName(name);
	}

	public Product addProduct(Product product) {
		return productDAO.addProduct(product);
	}

	public Product updateProduct(Product product,String id) {
		return productDAO.updateProduct(product,id);
	}

	public void removeProduct(String id) {
		Product product = productDAO.getProductById(id);
		productDAO.deleteProduct(product);
	}
	
	public void deleteAll() {
		productDAO.deleteAll();
	}

}
