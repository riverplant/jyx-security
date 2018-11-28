package com.river.browser.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpUtils {
	// Headers parameter to parse to retrieve an ip in order of priority
    private static final String[] HEADERS = new String[]{"X-REAL-IP", "X-FORWARDED-FOR"};
    
    private static Pattern VALID_IPV4_PATTERN = null;
    private static Pattern VALID_IPV6_PATTERN = null;
    private static final String ipv4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    private static final String ipv6Pattern = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";
    private static Logger logger = LoggerFactory.getLogger(IpUtils.class);
    
    static {
      try {
        VALID_IPV4_PATTERN = Pattern.compile(ipv4Pattern, Pattern.CASE_INSENSITIVE);
        VALID_IPV6_PATTERN = Pattern.compile(ipv6Pattern, Pattern.CASE_INSENSITIVE);
      } catch (PatternSyntaxException e) {
        logger.info("Unable to compile pattern", e);
      }
    }
    
    
    
    public static String getIp(HttpServletRequest request){
        String header = null;
        String[] ips;
        for(String name : HEADERS){
            header = request.getHeader(name);
            if(header != null && !header.isEmpty())
                break;
        }
        
        if(header == null || header.isEmpty())
            header = request.getRemoteAddr();
        else {
             // if request went through proxies multiple ip might be in the chain
            int index = header.indexOf(",");
            if(index > -1){
                header = header.substring(0, index);
            }
        }
        return header;
    }
    
    /**
     * Determine if the given string is a valid IPv4 or IPv6 address.  This method
     * uses pattern matching to see if the given string could be a valid IP address.
     *
     * @param ipAddress A string that is to be examined to verify whether or not
     *  it could be a valid IP address.
     * @return <code>true</code> if the string is a value that is a valid IP address,
     *  <code>false</code> otherwise.
     */
    public static boolean isIpAddress(String ipAddress) {

        Matcher m1 = VALID_IPV4_PATTERN.matcher(ipAddress);
        if (m1.matches()) {
            return true;
        }
        Matcher m2 = VALID_IPV6_PATTERN.matcher(ipAddress);
        return m2.matches();
    }
}
