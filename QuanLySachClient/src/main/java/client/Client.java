package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		
		try(Socket socket = new Socket("DESKTOP-MTLRHL1", 25);
				Scanner sc = new Scanner(System.in);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				){
            System.out.println("Connected to server...");
            
           while(true) {
        	   System.out.println("Enter the MNV: ");
               String mnv = sc.nextLine();
               
               out.writeUTF(mnv);
               out.flush();
               
//               Receive response form server
				String response = in.readUTF();
				System.out.println("Response: " + response);
           }
          
            
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
