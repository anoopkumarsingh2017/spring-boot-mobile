package com.springboot.dao.mobile.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.springboot.dao.mobile.MobileDao;
import com.springboot.modal.mobile.Mobile;

@Repository("mobileDaoJdbcDataSourceDependencyInjectionImpl")
public class MobileDaoJdbcDataSourceDependencyInjectionImpl implements MobileDao {

	private static final Logger logger = LoggerFactory.getLogger(MobileDaoJdbcDataSourceDependencyInjectionImpl.class);
	static {
		try {
			logger.info("Loading driver");
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	DataSource dataSource = null;

	@Autowired
	ApplicationContext context;

	private DataSource getDataSource() {

		System.out.println("Getting Connecting from database");
		logger.info("Getting datasource");
		if (dataSource == null) {
			dataSource = context.getBean("postgresqlDataSource", DataSource.class);
		}
		return dataSource;
	}

	@Override
	public List<Mobile> list() {
		
		logger.info("Inside list()");

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
			logger.info("Fetched mobile objects");
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
