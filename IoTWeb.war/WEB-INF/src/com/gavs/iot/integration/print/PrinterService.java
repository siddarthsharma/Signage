package com.gavs.iot.integration.print;

import java.io.FileInputStream;



import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import com.gavs.iot.utils.ConstantsInterface;

public class PrinterService implements ConstantsInterface {

	public static String print(String filename) throws Exception {
		
		PrintService pss[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.PNG, null);
		String printer = new String();
		if (pss.length == 0){
		     throw new RuntimeException("No printer services available.");
		}
		System.out.println("Available Printers:");
		for(int i=0;i<pss.length;i++){
			PrintService ps = pss[i]; //Check for C50 Card Printer
			System.out.println(ps);
			if(ps.getName().indexOf("C50 Card Printer") != -1){
				System.out.println("Printing "+ filename +" to " + ps);
				printer = ps.getName();
				DocPrintJob job = ps.createPrintJob();
				FileInputStream fin = new FileInputStream(PRINTED_CARDS_DIR + "\\"+filename);
				Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.PNG, null);
				job.print(doc, getRequestAttributeSet()); 
				fin.close();
				printAddress(ps);
				break;
			}
		}
		return printer;
	}

	public static void printAddress(PrintService ps) throws Exception {
		DocPrintJob job = ps.createPrintJob();
		FileInputStream fin = new FileInputStream(ROOT_DIR + TEMPLATE_DIR + ADDRESS_IMAGE);
		//FileInputStream fin = new FileInputStream(DOMAIN + TEMPLATE_DIR + ADDRESS_IMAGE);
		Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.JPEG, null);
		job.print(doc, getRequestAttributeSet()); 
		fin.close();
	}
	
	public static PrintRequestAttributeSet getRequestAttributeSet(){
		PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
		attr.add(new Copies(1));
		attr.add(MediaSizeName.ISO_A4);
		attr.add(OrientationRequested.PORTRAIT);
   
		int width = Math.round(MediaSize.ISO.A4.getX(MediaSize.MM));
		int height = Math.round(MediaSize.ISO.A4.getY(MediaSize.MM));
   
		attr.add(new MediaPrintableArea(0, 0, width, height, MediaPrintableArea.MM));
   
		//attr.add(new MediaPrintableArea(0.0f, 0.0f, 53.679f, 85.598f, MediaPrintableArea.MM));
		return attr;
	}
}
