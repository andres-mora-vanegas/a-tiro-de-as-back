package com.atirodeas.crud.dao;

import java.util.List;

import com.atirodeas.crud.models.Product;

public interface IProductDAO {

	public List<Product> getAllProducts();

	public Product getProductById(String id);

	public Product getProductByName(String name);

	public Product addProduct(Product product);

	public Product updateProduct(Product product, String id);

	public void deleteProduct(Product product);

}
