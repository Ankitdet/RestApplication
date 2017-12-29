package com.test.ws.datamanager.intrf;

import com.test.ws.exception.CommandException;
import com.test.ws.requestobject.LoginResponse;

public interface LoginDao {
	public LoginResponse validateLogin(String email,String password) throws CommandException;
}
