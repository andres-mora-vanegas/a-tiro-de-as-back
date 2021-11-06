package com.atirodeas.crud.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.atirodeas.crud.models.Product;
import com.atirodeas.crud.repository.ProductRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Repository
public class ProductDAOImpl implements IProductDAO {

	@Value("${server.port}")
	private String PORT_PUBLIC;

	private final String STATIC_DIRECTORY = "target/classes/static/";
	private final String STATIC_PUBLIC_DIRECTORY = "http://127.0.0.1:8080/content/";
	private final String STATIC_IMAGE_EXTENSION = ".png";

	private final ProductRepository productRepository;

	@Autowired
	ProductDAOImpl(ProductRepository iProductRepository) {
		this.productRepository = iProductRepository;
	}

	private String uploadFile(Product product, String id) {
		String encodedImg[] = product.getImageUrl().split(",");
		String fileName = id + STATIC_IMAGE_EXTENSION;
		if(encodedImg.length>0) {
			byte[] decodedImg = Base64.getDecoder().decode(encodedImg[1].getBytes(StandardCharsets.UTF_8));
			Path destinationFile = Paths.get(STATIC_DIRECTORY, fileName);
			try {
				Files.write(destinationFile, decodedImg);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return STATIC_PUBLIC_DIRECTORY + id + STATIC_IMAGE_EXTENSION;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(String id) {
		Optional<Product> product = productRepository.findById(id);
		return product.get();
	}

	@Override
	public Product getProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product addProduct(Product product) {

		Product originalProduct = new Product(null,null,null,null,null,null,null);
		BeanUtils.copyProperties(product, originalProduct);
		product.setImageUrl("");
		Product newProduct = productRepository.insert(product);

		String fileLink = this.uploadFile(originalProduct, newProduct.getId());
		newProduct.setImageUrl(fileLink);
		productRepository.save(newProduct);
		return newProduct;
	}

	@Override
	public Product updateProduct(Product product, String id) {

		Optional<Product> existingProduct = productRepository.findById(id);
		if (existingProduct.isPresent()) {
			String fileLink = this.uploadFile(product, existingProduct.get().getId());
			product.setId(existingProduct.get().getId());
			product.setImageUrl(fileLink);
			return productRepository.save(product);
		}
		return existingProduct.get();

	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);

	}

}
