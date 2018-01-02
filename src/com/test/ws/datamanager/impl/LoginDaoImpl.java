package com.test.ws.datamanager.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.ior.ObjectKey;
import com.test.ws.requestobject.LoginResponse;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.test.ws.datamanager.intrf.LoginDao;
import com.test.ws.entities.Users;
import com.test.ws.exception.BusinessException;
import com.test.ws.exception.CommandException;
import com.test.ws.exception.InfrastructureException;
import com.test.ws.utils.HibernateUtil;
import com.test.ws.utils.TokenGenerator;

public class LoginDaoImpl implements LoginDao {

    @Override
    public LoginResponse validateLogin(String email, String password) throws CommandException {

        Long user_id = 0l;
        List<Object[]> list = null;
        String queryString = "";
        Users user = new Users();
        LoginResponse loginResponse = new LoginResponse();

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

                queryString = "update users set auth_token='" + token + "',updated_at='" + getFormatedDate() + "' where id='" + user_id + "'";
                query = session.createSQLQuery(queryString);
                query.executeUpdate();

                queryString = "select u.first_name,u.middle_name,u.last_name,email,u.auth_token,ur.role_name,ur.id from users u left join user_roles ur on ur.id=u.role_id  where u.id='"+user_id+"'";
                query = session.createSQLQuery(queryString);
                List<Object[]> testuser = query.list();

                for(Object[] ob : testuser){
                    loginResponse.setFirstName((String)ob[0]);
                    loginResponse.setMiddleName((String)ob[1]);
                    loginResponse.setLastName((String)ob[2]);
                    loginResponse.setEmail((String)ob[3]);
                    loginResponse.setToken((String)ob[4]);
                    loginResponse.setuTypeName((String)ob[5]);
                    loginResponse.setuType(String.valueOf((Integer)ob[6]));
                    loginResponse.setuId(String.valueOf(user_id));
                }
            } else {
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

    @Override
    public List<Users> getUserContactList() throws CommandException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Users> list = null;

        try {
            Criteria crt = session.createCriteria(Users.class);
            list = (List<Users>) crt.list();
            System.out.println("Login response Data : " + list);

        } catch (BusinessException be) {

        } catch (Exception e) {

        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<Users> getBirthday(String cakeId) throws CommandException {

        String queryString = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Users> list = new ArrayList<Users>();

        /**
         *  0 = today's
         *  1 =  Week
         *  2 = 1 Months
         *  3 = 3 Months
         *  4 = 6 months
         *  5 = 1 year
         */
        try{
            Long myBirthdayDigit = Long.parseLong(cakeId);
            if(myBirthdayDigit == 0){
                queryString = "select * from users WHERE birth_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 1 day)";
            }else if(myBirthdayDigit == 1){
                queryString = "select * from users WHERE birth_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 1 WEEK)";
            }else if(myBirthdayDigit == 2){
                queryString = "select * from users WHERE birth_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 1 MONTH)";
            }else if(myBirthdayDigit == 3){
                queryString = "select * from users WHERE birth_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 3 MONTH)";
            }else if(myBirthdayDigit == 4){
                queryString = "select * from users WHERE birth_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 6 MONTH)";
            }else if(myBirthdayDigit == 5){
                queryString = "select * from users WHERE birth_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 12 MONTH)";
            }
            Query query = session.createSQLQuery(queryString);
            list = query.list();

        }catch (Exception pe){

        }


        return null;
    }
}
