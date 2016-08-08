package com.product.service.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.product.dao.IProductDao;
import com.product.model.Product;
import com.product.service.ProductService;

public class ProductServiceImpl implements ProductService{
	
	@Autowired
	IProductDao productDao;

	@Override
	public Response getProductDetail(String productId) {
		if(productId == null)
		{
			return Response.status(Response.Status.BAD_REQUEST).build();
		}		
		return Response.ok(productDao.getProductDetail(Integer.parseInt(productId))).build();
	}
	

	@Transactional
	@Override
	public Response buyProducts(List<Product> products) {
		if(products == null)
		{
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		for(Product product : products){
			if(!productDao.buyProduct(product)){
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		}
		return Response.ok().build();
	}

	@Override
	public Response getProducts() {
		
		List<Product> products = productDao.getProducts();
		
		return Response.ok(products).build();
	}

}
