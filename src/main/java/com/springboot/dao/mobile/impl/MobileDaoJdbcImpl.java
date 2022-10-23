package com.springboot.dao.mobile.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.dao.mobile.MobileDao;
import com.springboot.modal.mobile.Mobile;

@Repository
public class MobileDaoJdbcImpl implements MobileDao {
	
	private Connection conn;

	private Connection getConnection() {
		System.out.println("Getting Connecting from database");
		if (conn == null) {
			try {
				Class.forName("org.postgresql.Driver");
				System.out.println("Loading driver..");
				conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres?user=postgres&password=postgresp");
				System.out.println("Connected to database");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
		}
		return conn;
	}

	@Override
	public List<Mobile> list() {

		String sql = "select * from mobile";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Mobile> mobiles = new ArrayList<>();
		try {
			ps = getConnection().prepareStatement(sql);
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
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return mobiles;

	}

}
