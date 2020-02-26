import java.io.*; 
import java.net.*;
import java.util.*;
  
class ChatServer { 
  public static void main(String args[]) throws Exception 
    { 
      ServerSocket welcomeSocket = new ServerSocket(2020); 
      Map<String, DataOutputStream> m = new HashMap<String, DataOutputStream>();
  
      while(true) { 
  
            Socket connectionSocket = welcomeSocket.accept();
	    ThreadChat t = new ThreadChat(connectionSocket,m);
	    t.start();
      }
    } 
}  
