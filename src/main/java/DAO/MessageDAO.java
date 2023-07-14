package DAO;

import Model.Message;
import Util.ConnectionUtil;

import static org.mockito.Mockito.never;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    // Get All Messages
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try {
        String sql = "SELECT * FROM message";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getInt("time_posted_epoch"));
            messages.add(message);
        }
            
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
        return messages;
    }
    
    // Get a message by id
    public Message getMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
      
        try {
        String sql = "SELECT * FROM message WHERE message_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,message_id);

        ResultSet rs = preparedStatement.executeQuery();
        
        while(rs.next()){
            Message message = new Message(
                rs.getInt("message_id"), 
                rs.getInt("posted_by"), 
                rs.getString("message_text"), 
                rs.getInt("time_posted_epoch"));
            return message;
        }
            
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
       
        return null;
    }

    // Get all of a users messages given user id.
    public List<Message> getAllUsersMessages(int user_id){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try {
        String sql = "SELECT * FROM message WHERE posted_by = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            Message message = new Message(
                rs.getInt("message_id"), 
                rs.getInt("posted_by"), 
                rs.getString("message_text"), 
                rs.getInt("time_posted_epoch")
                );
            messages.add(message);
        }
            
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
        return messages;
    }


    // Create a new message
    public Message createMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch ) VALUES (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setLong(1, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();

            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();

            if(pkeyResultSet.next()){
                int generate_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(
                    generate_message_id, 
                    message.getPosted_by(), 
                    message.getMessage_text(),
                    message.getTime_posted_epoch()
                    );
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Update a message with id

    // Delete a message given an id


    
}
