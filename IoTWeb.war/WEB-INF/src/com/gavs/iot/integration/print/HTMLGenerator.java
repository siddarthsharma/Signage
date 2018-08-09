package com.gavs.iot.integration.print;

import com.gavs.iot.utils.ConstantsInterface;

public class HTMLGenerator implements ConstantsInterface {

	public static String generateClientHTML(String name,String from, String filename){
		
		String html = new String();
		if(name.length()>17){	
			html =	"<div style=\"background-image: url('"+ DOMAIN + TEMPLATE_DIR + HEADER_IMAGE +"'); background-repeat: no-repeat; width:230px; height:385px; \">"+
					  "<br><br><br><br><br><br><center>"+ 
					  		"<img src=\""+ DOMAIN + CARD_TEMP_DIR + "/"+filename+"\" height=\"217\" width=\"186\" border=\"1\"/>"+ 
					  		"<span style=\"font-size: 13px; font-weight: bold; text-transform: capitalize;font-family: calibri;\">"+name+"</span><br/><br/>"+ 
					  		"<span style=\"font-size: 13px; font-weight: bold; font-family: calibri;\">"+from+"</span>"+ 
					  "</center><br><br>"+
				  "</div>" ;
		}else{
			html =	"<div style=\"background-image: url('"+ DOMAIN + TEMPLATE_DIR + HEADER_IMAGE +"'); background-repeat: no-repeat; width:230px; height:385px; \">"+
					  "<br><br><br><br><br><br><center>"+ 
					  		"<img src=\""+ DOMAIN + CARD_TEMP_DIR + "/"+filename+"\" height=\"217\" width=\"186\" border=\"1\" /><br/><br/>"+ 
					  		"<span style=\"font-size: 14px; padding-top: 10px; font-weight: bold; text-transform: capitalize;font-family: calibri;\">"+name+"</span><br/><br/>"+ 
					  		"<span style=\"font-size: 14px;  font-weight: bold; font-family: calibri;\">"+from+"</span>"+ 
					  "</center><br><br>"+
				  "</div>" ;
		}
		
				
					  
		return html;
		
	}
	
	public static String generateGuestHTML(String name,String type, String filename){
		
		String html = new String();
		if(name.length()>17){	
			html =	"<div style=\"background-image: url('"+ DOMAIN + TEMPLATE_DIR + HEADER_IMAGE +"'); background-repeat: no-repeat; width:230px; height:385px; \">"+
					  "<br><br><br><br><br><br><center>"+ 
					  		"<img src=\""+ DOMAIN + CARD_TEMP_DIR + "/"+filename+"\" height=\"217\" width=\"186\" border=\"1\" /><br/><br/>"+ 
					  		"<span style=\"font-size: 13px; font-weight: bold; font-family: calibri;\">"+name+"</span><br/><br/>"+ 
					  		"<span style=\"font-size: 13px; font-weight: bold; font-family: calibri;\">"+type+"</span>"+ 
					  "</center><br><br>"+
				  "</div>" ;
		}else{
			html =	"<div style=\"background-image: url('"+ DOMAIN + TEMPLATE_DIR + HEADER_IMAGE +"'); background-repeat: no-repeat; width:230px; height:385px; \">"+
					  "<br><br><br><br><br><br><center>"+ 
					  		"<img src=\""+ DOMAIN + CARD_TEMP_DIR + "/"+filename+"\" height=\"217\" width=\"186\" border=\"1\" /><br/><br/>"+ 
					  		"<span style=\"font-size: 14px; padding-top: 10px; font-weight: bold; font-family: calibri;\">"+name+"</span><br/><br/>"+ 
					  		"<span style=\"font-size: 14px;  font-weight: bold; font-family: calibri;\">"+type+"</span>"+ 
					  "</center><br><br>"+
				  "</div>" ;
		}
		
				
					  
		return html;
		
	}
	
	public static String generateEmptHTML(String name,String emp_Id,String bloodGroup,String filename){
		
		String html = new String();
		if(name.length()>17){	
			html =	"<div style=\"background-image: url('"+ DOMAIN + TEMPLATE_DIR + HEADER_IMAGE +"'); background-repeat: no-repeat; width:230px; height:385px; \">"+
					  "<br><br><br><br><br><br><center>"+ 
					  		"<img src=\""+ DOMAIN + CARD_TEMP_DIR + "/"+filename+"\" height=\"217\" width=\"186\" border=\"1\" /><br/>"+ 
					  		"<span style=\"font-size: 16px; font-weight: bold;text-transform: capitalize; font-family: calibri;\">"+name+"</span><br/><br/>"+ 
					  		"<span style=\"font-size: 14px; font-weight: bold; font-family: calibri;\">"+emp_Id+"</span><br/><br/>"+ 
					  		"<span style=\"font-size: 14px; font-weight: bold;text-transform: capitalize; font-family: calibri;\">Blood Group: "+bloodGroup+"</span>"+
					  "</center><br><br>"+
				  "</div>" ;
		}else{
			html =	"<div style=\"background-image: url('"+ DOMAIN + TEMPLATE_DIR + HEADER_IMAGE +"'); background-repeat: no-repeat; width:230px; height:385px; \">"+
					  "<br><br><br><br><br><br><center>"+ 
					  		"<img src=\""+ DOMAIN + CARD_TEMP_DIR + "/"+filename+"\" height=\"217\" width=\"186\" border=\"1\"/><br/>"+ 
					  		"<span style=\"font-size: 16px; padding-top: 10px;text-transform: capitalize; font-weight: bold; font-family: calibri;\">"+name+"</span><br/><br/>"+ 
					  		"<span style=\"font-size: 14px;  font-weight: bold; font-family: calibri;\">"+emp_Id+"</span><br/><br/>"+ 
					  		"<span style=\"font-size: 14px;  font-weight:bold;text-transform: capitalize; font-family: calibri;\">Blood Group: "+bloodGroup+"</span>"+
					  "</center><br><br>"+
				  "</div>" ;
		}
		
				
					  
		return html;
		
	}
	

}
