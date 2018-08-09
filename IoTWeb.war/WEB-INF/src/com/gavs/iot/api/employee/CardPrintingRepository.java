package com.gavs.iot.api.employee;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.gavs.iot.integration.print.Capitalize;
import com.gavs.iot.integration.print.FormatNameUtil;
import com.gavs.iot.integration.print.HTMLGenerator;
//import com.gavs.iot.integration.print.HexToImage;
import com.gavs.iot.integration.print.HtmlImageGenerator;
import com.gavs.iot.integration.print.PrinterService;
import com.gavs.iot.utils.ConstantsInterface;
import org.json.JSONObject;
import org.apache.commons.lang.WordUtils;

@Path("print")
public class CardPrintingRepository implements ConstantsInterface {

	
	
	private static Log log = LogFactory.getLog(CardPrintingRepository.class);
	
//	public static List<String> ips = new ArrayList<String>();
//	static {
//		ips.add("10.0.23.145");
//		ips.add("10.0.8.138");
//		ips.add("10.0.23.167");
//		
//	}
	
	

	@PermitAll
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)

	@Path("card")
	public Response printCard(String data){

		Response returnResponse = null;
		boolean requestHasErrors = false;
		String message = new String();
		String html = "<meta id=\"meta\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" /><br><center><h5>MESSAGE</h5></center>";
		
		System.out.println("Reached - /print/card");
		
		try{
			JSONObject json = new JSONObject(data);
//			if(!ips.contains(req.getRemoteAddr())){
//				requestHasErrors = true;
//				message = "Unauthorized access";
//				if(log.isDebugEnabled())
//					log.debug("Unauthorized access");
//			}else 
			if(data == null) {
				requestHasErrors = true;
				message = "Request has errors";
				if(log.isDebugEnabled())
					log.debug("Request has errors");
			}else{
				String type = json.get("type").toString();
				
				String from="";
				String contact_No = "";
				String purpose = "";
				String bloodGroup="";
			
				String firstName = json.get("first_Name").toString();
				String lastName =  json.get("last_Name").toString();
				
				
				String emp_Id="";
				String hexImage = json.get("photo").toString();
				
				
				if(type.equalsIgnoreCase("New Employee")){
					emp_Id = json.get("emp_Id").toString();
					bloodGroup = json.get("blood_Group").toString();
					
				}
				else if(type.equalsIgnoreCase("Client")){
					
					from = json.get("organization").toString();
					//contact_No = json.get("contact_No").toString();
					//purpose = json.get("purpose").toString();
				}
				else{
					
					from = json.get("from").toString();
					contact_No = json.get("contact_No").toString();
					purpose = json.get("purpose").toString();
					
				}
				
				Capitalize cap = new Capitalize();
				firstName = cap.toTitleCase(firstName);
				lastName = cap.toTitleCase(lastName);
				from = cap.toTitleCase(from);
				purpose = cap.toTitleCase(purpose);
				bloodGroup = cap.toTitleCase(bloodGroup);
				type = cap.toTitleCase(type);
				
				String name = null;
				
				if((firstName != null && !firstName.isEmpty()) || (lastName != null && !lastName.isEmpty())){
					name = firstName +" "+ lastName;
				}
				
				
				int k=0;
				String blood ="";
				char bg [] = bloodGroup.toCharArray(); 
				if(bloodGroup!=null){
					
					while(k<bloodGroup.length()-1){
						if(bloodGroup.charAt(k)=='+' || bloodGroup.charAt(k)=='-')
							break;
						else
							k++;
					}
				
					
					if(k == bloodGroup.length()-1){
						blood=bloodGroup+"ve";
					}
					else
						blood=bloodGroup;
					
						
				}
				
				
					
					System.out.println(name);
					System.out.println(from);
					System.out.println(contact_No);
					System.out.println(purpose);
					System.out.println(blood);
					System.out.println(emp_Id);
					
					
					
					//converting hexString to byte array
				byte[] b = new byte[hexImage.length() / 2];
				    for (int i = 0; i < b.length; i++) {
				      int index = i * 2;
				      int v = Integer.parseInt(hexImage.substring(index, index + 2), 16);
				      b[i] = (byte) v;
				    }
					
				    //converting byte array to image and storing it
				      ByteArrayInputStream bis = new ByteArrayInputStream(b);
				      BufferedImage bImage2 = ImageIO.read(bis);
				      ImageIO.write(bImage2, "png", new File("D:\\jboss-as-7.1.1.Final\\jboss-as-7.1.1.Final\\standalone\\deployments\\IoT.war\\images\\idcard\\temp\\"+firstName+".png") );
				      System.out.println("image created");
					
					       
					     String filename = firstName+".png";
				    
						
					
					
					    HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
					    
					    if(type.equalsIgnoreCase("New Employee")){
					    
					     imageGenerator.loadHtml(HTMLGenerator.generateEmptHTML(name,emp_Id,blood,filename));
						 imageGenerator.saveAsImage(PRINTED_CARDS_DIR+"/"+name+".png");
					     
					    }
					    else if(type.equalsIgnoreCase("Client")){
					    	imageGenerator.loadHtml(HTMLGenerator.generateClientHTML(name, from, filename));
							imageGenerator.saveAsImage(PRINTED_CARDS_DIR+"/"+name+".png");
					    }
					    else{
					    	imageGenerator.loadHtml(HTMLGenerator.generateGuestHTML(name, type ,filename));
							imageGenerator.saveAsImage(PRINTED_CARDS_DIR+"/"+name+".png");
					    }
					 
					    
					  
						
						//File file = new File(ROOT_DIR + CARD_TEMP_DIR+"/"+filename);
						
						//if(file.exists()){
							//file.delete();
						//}
							
					    //String printer = PrinterService.print(name+".png");
//						String link = "<br/><br/><a href=\"/printcard.jsp?type="+type+"\">Print another card</a>";	
						returnResponse = Response.status(Response.Status.OK).entity(html.replace("MESSAGE", "Request sent to printer. <br/><br/>Please collect your card from front desk.")).build();
					    
					}
				
			
			
			if(requestHasErrors){
				returnResponse = Response.status(Response.Status.BAD_REQUEST).entity(html.replace("MESSAGE", message)).build();
				
			}
		
			
			
        }catch(Exception e){
        	log.error(e.getMessage(), e);
        	returnResponse = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(html.replace("MESSAGE", MSG_INTERNAL_SERVER_ERROR)).build();
        }
		
		return returnResponse;
	

}
}
