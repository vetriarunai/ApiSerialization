package com.base;


import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	RequestSpecification reqspec;
	Response response;
	public void basicAuth(String username, String password) {
		reqspec = RestAssured.given().auth().preemptive().basic(username,password);
	}

	public void addHeader(String key, String value) {
		reqspec = RestAssured.given().header(key, value);

	}

	public void addHeaders(Headers header) {
		reqspec = RestAssured.given().headers(header);
	}

	public void queryParam(String key, String value) {
		reqspec = reqspec.queryParam(key, value);
	}

	public void pathparam(String key, String value) {
		reqspec = reqspec.pathParam(key, value);

	}

	public void addbody(String body) {
		reqspec = reqspec.body(body);
	}

	public void addbody(Object body) {
		reqspec = reqspec.body(body);
	}

	public Response reqType(String type, String endpoint) {
		switch (type) {
		case "GET":
			response = reqspec.get(endpoint);

			break;
		case "PUT":
			response = reqspec.put(endpoint);
			break;
		case "POST":
			response = reqspec.post(endpoint);

			break;
		case "DELETE":
			response = reqspec.delete(endpoint);
			break;

		default:
			break;
		}
		return response;
	}

	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	public String asResBpodyString(Response reponse) {
		String asString = response.asString();
		return asString;
	}

	public String asPrettyString(Response response) {

		String asPrettyString = response.asPrettyString();
		return asPrettyString;

	}
}