package com.qylm.spring.webservice.desk;

import java.net.URI;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path(value = "/authorize")
public class AuthorizeServiceImpl implements AuthorizeService {
	
	@Context
    private UriInfo uriInfo;
	
	public String doGet() {
		URI requestUri = uriInfo.getRequestUri();
		String query = requestUri.getQuery();
		System.out.println(query);
		return "";
	}
	
}
