package com.springboot.dao.mobile.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.stereotype.Repository;

import com.springboot.dao.mobile.MobileDao;
import com.springboot.modal.mobile.Mobile;

@Repository("mobileDaoJdbcDataSourceImpl")
public class MobileDaoJdbcDataSourceImpl implements MobileDao {

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	DataSource dataSource = null;

	private DataSource getDataSource() {

		System.out.println("Getting DataSource");

		if (dataSource == null) {
			PGSimpleDataSource dataSource = new PGSimpleDataSource();
			dataSource.setServerName("localhost");
			dataSource.setPortNumber(5432);
			dataSource.setDatabaseName("postgres");
			dataSource.setUser("postgres");
			dataSource.setPassword("postgresp");
		}
		return dataSource;
	}

	@Override
	public List<Mobile> list() {

		String sql = "select * from mobile";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Mobile> mobiles = new ArrayList<>();
		try {
			ps = getDataSource().getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				// Retrieve by column name
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				String company = rs.getString("company");

				Mobile mobile = new Mobile(name, price, company);
				mobiles.add(mobile);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return mobiles;

	}

}
