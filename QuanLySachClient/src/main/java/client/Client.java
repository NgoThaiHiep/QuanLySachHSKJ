package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		
		try(Socket socket = new Socket("DESKTOP-C3QB7HM", 25);
				Scanner sc = new Scanner(System.in);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				
				){
            int choice = 0;
            
            
           while(true) {
        	   System.out.println("******MENU*******: \n"
						+ "1. \n"
						+ "2. \n"
						+ "3. \n"
						+ "4. \n"
						+ "5. \n"
						+ "6. \n"
						+ "7. \n"
						+ "8. \n"
						+ "0. Exit");
				 System.out.print("Enter your choice: ");
				 choice = sc.nextInt();
					out.writeInt(choice);
					out.flush();
					switch (choice) {
					case 1:
						// code
						break;
					case 0:
	                    System.out.println("Exiting...");
	                    break;
	                    
	                default:
	                    System.out.println("Invalid choice. Please try again!");
					}
           }
          
            
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
