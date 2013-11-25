package com.antilope.openutils.protocol.scoket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author         :    xushiheng
 * @date           :    
 * @version        :    1.0 20120307 
 * @since          :    1.0 20120307
 *
 */
public class ScoketServer {
	
	public ScoketServer(int port) {
		// TODO Auto-generated constructor stub
	}
	
	public void listen(){
		
	}
	
	public void pushResponse(String response){
		
	}
	
	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(8892);
			Socket clientSocket = server.accept();
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
			
			while(true){
				 String receive = reader.readLine();
				 System.out.println(receive);
				 
				 String responer = "0000";
				 writer.println(responer);
				 writer.flush();
				 
				 if(receive.equals("end")){
					 break;
				 }
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
