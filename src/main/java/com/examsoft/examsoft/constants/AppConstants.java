package com.examsoft.examsoft.constants;

public class AppConstants {

    public static final Integer SUCCESS_CODE = 2000;
    public static final Integer ERROR_CODE = 2002;
    public static final Integer ACCESS_DENIED_CODE = 2001;

    public static final String SUCCESS_MESSAGE = "Success";
    public static final String ERROR_RESPONSE = "Error";
    public static final String ACCESS_DENIED_MESSAGE = "Failure";


    public static final Integer ACCESS_TOKEN_EXPIRED=2003;
    public static final Integer REFRESH_TOKEN_EXPIRED=2004;

    public static final String SECRET_KEY = "t3pCSx2wx1ExbQ5z43XXB8my/KR24aon4EH/niU9iZi1I3S69rk1QhlMFFsTrZIY";
    public static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60;  //10 hour
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 5; //5 days
    public static final String ENTITY_TYPE="ENTITY_TYPE";

    public static final String ENTITY_TYPE_CONSUMER="CONSUMER";
    public static final String ENTITY_TYPE_DEPARTMENT="DEPARTMENT";
    public static final String ENTITY_TYPE_USER="USER";

    public static final String BEARER_TOKEN_PREFIX = "Bearer ";
    public static final String BASIC_TOKEN_PREFIX =  "Basic ";
    public static final String AUTH_HEADER = "Authorization";
    public static final String SIGN_IN_URI_ENDING = "/login";
    public static final String REALM_HEADER = "WWW-Authenticate";

    public static final String[] PUBLIC_URLS = {

    };

}
