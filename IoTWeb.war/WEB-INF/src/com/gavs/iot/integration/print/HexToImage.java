package com.gavs.iot.integration.print;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HexToImage {
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	
	  public static byte[] hexStringToByteArray(String s) {
		    byte[] b = new byte[s.length() / 2];
		    for (int i = 0; i < b.length; i++) {
		      int index = i * 2;
		      int v = Integer.parseInt(s.substring(index, index + 2), 16);
		      b[i] = (byte) v;
		    }
		    return b;
		  }
	  
	  public static void imageGen(byte[] b,String firstName) throws IOException{
		//converting byte array to image and storing it
	      
		ByteArrayInputStream bis = new ByteArrayInputStream(b);
	      BufferedImage bImage2 = ImageIO.read(bis);
	      ImageIO.write(bImage2, "png", new File("D:\\jboss-as-7.1.1.Final\\jboss-as-7.1.1.Final\\standalone\\deployments\\IoT.war\\images\\idcard\\temp\\"+firstName+".png") );
	      System.out.println("image created");
	  }
}
