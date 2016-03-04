package com.qylm.spring.webservice.desk;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface AuthorizeService {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String doGet();

}
