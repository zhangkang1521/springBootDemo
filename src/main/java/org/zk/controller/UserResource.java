package org.zk.controller;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/user")
@Component
public class UserResource {

	@Path("/hi")
	@GET
	public String getUsers() {
		return "hello,jersey";
	}
}
