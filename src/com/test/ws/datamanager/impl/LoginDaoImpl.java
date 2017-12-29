package com.test.ws.datamanager.impl;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.test.ws.datamanager.intrf.LoginDao;
import com.test.ws.exception.BusinessException;
import com.test.ws.exception.CommandException;
import com.test.ws.exception.InfrastructureException;
import com.test.ws.requestobject.LoginResponse;
import com.test.ws.utils.HibernateUtil;

public class LoginDaoImpl implements LoginDao {

	@Override
	public LoginResponse validateLogin(String email, String password) throws CommandException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		LoginResponse loginResponse = new LoginResponse();
		
		try {
			/*Query query = session
					.createSQLQuery("select * from users where email='" + email + "' and password='" + password + "'");
			List<Object[]> list = query.list();

			if (!list.isEmpty()) {
				Long user_id = 0l;
				
				for (Object[] o : list) {
					user_id = ((Long) o[0]).longValue();
				}
				String token = TokenGenerator.generateToken(email);
				query = session
						.createSQLQuery("update users set auth_token='" + token + "' where id='" + user_id + "'");
				query.executeUpdate();
				
			}*/
			
			loginResponse.setuId("1");
			loginResponse.setuType("1");
			loginResponse.setName("name");
			loginResponse.setuTypeName("XYZ");
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
