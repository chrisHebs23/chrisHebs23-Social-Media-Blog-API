package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;

    // Constructor 
    public MessageService(){
        messageDAO = new MessageDAO();
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
    public Message getMessageById(int id){
        return messageDAO.getMessageById(id);
    }
}
