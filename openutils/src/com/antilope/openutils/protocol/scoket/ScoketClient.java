package com.antilope.openutils.protocol.scoket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ScoketClient {
	
	public static void main(String[] args) {
		
		try {
			Socket client = new Socket("127.0.0.1",8892);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter writer = new PrintWriter(client.getOutputStream());
			
			writer.println("end");
			writer.flush();
			System.out.print(reader.readLine());
			
			client.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
