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

@Path("email")
public class Test implements ConstantsInterface {

	
	
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

	@Path("sid")
	public Response printCard(String data){

		Response returnResponse = null;
		boolean requestHasErrors = false;
		String message = new String();
		String html = "<meta id=\"meta\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" /><br><center><h5>MESSAGE</h5></center>";
		
		System.out.println("Reached - /email/sid");
		
		try{
			
			if(data == null) {
				requestHasErrors = true;
				message = "Request has errors";
				if(log.isDebugEnabled())
					log.debug("Request has errors");
			}else{
				
						
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
