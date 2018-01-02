package com.test.ws.datamanager.intrf;

import java.util.List;

import com.test.ws.entities.Users;
import com.test.ws.exception.CommandException;
import com.test.ws.requestobject.LoginResponse;

public interface LoginDao {
	public LoginResponse validateLogin(String email, String password) throws CommandException;

	public List<Users> getUserContactList() throws CommandException;

	public List<Users> getBirthday(String cakeId) throws CommandException;
}
