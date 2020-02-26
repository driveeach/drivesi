import java.io.*; 
import java.net.*;
import java.util.*;

class ThreadChat extends Thread {

	private int i;

	Socket connectionSocket;
	Map<String, DataOutputStream> userMap;
	String name;

	public ThreadChat(Socket s, Map<String, DataOutputStream> users){
		connectionSocket=s;
		userMap = users;
	}	

        public void run() {

		try{
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
			DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream()); 
			int time = 200;

			{
				int i = 0;
				while (!inFromClient.ready() && i < time){
					Thread.sleep(100);
					i++;
				}
				if (i >= time){
					 connectionSocket.close(); 
					return;
				} 
			        String sentence = inFromClient.readLine(); 
		  		System.out.println(sentence);
		  		String[] tokens = sentence.split(":");
		  		String message = "ERROR";  
		  		if ((tokens.length > 1) && tokens[0].equals("A")){
					message = "okA";
					name = tokens[1];
					userMap.put(tokens[1],outToClient);
				        outToClient.writeBytes(message+'\n'); 
				} else{
					connectionSocket.close(); 
					return;
				}
			
	  		}
		


			while(true){
				int i = 0;
				while (!inFromClient.ready() && i < time){
					Thread.sleep(100);
					i++;
				}
				if (i >= time){
					connectionSocket.close();
					userMap.remove(name);
					return;
				} 
		        	String sentence = inFromClient.readLine(); 
		  		System.out.println(sentence);
		  		String[] tokens = sentence.split(":");
		  		String message = "ERROR";  
		  		if (tokens.length > 0){
					if (tokens[0].equals("B")){
						message = "okB";
					} else if (tokens[0].equals("C")){
						message = "FIM";
						Set u = userMap.keySet();
						Object[] users = u.toArray();
						for (Object s: users){
							message = s.toString() + ":" + message;
						}
					} else if (tokens[0].equals("D")){
						if (tokens.length > 2){
							if (userMap.containsKey(tokens[1])){
								message = "okD";
								DataOutputStream saida = userMap.get(tokens[1]);
								saida.writeBytes(name + "::" + tokens[2]+'\n');
							}
						}
					}
				}
		
			        outToClient.writeBytes(message+'\n'); 
		  	}
		} catch (IOException e) {
    			System.err.println("Caught IOException: " + e.getMessage());
		} catch (InterruptedException e){
    			System.err.println("Caught InterruptedException: " + e.getMessage());
		}
  

	}


}
