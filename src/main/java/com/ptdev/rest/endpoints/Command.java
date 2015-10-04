package com.ptdev.rest.endpoints;

import java.io.FileNotFoundException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.esotericsoftware.yamlbeans.YamlException;
import com.ptdev.exceptions.ConfigDirectoryException;
import com.ptdev.exceptions.InvalidConfigSetupException;
import com.ptdev.picore.io.IoContext;

import com.ptdev.support.ConfigReader;

@Path("/cmd")
public class Command {

	@POST
	@Path("/{sequence}")
	public Response comResponse( @PathParam("sequence") String seq) {
		//Run sequence based on name
		try {
			IoContext.getInstance();
			new ConfigReader(seq).getSequence().start();
		} catch (FileNotFoundException e) {
			return Response.status(404).entity("One of the files was not found: Stack - " + e.toString()).build();
		} catch (YamlException e) {
			return Response.status(400).entity("Config file was not formatted right: Stack - " + e.toString()).build();
		} catch (InvalidConfigSetupException e) {
			return Response.status(400).entity("Config file was not setup right: Stack - " + e.toString()).build();
		} catch (ConfigDirectoryException e) {
			return Response.status(404).entity("Config file did not exist or was not set: Stack - " + e.toString()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Oh shit! IDK LOLZ. See logs").build();
		}
		
		//If no errors occurred then all should be well
		return Response.status(200).entity(String.format("The '%s' command has been run successfully", seq)).build();
	}
	
	@GET
	public Response comTest() {
		return Response.status(200).entity("This shit is working.").build();
	}
	
	@POST
	@Path("/lightOn")
	public Response turnLightOn() {
		IoContext.getInstance().getTestPin().setState(true);
		return Response.status(200).entity("Light on").build();
	}
	
	@POST
	@Path("/lightOff")
	public Response turnLightOff() {
		IoContext.getInstance().getTestPin().setState(false);
		return Response.status(200).entity("Light on").build();
	}
}
