package EmailSystem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompanyEmail {
    private String fromAddress;
    private String toAddress;
    private String subjectLine;
    private String emailMessage;
    
    public CompanyEmail() {
        fromAddress = null;
        toAddress = null;
        subjectLine = null;
        emailMessage = null;
    }

    public CompanyEmail(String fAddress, String tAddress, String subLine, String eMessage) {
    	fromAddress = fAddress;
    	toAddress = tAddress;
    	subjectLine = subLine;
    	emailMessage = eMessage;
    }
    
    public String fromAddress() {
        return fromAddress;
    }
    
    public String toAddress() {
        return toAddress;
    }
    
    public String subjectLine() {
        return subjectLine;
    }
    
    /*
     * Change Impact: emailMessage()
     * ChangeID: CHG001
     * Author: Aidan Reed
     * Date: 27.04.2018
     * Notes: Removed return emailMessage() Call and replaced with
     * 		  return emailMessage; Used keyword this to diff between method
     */
    public String emailMessage() {
        return this.emailMessage;
    }
    
    
    /*
     * Change Impact: setFrom()
     * ChangeID: CHG003
     * Author: Aidan Reed
     * Date: 27.04.2018
     * Notes: Added method call to emailParser to validate email
     */
    public Boolean setFrom(String fromAddr) {
        if (emailParser(fromAddr)) {
            fromAddress = fromAddr;
            return true;
        }
        else return false;
    }
    
    /*
     * Change Impact: setTo()
     * ChangeID: CHG003
     * Author: Aidan Reed
     * Date: 27.04.2018
     * Notes: Added method call to emailParser to validate email
     */
    public Boolean setTo(String toAddr) {
    	if(emailParser(toAddr)) {
    		toAddress = toAddr;
    		return true;
        }
        else return false;
    }
    
    /*
     * Change Impact: new method emailParser
     * ChangeID: CHG003
     * Author: Aidan Reed
     * Date: 27.04.2018
     * Notes: Added a regular expression to check the validity of emails returns 
     * 		  boolean for calling function
     */
    public boolean emailParser (String address) {
    	String regExValidate = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    	Matcher emailMatcher = Pattern.compile(regExValidate).matcher(address);
    	if (emailMatcher.matches()) {
    		return true;
    	}
    	return false;
    }
    
    public void setSubject(String subLine) {
        subjectLine = subLine;
    }
    
    public void setMessage(String eMessage) {
        emailMessage = eMessage;
    }
    
    public boolean isValid() {
        boolean isComplete = true;
        if (fromAddress == null) isComplete = false;
        if (toAddress == null) isComplete = false;
        if (subjectLine == null) isComplete = false;
        if (emailMessage == null) isComplete = false;
        return isComplete;
    }
  
    /*
     * Change Impact: toString()
     * ChangeID: CHG002
     * Author: Aidan Reed
     * Date: 27.04.2018
     * Notes: Added check to see if subject line is null
     * 		  prevents null exception
     */
    public String toString() {
    	if (subjectLine == null || subjectLine.equals("")) {
    		return "[no subject]";
    	} else {
    		return subjectLine;
    	}
    }
}
