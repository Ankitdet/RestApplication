package com.test.ws.datamanager.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.test.ws.datamanager.intrf.LoginDao;
import com.test.ws.exception.BusinessException;
import com.test.ws.exception.CommandException;
import com.test.ws.exception.InfrastructureException;
import com.test.ws.requestobject.LoginResponse;
import com.test.ws.utils.HibernateUtil;
import com.test.ws.utils.TokenGenerator;

public class LoginDaoImpl implements LoginDao {

	@Override
	public LoginResponse validateLogin(String email, String password) throws CommandException {

		Long user_id = 0l;
		List<Object[]> list = null;
		LoginResponse loginResponse = new LoginResponse();
		String queryString = "";
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			
			String token = TokenGenerator.uniqueUUID();
			
			queryString = "select * from users where email='" + email + "' and password='" + password + "'";
			Query query = session.createSQLQuery(queryString);
			list = query.list();

			if (!list.isEmpty()) {
				for (Object[] o : list) {
					user_id = ((BigInteger) o[0]).longValue();
				}
				
				queryString = "update users set auth_token='" + token + "',updated_at='"+getFormatedDate()+"' where id='" + user_id + "'";
				query = session.createSQLQuery(queryString);
				query.executeUpdate();
				
				queryString = "select username,uType,uTypeName from users where id='"+user_id+"'";
				query = session.createSQLQuery(queryString);
				list = query.list();
				
				if (!list.isEmpty()) {
					for (Object[] o : list) {
						loginResponse.setName(((String) o[0]).toString());
						loginResponse.setuType(((Integer) o[1]).toString());
						loginResponse.setuTypeName(((String) o[2]).toString());
						loginResponse.setuId(String.valueOf(user_id));
						loginResponse.setToken(token);
						loginResponse.setEmail(email);
					}
				}
			}else{
				return null;
			}
			tx.commit();
		} catch (InfrastructureException ex) {
			tx.rollback();
			throw new CommandException(ex);

		} catch (BusinessException ex) {
			throw new CommandException(ex);
		} finally {
			session.close();
		}
		return loginResponse;
	}
	
	private Timestamp getFormatedDate() {
		java.util.Date date = new java.util.Date();
		return new Timestamp(date.getTime());
	}

}
