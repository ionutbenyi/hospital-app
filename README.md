After cloning the repository, you should:    
1. Start the main server  
   + Run in your favorite IDE the spring-demo project  
   + Start with the “npm start” command in the directory of react-js-example the main web server for the front end    
2. Start the Doctor SOAP Web Service  
   + Run in the IDE the producer-soap project  
   + After the server is started, you can access the “patient status” section in the doctor page    
3. Additional – Keep track of a patient using its activities  
   + Set the user_id to the corresponding id of the user you need  
   + Run the rabbitmq-client server in your IDE  
   + Wait about a min for the ops to complete, then check the status of your patient in the doctor page from the front end    
4. Additional – Keep track of the medication status (taken or not taken)  
   + Run the rpc-client project in your IDE  
   + Run using the “npm start” the front end of the pillbox UI  
   + During a day, access your page to take medicines    
