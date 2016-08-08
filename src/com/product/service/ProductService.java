package com.product.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.product.model.Product;
 

@Path("/")
@WebService(name="productService", targetNamespace="http://www.product.com/Product")
public interface ProductService 
{
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getproductdetail")
	public Response getProductDetail(@QueryParam("productId") String productId);
	
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/buyproducts")
	public Response buyProducts(List<Product> products);
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getproducts")
	public Response getProducts();
	
}