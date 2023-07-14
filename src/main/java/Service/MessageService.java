package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;

    // Constructor 
    public MessageService(){
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    // Mock data
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    // get all messages
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    // Get message by Id
    public Message getMessageById(int message_id){
        return messageDAO.getMessageById(message_id);
    }

    // Get users messages by user_id
    public List<Message> getAllUsersMessages(int user_id){
        return messageDAO.getAllUsersMessages(user_id);
    }

    // Create message 
    public Message createMessage(Message message){
        System.out.println("hit");
        System.out.println(message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255 && accountDAO.geAccount(message.getPosted_by())!= null);
        if(message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255 && accountDAO.geAccount(message.getPosted_by())!= null){
            return messageDAO.createMessage(message);
        }
        return null;
    }
}
