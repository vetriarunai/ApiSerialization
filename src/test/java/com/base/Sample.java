package com.base;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pojo.Address_Input_POJO;
import com.pojo.Login;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Sample extends BaseClass {
	Response response;
	String logtoken;

	@Test(priority = 1)

	private void login() {
		addHeader("accept", "application/json");
		basicAuth("vetriarunai@gmail.com", "Vetri@7627");
		response = reqType("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");
		int getStatusCode = getStatusCode(response);
		Assert.assertEquals(getStatusCode, 200, "Verify Status");
		String asPrettyString = asPrettyString(response);
		System.out.println(asPrettyString);
		Login as = response.as(Login.class);
		String message = as.getMessage();
		System.out.println(message);
		Assert.assertEquals(message, "Login successfully", "Verify Success Message");
		logtoken = as.getData().getLogtoken();
	}

	@Test(priority = 2)
	private void addAddress() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		Address_Input_POJO address_Input_POJO = new Address_Input_POJO("Vetri", "Vel", "987654321", "Rose Garden", 143,
				148, 152, "6216001", "ganesh Nagar", "Home");
		addbody(address_Input_POJO);
		response = reqType("POST", "https://omrbranch.com/api/addUserAddress");
		int getStatusCode = getStatusCode(response);
		System.out.println(getStatusCode);
		//Assert.assertEquals(getStatusCode, 200, "Verify Status");
		String asPrettyString = asPrettyString(response);
		System.out.println(asPrettyString);

	}
	@Test (priority=3)
	private void updateAddress() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);
		}

}
