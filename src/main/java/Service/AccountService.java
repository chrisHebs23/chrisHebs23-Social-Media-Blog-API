package Service;

import Model.Account;
import DAO.AccountDAO;



public class AccountService {
    AccountDAO accountDAO;

    // Constructor 
    public AccountService() {
        accountDAO = new AccountDAO();
    }

    // Used for mock accountDAO for testing
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    
    // Add a new AccountDAO to the database
    public Account registerUser(Account account){
    
        if(accountDAO.getUserByUsername(account.getUsername()) == null && account.getPassword().length() >= 4 && account.getUsername() != "" ){
    
            return this.accountDAO.register(account);
        }
        return null;
    }

    public Account loginUser(Account account){
       
        return accountDAO.login(account);

    }

}