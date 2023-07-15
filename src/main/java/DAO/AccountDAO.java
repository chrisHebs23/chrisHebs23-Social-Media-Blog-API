package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;



public class AccountDAO {

/**
 * 
 * @param account
 * @return New Account
 */
public Account register(Account account){
    Connection connection = ConnectionUtil.getConnection();


    try {
        String sql = "INSERT INTO account (username, password) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, account.getUsername());
        preparedStatement.setString(2, account.getPassword());
        preparedStatement.executeUpdate();
        ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
        if(pkeyResultSet.next()){
            int generate_user_id = (int) pkeyResultSet.getLong(1);
            return new Account(generate_user_id, account.getUsername(), account.getPassword());
        }
    }catch (SQLException e) {
       System.out.println(e.getMessage());
    }


    return null;
}

/**
 * 
 * @param username
 * @return Returns an Account if usrename is found
 */
public Account getUserByUsername(String username){
    Connection connection = ConnectionUtil.getConnection();

    try{ 
        String sql = "SELECT * FROM account WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);

        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            Account account = new Account(
                rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password")
                );
            return account;
        }

    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
  
}

/**
 * 
 * @param account
 * @return Logged in Account
 */
public Account login(Account account){
    Connection connection = ConnectionUtil.getConnection();

    try{ 
        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, account.getUsername());
        preparedStatement.setString(2, account.getPassword());

        ResultSet rs = preparedStatement.executeQuery();
        

        while(rs.next()){
            Account loginAccount = new Account(
                rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password")
                );
            return loginAccount;
        }

    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;

}

/**
 * for seeing if user exists in creating messages
 * @param user_id
 * @return Boolean so important data is return
 */
public Boolean geAccount(int user_id){
    Connection connection = ConnectionUtil.getConnection();

    try{ 
        String sql = "SELECT * FROM account WHERE account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, user_id);


        ResultSet rs = preparedStatement.executeQuery();
        

        return rs.next();
        

    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return false;

} 

}
