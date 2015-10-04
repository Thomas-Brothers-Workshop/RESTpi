package com.ptdev.rest.endpoints;

import java.io.FileNotFoundException;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.esotericsoftware.yamlbeans.YamlException;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.ptdev.exceptions.ConfigDirectoryException;
import com.ptdev.exceptions.InvalidConfigSetupException;
import com.ptdev.picore.io.Mcp23017;
import com.ptdev.picore.io.Mcp23017.ByteAddress;
import com.ptdev.support.ConfigReader;

@Path("/cmd")
public class Command {
	//Fix chip class to contain all chips
	final GpioController gpio = GpioFactory.getInstance();
	public Mcp23017 chipOne;
	public Mcp23017 chipTwo; 
	public Map<Integer, Mcp23017> mcpMap;
	// provision gpio pin #01 as an output pin and turn on
    final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);

	public Command() {
		try {
			//Chips
			System.out.println("GPIO Info: " + gpio.toString());
			
			// set shutdown state for this pin
			pin.setShutdownOptions(true, PinState.LOW);
			
			chipOne = new Mcp23017(gpio, ByteAddress.ONE, "one").setAllPinsOutput();
			//chipTwo = new Mcp23017(GpioFactory.getInstance(), ByteAddress.TWO, "one").setAllPinsOutput();
			
			//Map
			mcpMap.put(1, chipOne);
//			mcpMap.put(2, chipTwo);
		} catch (NullPointerException e) {
			System.err.println("Looks like I couldn't find the IO expanders.");
			e.printStackTrace();
		}
	}
	
	@POST
	@Path("/{sequence}")
	public Response comResponse( @PathParam("sequence") String seq) {
		//Run sequence based on name
		try {
			new ConfigReader(seq, mcpMap).getSequence().start();
		} catch (FileNotFoundException e) {
			return Response.status(404).entity("One of the files was not found: Stack - " + e.toString()).build();
		} catch (YamlException e) {
			return Response.status(400).entity("Config file was not formatted right: Stack - " + e.toString()).build();
		} catch (InvalidConfigSetupException e) {
			return Response.status(400).entity("Config file was not setup right: Stack - " + e.toString()).build();
		} catch (ConfigDirectoryException e) {
			return Response.status(404).entity("Config file did not exist or was not set: Stack - " + e.toString()).build();
		} catch (Exception e) {
			return Response.status(500).entity("Oh shit! IDK LOLZ: Stack - " + e.toString()).build();
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
		pin.setState(true);
		return Response.status(200).entity("Light on").build();
	}
	
	@POST
	@Path("/lightOff")
	public Response turnLightOff() {
		pin.setState(false);
		return Response.status(200).entity("Light on").build();
	}
}
