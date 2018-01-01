package com.test.ws.datamanager.intrf;

import java.util.List;

import com.test.ws.entities.Users;
import com.test.ws.exception.CommandException;

public interface LoginDao {
	public Users validateLogin(String email,String password) throws CommandException;

	public List<Users> getUserContactList() throws CommandException;
}
