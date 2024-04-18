package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;


import DAO_IMP.NhanVienDAO_IMP;
import entity.NhanVien;


public class Server {
	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket(25)
				
				){
            System.out.println("Server is running...");
          
			while (true) {
				
				Socket socket = server.accept();
				System.out.println("Connected to client: " + socket.getInetAddress().getHostName());
				Server temp = new Server();
				
				Thread t = new Thread(temp.new Handler(socket));
				t.start();
			}
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

	private class Handler implements Runnable {
		private  Socket socket;
		private NhanVienDAO_IMP nhanVien_DAO_IMP; 
		private static final Gson GSON = new Gson(); 
		
		public Handler(Socket socket) {
                        
			this.socket = socket;
			this.nhanVien_DAO_IMP = new NhanVienDAO_IMP();
		}

		@Override
		public void run() {
			try(
					DataInputStream in = new DataInputStream(socket.getInputStream());
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					){
				while(true) {
                   String mnv = in.readUTF();
                   System.out.println("MNV: " + mnv);
                   
                   nhanVien_DAO_IMP = new NhanVienDAO_IMP();
                   NhanVien nv = nhanVien_DAO_IMP.timKiemNhanVienTheoMaNV(mnv);
                   
                   String json = GSON.toJson(nv);
                   System.out.println("Sending: "+json);
                   out.writeUTF(json);
                   out.flush();
                   
//                 
                   
                }
			}catch (Exception e) {
				// TODO: handle exception
				
				System.out.println("Client disconnected...");
			}
		}
	}
}
