package com.ptdev.pi.examples;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/test")
public class HelloWorld {
	@GET
	@Path("/sayHello")
	public Response sayHello( @QueryParam("from") String from, @QueryParam("to") String toPerson) {
		String response = "<h1>" + from + " says hello to " + toPerson + "</h1>";
		return Response.status(200).entity(response).build();
	}
}