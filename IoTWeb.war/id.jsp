<%@page import="com.gavs.iot.integration.print.HtmlImageGenerator"%>
<%@page import="javax.print.Doc"%>
<%@page import="javax.print.SimpleDoc"%>
<%@page import="javax.print.DocPrintJob"%>
<%@page import="javax.print.PrintService"%>
<%@page import="javax.print.DocFlavor"%>
<%@page import="javax.print.PrintServiceLookup"%>
<%@page import="javax.print.attribute.standard.OrientationRequested"%>
<%@page import="javax.print.attribute.standard.Copies"%>
<%@page import="javax.print.attribute.HashPrintRequestAttributeSet"%>
<%@page import="javax.print.attribute.PrintRequestAttributeSet"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>


Image Generator...

<%

InputStream is = null;
OutputStream os = null;
try {
    is = new FileInputStream(new File("D:/jboss-as-7.1.1.Final/standalone/deployments/IoT.war/images/idcard/templates/photo.png"));
    os = new FileOutputStream(new File("D:/jboss-as-7.1.1.Final/standalone/deployments/IoT.war/images/idcard/photo.png"));
    byte[] buffer = new byte[1024];
    int length;
    while ((length = is.read(buffer)) > 0) {
        os.write(buffer, 0, length);
    }

	String filename = "SUNDARAM_PRAKASH.png";
	
	HtmlImageGenerator imageGenerator = new HtmlImageGenerator(); 
	String html = "<div style=\"width:300px; border: 1px solid;\">"+
				  "<div id=\"header\" style=\"text-align:center;\">"+
				  "<img src=\"http://10.0.23.145:8080/images/idcard/templates/header.png\" height=\"120\" width=\"300\"/> "+
				  "</div><div id=\"main\" style=\"height: 322; text-align: center; padding-bottom: 20px; padding-top: 20px;\"> "+
				  "<img src=\"http://10.0.23.145:8080/images/idcard/photo.png\" height=\"217\" width=\"186\" /><br/><br/> "+
				  "<span style=\"font-size: 18px; text-transform: uppercase; padding-top: 10px; font-weight: bold; font-family: verdana;\">PRAKASH SUNDARAM</span><br/><br/> "+
				  "<span style=\"font-size: 26px; text-transform: uppercase; padding-top: 10px; font-weight: bold; font-family: verdana;\">GUEST</span> "+
				  "</div><div id=\"footer\"><img src=\"http://10.0.23.145:8080/images/idcard/templates/footer.png\" height=\"24\" /></div></div>";
	
	imageGenerator.loadHtml(html);
	imageGenerator.saveAsImage("D:/IoT/ID Card/cards/"+filename); 
	
	File file = new File("D:/jboss-as-7.1.1.Final/standalone/deployments/IoT.war/images/idcard/photo.png");
	out.println("---->"+file.exists());
	if(file.exists()){
		file.delete();
	}
	
	
	PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
    pras.add(new Copies(1));
    pras.add(OrientationRequested.PORTRAIT);


    
    PrintService pss[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.PNG, pras);
    if (pss.length == 0)
      throw new RuntimeException("No printer services available.");
    for(int i=0;i<pss.length;i++){
    	PrintService ps = pss[i]; //Microsoft XPS Document Writer
    	if(ps.getName().indexOf("Microsoft XPS Document Writer") != -1){
    		System.out.println("Printing to " + ps);
    	    DocPrintJob job = ps.createPrintJob();
    	    FileInputStream fin = new FileInputStream("D:/IoT/ID Card/cards/"+filename);
    	    Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.PNG, null);
    	    job.print(doc, pras);
    	    fin.close();
    	}
    }
	
	
}catch(Exception ex){
	System.out.println(ex);
} finally {
    is.close();
    os.close();
}
%>