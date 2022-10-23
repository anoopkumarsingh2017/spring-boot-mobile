package com.springboot.handler.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.modal.mobile.Mobile;
import com.springboot.service.mobile.MobileService;

@RestController
@RequestMapping("/m")
public class MobileHandler {

	@Autowired
	MobileService mobileService;

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String getDefault() {
		logIt();
		return "Default method called!";
	}

	public String logIt() {
		return "Welcome to the log!";
	}

	@RequestMapping(value = "/mobiles", method = RequestMethod.GET)
	public List<Mobile> getList() {
		return mobileService.list();
	}

}
