package com.gavs.iot.utils;

public interface ConstantsInterface {

	//DOMAIN
	public static final String DOMAIN = "http://10.0.8.139:8081/gavs";
	
	//HTTP BASIC AUTH PROPERTIES
    public static final String AUTHORIZATION_PROPERTY = "Authorization";
    public static final String AUTHENTICATION_SCHEME_BASIC = "Basic";
    public static final String AUTHENTICATION_SCHEME_BEARER = "Bearer";
	
    //USER ROLES
    public static final String ROLE_VERIFIED_EMPLOYEE = "verifiedEmployee";
    public static final String ROLE_ADMIN = "admin";
    
    //ID OF ROLESET OBJECT IN HTTP REQUEST
  	public static final String ROLESET_OBJECT_ID = "rolesetObject";
  	
	//Error Messages
	public static final String ERROR_MOBILE_NOT_REGISTERED = "Mobile not registered";
	public static final String ERROR_EMPLOYEE_ALREADY_REGISTERED = "Employee already registered";
	public static final String ERROR_INACTIVE_ACCOUNT = "Account is not active";
	public static final String ERROR_EMPLOYEE_NOT_REGISTERED = "Invalid Employee Id or Id not registered";
	public static final String ERROR_INVALID_REQUEST = "Invalid request";
	public static final String ERROR_INVALID_EMAIL = "Please enter a valid GAVSTECH email";
	public static final String ERROR_SENDING_ACTIVATION_EMAIL = "Error in sending activation email";
	
	public static final String MSG_BAD_REQUEST = "Bad Request";
	public static final String MSG_INTERNAL_SERVER_ERROR = "Internal Server Error";
	
	//USER ACTIVATION
	public static final String ACCOUNT_ACTIVATED = "Account activated successfully";
	public static final String ACCOUNT_UPDATED = "Account updated successfully";
	public static final String ACCOUNT_ALREADY_ACTIVATED = "Account already activated or invalid";
	public static final int TYPE_CREATE = 1;
	public static final int TYPE_UPDATE = 2;
	
	public static final String STATUS_ACTIVE = "1";
	
	//Access Card Printing
	public static final String TEMPLATE_DIR = "/images/idcard/templates";
	public static final String ROOT_DIR = "D:/jboss-as-7.1.1.Final/jboss-as-7.1.1.Final/standalone/deployments/IoT.war";
	public static final String CARD_TEMP_DIR = "/images/idcard/temp";
	public static final String HEADER_IMAGE = "/template.png";
	public static final String FOOTER_IMAGE = "/footer.png";
	public static final String ADDRESS_IMAGE = "/address.png";
	public static final String CARD_TYPE_EMPLOYEE = "Employee";
	public static final String CARD_TYPE_GUEST = "Guest";
	
	public static final String PRINTED_CARDS_DIR = "D:/test";//IoT/ID Card/printed-cards/";
	
}