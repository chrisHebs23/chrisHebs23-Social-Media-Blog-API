package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import java.util.List;



/**
 *  will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postUserHandler);
        app.post("/login", this::postLoginHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.get("/accounts/{user_id}/messages", this::getUserMessages);

        return app;
    }

    // Account controllers
    private void postUserHandler(Context ctx) throws JsonProcessingException {
        
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account registeredUser = accountService.registerUser(account);
      
        if(registeredUser != null){
            ctx.json(mapper.writeValueAsString(registeredUser));
        } else{

            ctx.status(400);
        }
    } 

    private void postLoginHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account loggedinUser = accountService.loginUser(account);

        System.out.println(loggedinUser);

        if(loggedinUser != null){
            ctx.json(mapper.writeValueAsString(loggedinUser));
        
        }else{
            ctx.status(401);
        }

    }

    // Messages Controllers
    private void getAllMessagesHandler(Context ctx) throws JsonProcessingException{
        ctx.json(messageService.getAllMessages());
    }

    private void getMessageByIdHandler(Context ctx) throws JsonProcessingException{
       Message message =messageService.getMessageById(Integer.parseInt(ctx.pathParam("message_id")));
       if(message != null){
          ctx.json(message);
       } else {
        ctx.status(200);
       }
    }

    private void getUserMessages(Context ctx) throws JsonProcessingException{
       List<Message> message = messageService.getAllUsersMessages(Integer.parseInt(ctx.pathParam("user_id")));
       if(message != null){
        
          ctx.json(message);
       } else {
        ctx.status(200);
       }
       
    }

 
 




}