import java.io.*;
import java.net.*;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.URL;
public class Problem3 {
	public static void main(String args[]){ 
		Scanner terminalScan=new Scanner(System.in);
		String nextWord = terminalScan.next();
		terminalScan.close();
		String myGetUrl = "https://www.cse.iitb.ac.in/~safeer/get_hash.php?input="+nextWord;
		URL getUrl = null;
		try {
			getUrl = new URL(myGetUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			HttpURLConnection getConnection = (HttpURLConnection) getUrl.openConnection();
			getConnection.setRequestMethod("GET");
			BufferedReader getInput = new BufferedReader(new InputStreamReader(getConnection.getInputStream()));
			String line = null;
			System.out.println("Get Method:");
			while ((line = getInput.readLine()) != null) {
		    	System.out.println(line);
		    } 
			getInput.close();
			getConnection.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String myPostUrl = "https://www.cse.iitb.ac.in/~safeer/post_hash.php";//?input="+nextWord;
		URL postUrl = null;
		try {
			postUrl = new URL(myPostUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		    HttpURLConnection postConnection = (HttpURLConnection)postUrl.openConnection();
		    postConnection.setRequestMethod("POST");
		    postConnection.setDoOutput(true);
		    PrintStream ps = new PrintStream(postConnection.getOutputStream());
		    ps.print("input="+nextWord);
		    BufferedReader postInput = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
		        System.out.println("Post Method:");
		        String line;
		        while ((line = postInput.readLine()) != null) {
		         System.out.println(line);
		       }
		    ps.close();
		    postConnection.disconnect();
		    } catch (MalformedURLException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		
	}
}