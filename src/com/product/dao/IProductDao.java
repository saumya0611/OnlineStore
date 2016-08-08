package com.product.dao;

import java.util.List;

import com.product.model.Product;

public interface IProductDao {
	
	public Product getProductDetail(int productId);
	
	public List<Product> getProducts();

	public boolean buyProduct(final Product product);

}
