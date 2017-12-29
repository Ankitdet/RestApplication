package com.test.ws.service.impl;

import java.text.ParseException;

import com.test.ws.datamanager.impl.LoginDaoImpl;
import com.test.ws.exception.BusinessException;
import com.test.ws.exception.CommandException;
import com.test.ws.exception.InfrastructureException;
import com.test.ws.logger.Logger;
import com.test.ws.requestobject.LoginResponse;
import com.test.ws.requestobject.Response;
import com.test.ws.service.intrf.LoginService;

public class LoginServiceImpl implements LoginService {

	
	@Override
	public Response validateLogin(String email,String password) throws CommandException, ParseException {
		
		Response response = null;
		LoginResponse loginResponse = null;
		LoginDaoImpl loginDao = new LoginDaoImpl();
		Logger.logDebug("Test", "Enter into create method of ");
		
		try {
			 loginResponse = loginDao.validateLogin(email,password);

		} catch (InfrastructureException ex) {
			// Rethrow as a checked exception
			//HibernateUtil.rollbackTransaction();
			throw new CommandException(ex);

		} catch (BusinessException ex) {
			// Rethrow as a checked exception
			throw new CommandException(ex);
		} finally {
			
		}
		return new Response(200, "Login successfully", null, null,loginResponse);
	}
}
