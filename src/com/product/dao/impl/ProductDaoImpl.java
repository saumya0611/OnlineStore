package com.product.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

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
				new RowMapper<Product>() {

					@Override
					public Product mapRow(ResultSet rs, int arg1)
							throws SQLException {
						Product prd = new Product();
						prd.setId(rs.getInt("PRD_ID"));
						prd.setName(rs.getString("PRD_NAME"));
						prd.setCount(rs.getInt("PRD_COUNT"));
						return prd;
					}

				});
	}

	@Override
	public boolean buyProduct(final Product product) {
		this.update(
				"UPDATE \"T_PRD_DTL\" SET \"PRD_COUNT\"=((SELECT \"PRD_COUNT\" FROM \"T_PRD_DTL\" WHERE \"PRD_ID\" = ?) -1) WHERE \"PRD_ID\" = ?",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps)
							throws SQLException {
						ps.setInt(1, product.getId());
						ps.setInt(2, product.getId());
					}

				});
		return true;
	}

}
