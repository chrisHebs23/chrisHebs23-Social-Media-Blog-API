package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;

    //Constructor
    public MessageService(){
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    // Mock data
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    /**
     * 
     * @return List of All Messages
     */
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    /**
     * 
     * @param message_id
     * @return Single Message
     */
    public Message getMessageById(int message_id){
        return messageDAO.getMessageById(message_id);
    }

    /**
     * 
     * @param user_id
     * @return List of Messges from a User
     */
    public List<Message> getAllUsersMessages(int user_id){
        return messageDAO.getAllUsersMessages(user_id);
    }

    /**
     * 
     * @param message
     * @return New Created Message
     */ 
    public Message createMessage(Message message){
        
        if(message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255 && accountDAO.geAccount(message.getPosted_by())){
            return messageDAO.createMessage(message);
        }
        return null;
    }


    /**
     * 
     * @param message_id
     * @param message
     * @return Updated Message
     */
    public Message updateMessageById(int message_id, Message message){

        if(message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255 && messageDAO.getMessageById(message_id) != null){
           
            return messageDAO.updateMessageById(message_id, message);
        }
        return null;
    }

    /**
     * 
     * @param message_id
     * @return Deleted Message 
     */
    public Message deleteMessageById(int message_id){
        if(messageDAO.getMessageById(message_id) != null){
            return messageDAO.deleteMessageById(message_id);
        }
        return null;
    }


}
