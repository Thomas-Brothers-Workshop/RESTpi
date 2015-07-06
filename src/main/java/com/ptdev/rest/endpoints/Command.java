package com.ptdev.rest.endpoints;

import java.io.FileNotFoundException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.esotericsoftware.yamlbeans.YamlException;
import com.pi4j.io.gpio.GpioFactory;
import com.ptdev.exceptions.ConfigDirectoryException;
import com.ptdev.exceptions.InvalidConfigSetupException;
import com.ptdev.picore.io.Mcp23017;
import com.ptdev.picore.io.Mcp23017.ByteAddress;
import com.ptdev.support.ConfigReader;

@Path("/cmd")
public class Command {
	//Fix chip class to contain all chips
	public Mcp23017 chipOne = new Mcp23017(GpioFactory.getInstance(), ByteAddress.ONE, "one").setAllPinsOutput();
	public Mcp23017 chipTwo = new Mcp23017(GpioFactory.getInstance(), ByteAddress.TWO, "one").setAllPinsOutput();
	
	@POST
	@Path("/{sequence}")
	public Response sayHello( @PathParam("sequence") String seq) {
		//Run sequence based on name
		try {
			new ConfigReader(seq, chipOne).getSequence().start();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (YamlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigSetupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigDirectoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(200).entity(String.format("The '%s' command has been run successfully", seq)).build();
	}
}
