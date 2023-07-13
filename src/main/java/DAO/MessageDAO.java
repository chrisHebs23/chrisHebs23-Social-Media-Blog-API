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

    // Get all of a users messages given user id.

    // Create a new message

    // Update a message with id

    // Delete a message given an id


    
}
