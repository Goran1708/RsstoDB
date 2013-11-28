package XML;

import java.io.*;
import java.net.*;
 
public class GrabXML{
	


	public static void main(String[] args) throws Exception{
		
		System.out.println(newsName("http://www.index.hr/"));
		System.out.println(grabLink("http://www.index.hr/"));
	}
	
	public static String newsName(String urlAddress) {
		try{
		   //Set URL
		   URL url = new URL(urlAddress);
		   URLConnection spoof = url.openConnection();
		 
		   //Spoof the connection so we look like a web browser
		   spoof.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)" );
		   BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
		   String strLine = "";
		   String line;
		 
		   //Loop through every line in the source
		   while ((line = in.readLine()) != null){
			   if(line.contains("fpbox fpnajnovije"))
				   break;
			   else if (line.contains("alt=\"") && !line.contains("alt=\"Index.hr") && !line.contains("alt=\"\"")){
				   int firstPos = line.indexOf("alt=\"");
				   String temp = line.substring(firstPos);
				   temp = temp.replace("alt=\"","");
				   int lastPos = temp.indexOf("\"");
				   temp = temp.substring(0, lastPos);
				   strLine += temp + "\n";
			   }
		   }
		   in.close();
		   return strLine;
		} catch (MalformedURLException ue){
			System.out.print("Malformed URL");
		} catch (IOException ioe){
			System.out.println("Something went wrong reading the contents");
		}
		return null;
	}
	
	public static String grabLink(String urlAddress) {
		try{
		   //Set URL
		   URL url = new URL(urlAddress);
		   URLConnection spoof = url.openConnection();
		 
		   //Spoof the connection so we look like a web browser
		   spoof.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)" );
		   BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
		   String strLink = "";
		   String line;
		 
		   //Loop through every line in the source
		   while ((line = in.readLine()) != null){
			   if(line.contains("fpbox fpnajnovije"))
				   break;
			   else if (line.contains("/clanak/") 
					   && !line.contains("data-href='http://www.index.hr/vijesti/clanak") 
					   && !line.contains("data-href='http://www.index.hr/sport/clanak") 
					   && !line.contains("data-href='http://www.index.hr/black/clanak")){
				   int firstPos = line.indexOf("/clanak/");
				   String tem = line.substring(firstPos);
				   tem = tem.replace("/clanak/","");
				   int lastPos = tem.indexOf("\"");
				   tem = tem.substring(0, lastPos);
				   strLink += tem + "\n";
			   }
		   }
		   in.close();
		   return strLink;
		} catch (MalformedURLException ue){
			System.out.print("Malformed URL");
		} catch (IOException ioe){
			System.out.println("Something went wrong reading the contents");
		}
		return null;
	}
}