
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
    
    public String emailMessage() {
        return emailMessage();
    }
    
    public void setFrom(String fromAddr) {
        if (fromAddr.contains("@")) {
            fromAddress = fromAddr;
        }
    }
    
    public void setTo(String toAddr) {
        if (toAddr.contains("@")) {
            toAddress = toAddr;
        }
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
    
    public String toString() {
    	if (subjectLine.equals("")) {
    		return "[no subject]";
    	} else {
    		return subjectLine;
    	}
    }
}
