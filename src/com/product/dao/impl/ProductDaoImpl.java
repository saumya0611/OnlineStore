package com.product.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.product.dao.IProductDao;
import com.product.model.Product;

public class ProductDaoImpl extends JdbcTemplate implements IProductDao {

	@Override
	public Product getProductDetail(int productId) {
		Product product = new Product();
		product.setId(productId);

		return product;
	}

	@Override
	public List<Product> getProducts() {

		return this.query("Select * from \"T_PRD_DTL\"",
				(rs, arg1) ->
					{
						Product prd = new Product();
						prd.setId(rs.getInt("PRD_ID"));
						prd.setName(rs.getString("PRD_NAME"));
						prd.setCount(rs.getInt("PRD_COUNT"));
						return prd;
					});
	}

	@Override
	public boolean buyProduct(final Product product) {
		this.update(
				"UPDATE \"T_PRD_DTL\" SET \"PRD_COUNT\"=((SELECT \"PRD_COUNT\" FROM \"T_PRD_DTL\" WHERE \"PRD_ID\" = ?) -1) WHERE \"PRD_ID\" = ?",
				(ps) ->
					{
						ps.setInt(1, product.getId());
						ps.setInt(2, product.getId());

					});
		return true;
	}

}
