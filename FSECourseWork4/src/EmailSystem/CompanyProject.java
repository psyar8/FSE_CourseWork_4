package EmailSystem;
import java.util.ArrayList;

public class CompanyProject {
    private int PID;
    private String PTitle;
    private ArrayList<String> ProjectContacts;
    private int ProjectPhase;
    private ArrayList[] ProjectEmails = new ArrayList[6];
    
    /*
     * Change Impact: CompanyProject()
     * ChangeID: CHGE001
     * Author: Justin Ng
     * Date: 02.05.2018
     * Notes: Changed ProjectPhase = 1 to ProjectPhase = 0
     */
    public CompanyProject() {
        CompanyEmailSystem.GlobalProjectCounter++;
        PID = CompanyEmailSystem.GlobalProjectCounter;
        PTitle = "New Project";
        ProjectContacts = new ArrayList<String>();
        ProjectPhase = 0;
        ProjectEmails[ProjectPhase] = new ArrayList<CompanyEmail>();
    }
    /*
     * Change Impact: CompanyProject(String pTitle)
     * ChangeID: CHGE001
     * Author: Justin Ng
     * Date: 02.05.2018
     * Notes: Changed ProjectPhase = 1 to ProjectPhase = 0
     */
    public CompanyProject(String pTitle) {
    	CompanyEmailSystem.GlobalProjectCounter++;
        PID = CompanyEmailSystem.GlobalProjectCounter;
        PTitle = pTitle;
        ProjectContacts = new ArrayList<String>();
        ProjectPhase = 0;
        ProjectEmails[ProjectPhase] = new ArrayList<CompanyEmail>();
    }
    
    public int getPID() {
        return PID;
    }
    
    public String getPTitle() {
        return PTitle;
    }
    
    public void setPTitle(String pTitle) {
    	if (pTitle.length() > 10 ) {
    		PTitle = pTitle;
    	}
    }
    
    public boolean isContact(String emailAddress) {
        return ProjectContacts.contains(emailAddress);
    }
    
    public void addContact(String emailAddress) {
        ProjectContacts.add(emailAddress);
    }
    
    public void addEmail(CompanyEmail newEmail) {
        if (newEmail.isValid()) {
            ProjectEmails[ProjectPhase].add(newEmail);
            if (ProjectContacts.contains(newEmail.fromAddress())) {
                //do nothing
            } else {
                ProjectContacts.add(newEmail.fromAddress());
            }
        }
    }
    
    public ArrayList<CompanyEmail> getEmailsForPhase() {
        return ProjectEmails[ProjectPhase];
    }
    
    public ArrayList<CompanyEmail> getEmailsForPhase(int thePhase) {
        return ProjectEmails[thePhase];
    }
    
    /*
     * Change Impact: nextPhase()
     * ChangeID: CHGE002
     * Author: Justin Ng
     * Date: 02.05.2018
     * Notes: Change check for ProjectPhases length to lenght -1 and moved ProjectPhase incrementor inside else statement
     */
    public boolean nextPhase() {
        if (ProjectPhase >= (CompanyEmailSystem.ProjectPhases.length - 1)) {
            return false;
        } else {
        	ProjectPhase++;
            return true;
        }
    }
    
    public String getPhaseByName() {
        return CompanyEmailSystem.ProjectPhases[ProjectPhase];
    }
    
    public int getPhaseByID() {
        return ProjectPhase;
    }
    
    public ArrayList<String> getProjectContacts() {
        return ProjectContacts;
    }
    
    public String toString() {
        return PTitle + " [" + getPhaseByName() + "]";
    }
}
