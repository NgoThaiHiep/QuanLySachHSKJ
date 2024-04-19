
package ServiceUser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
 
public class ServiceSMS {
	public static void sendSMS (String sdt,String code) {
  try {
   
   String apiKey = "apiKey=" + "NzY1YTc1NTg3OTRjNGE1NDcxNjEzNjQ2MzU2MTRjMzQ=";
   
   String message = "&message=" + URLEncoder.encode("Your OTP is " + code, "UTF-8");
   
   String numbers = "&numbers=" + sdt;
   
   String apiURL = "https://api.textlocal.in/send/?" + apiKey + message + numbers;
   
   URL url = new URL(apiURL);
   URLConnection connection = url.openConnection();
   connection.setDoOutput(true);
   
   BufferedReader reader = new BufferedReader(new 
     InputStreamReader(connection.getInputStream()));
   
   String line = "";
   StringBuilder sb = new StringBuilder();
   
   while ( (line = reader.readLine()) != null) {
    sb.append(line).append("\n");
   }
   
   System.out.println(sb.toString());
   
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
}