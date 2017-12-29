package com.test.ws.services.rest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.test.ws.constant.ResultCode;
import com.test.ws.exception.CommandException;
import com.test.ws.logger.Logger;
import com.test.ws.requestobject.Response;
import com.test.ws.service.impl.LoginServiceImpl;

@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class RestServices {

	private static final String MODULE = "RestServices";

	@POST
	@Path("/methodName")
	public Response getAllPerson(Response personRequest) {
		Response response = null;
		Logger.logDebug(MODULE, "Called" + getMethodName() + " with Request: " + personRequest);
		try {
			/**
			 * Write your logic
			 */
			System.out.println("Person Sting:" + personRequest.toString());

		} catch (NumberFormatException e) {
			Logger.logError(MODULE, "Error While creating Subscriber : " + e.getMessage());
			Logger.logTrace(MODULE, e);
		}
		return response;
	}

	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	/*
	 * @GET
	 * 
	 * @Path("/helloMyData") public Response helloMyData(String request) throws
	 * java.text.ParseException { Response data = null; //
	 * Logger.logInfo(MODULE, //
	 * "Method called wsCheckInAccount() with Request: "+request); try { Person
	 * p = new Person(); p.setName("Krutika"); p.setLastName("Detroja");
	 * 
	 * List<Person> list = new ArrayList<Person>(); list.add(p);
	 * 
	 * data = new Response(200, "first Application", "Simple Data", null,
	 * "Failed to get Data!!", list);
	 * 
	 * } catch (NumberFormatException e) {
	 * 
	 * Logger.logError(MODULE,"Error While checkIn Subscriber operation: "
	 * +e.getMessage()); Logger.logTrace(MODULE,e);
	 * 
	 * } catch (ParseException e) {
	 * 
	 * Logger.logError(MODULE,"Error While checkIn Subscriber operation: "
	 * +e.getMessage()); Logger.logTrace(MODULE,e); } return data; }
	 */

	@POST
	@Path("/login")
	public Response login(@QueryParam("email") String email,@QueryParam("password") String password) throws NumberFormatException, ParseException, CommandException {
		LoginServiceImpl blManager = new LoginServiceImpl();
		Response response = null;
		Logger.logInfo(MODULE, "Method called login() with Request: " + email + "and " + password);

		try {
			if (email == null || email.trim() == "") {
				return new Response(ResultCode.INPUT_PARAMETER_MISSING_401.code, "email must not blank",null, null, null);
			}
			if (password == null || password.trim() == "") {
				return new Response(ResultCode.INPUT_PARAMETER_MISSING_401.code,
						"password must not blank",null, null, null);
			}
			
			response = blManager.validateLogin(email,password);
			
		} catch (NumberFormatException e) {
			return new Response(ResultCode.INPUT_PARAMETER_MISSING_401.code, ResultCode.INPUT_PARAMETER_MISSING_401.name, null, e.getMessage(), null);
		} catch (ParseException e) {
			return new Response(ResultCode.INPUT_PARAMETER_MISSING_401.code, ResultCode.INPUT_PARAMETER_MISSING_401.name, null, e.getMessage(), null);
		} catch (CommandException e) {
			return new Response(ResultCode.INPUT_PARAMETER_MISSING_401.code, ResultCode.INPUT_PARAMETER_MISSING_401.name, null, e.getMessage(), null);
		}
		return response;
	}
}
