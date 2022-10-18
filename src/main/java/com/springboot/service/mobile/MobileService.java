package com.springboot.service.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springboot.dao.mobile.MobileDao;
import com.springboot.modal.mobile.Mobile;

@Service
public class MobileService {

	@Autowired
	@Qualifier("mobileDaoJdbcDataSourceDependencyInjectionImpl")
	MobileDao dao;

	public List<Mobile> list() {
		return dao.list();
	}
}
