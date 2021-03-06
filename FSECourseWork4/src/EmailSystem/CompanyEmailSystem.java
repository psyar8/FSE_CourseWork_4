package EmailSystem;

import java.util.*;

public class CompanyEmailSystem {

	public static int GlobalProjectCounter;
	public static String[] ProjectPhases = new String[]{"Feasibility","Design","Implementation","Testing","Deployment","Completed"};
	
    public static ArrayList<CompanyProject> AllProjects;
    public static int currentProjShowing = -1;
    
 
    
    public static void main(String[] args) {
        
        ///////
        //Startup
        //////
        GlobalProjectCounter = 0;
        AllProjects = new ArrayList<CompanyProject>();
        
        //////////////
        //example test data
        //////////////
        
        CompanyProject cp1 = new CompanyProject("Proj1");
        CompanyProject cp2 = new CompanyProject("Proj2");
        CompanyProject cp3 = new CompanyProject("Proj3");
        
        for (int x=0;x <10; x++) {
        	CompanyEmail ce = new CompanyEmail("me"+x+"@me.com", "you"+x+"@you.com", "this is a test subject for email"+x, "this is a test message for email "+x);
        	
        	switch(x%3) {
        	case 0:
        		cp1.addEmail(ce);
        		break;
        	case 1:
        		cp2.addEmail(ce);
        		break;
        	case 2:
        		cp3.addEmail(ce);
        		break;
        	}
        }
        
        AllProjects.add(cp1);
        AllProjects.add(cp2);
        AllProjects.add(cp3);

        /// END OF TEST DATA ///
        
        /*
         * Change Impact: Main Method
         * ChangeID: CHGE308
         * Author: Aidan Reed
         * Date: 03/05/2018
         * Notes: Initial state of currentProjShowing set to -1 
         * 		  Menu logic has been changed to reflect this
         * 		  Lines 75, 101, 112 Changed
         */
        /*
         * Change Impact: Main Method
         * ChangeID: CHGE309
         * Author: Ram Raja
         * Date: 03.05.2018
         * Notes: Added bounds to project selection.
         *        Projects can only be selected if the user input is
         *        above 0 and below/equal to size of ArrayList.
         */
        /*
         * Change Impact: Main Method
         * ChangeID: CHGE311
         * Author: Aidan Reed
         * Date: 04/05/2018
         * Notes: Reinitialise the currentProject to -1
         */
        currentProjShowing = -1;
        System.out.println("What do you want to do?\n P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String s = in.next();
            
            try{
            	/*CHGE308 Changed to -1*/
                if(currentProjShowing == -1) {
                    if (s.equals("P")) {
                        ListProjects();
                    } else if (s.equals("A")) {
                        AddProject(in);
                    } else if (s.equals("X")) {
                        System.out.println("Goodbye!");
                        break;
                    } else if (Integer.parseInt(s) > 0 && Integer.parseInt(s) <= AllProjects.size()) { //Added bounds, see CHGE309
                    	currentProjShowing = Integer.parseInt(s) - 1; 
                    } else {
                        System.out.println("Command not recognised");
                    }
                } else {
                    if (s.equals("A")) {
                        AddEmail(in);
                    }else if (s.equals("L")) {
                        ListEmails(0);
                    } else if (s.equals("F")) {
                        ListPhases();
                    } else if (s.equals("C")) {
                        ListContacts();
                    } else if (s.equals("N")) {
                        ChangeProjectPhase();
                    } else if (s.equals("X")) {
                    	/*CHGE308 Changed to -1*/
                        currentProjShowing = -1;
                    } else if (Integer.parseInt(s) != -1 ) {
                        ListEmails(Integer.parseInt(s) - 1);
                    } else {
                        System.out.println("Command not recognised");
                    }
                }
            } catch (Exception e) {
                /*
                 * Change Impact: Main Method
                 * ChangeID: CHGE310
                 * Author: Aidan Reed
                 * Date: 04/05/2018
                 * Notes: Added helpful message instead of displaying exception
                 */
            	System.out.println("Command not recognised");
            }
            /*CHGE308 Changed to -1*/
            if(currentProjShowing == -1) {
                System.out.println("What do you want to do?\n P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it Software");
            } else {
                System.out.println("What do you want to do?\n L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project");
            }
        }
        in.close();
        
    }
    
    /*
     * Change Impact: ListProject
     * ChangeID: CHGE303	
     * Author: Christian Stubbs
     * Date: 03.05.2018
     * Notes: Add additional space into string
     */
    public static void ListProjects(){
        for (int x = 0; x < AllProjects.size(); x++) {
            CompanyProject cp = AllProjects.get(x);
            int emailCount = cp.getEmailsForPhase().size();
            System.out.println((x+1) + ") " + cp.toString() + " - " + emailCount + " emails");
        }
    }
    
    
    /*
     * Change Impact: ListProject
     * ChangeID: CHGE304	
     * Author: Christian Stubbs
     * Date: 03.05.2018
     * Notes: Condition added to check length of title provided.
     */
    public static void AddProject(Scanner in) {
        System.out.println("What is the title of the project?");
        in.nextLine(); // to remove read-in bug
        String title = in.nextLine();
        /* Fixing no title bug CHGE304 */
        if (title.length() < 10) {
        	AllProjects.add(new CompanyProject());
        } else {
        	AllProjects.add(new CompanyProject(title));
        }
       
        System.out.println("[Project added]");
    }
    
    public static void ListEmails(int phaseToShow) {
        CompanyProject cp = AllProjects.get(currentProjShowing);
        ArrayList<CompanyEmail> projectPhaseEmails = null;
        if (phaseToShow==0) {
            projectPhaseEmails = cp.getEmailsForPhase();
        } else if (phaseToShow < cp.getPhaseByID()) {
            projectPhaseEmails = cp.getEmailsForPhase(phaseToShow);
        } else {
            System.out.println("Error: Unknown Phase");
        }
        if (projectPhaseEmails != null) {
            System.out.println(cp.toString());
            System.out.println("\n   From                Subject");
            System.out.println("--------------------------------");
            for (int x = 0; x < projectPhaseEmails.size(); x++) {
                CompanyEmail ce = projectPhaseEmails.get(projectPhaseEmails.size()-x-1);
                System.out.println((x+1) + ") " + ce.fromAddress() + " - " + ce.subjectLine());
                if (x==10) {
                    System.out.println("...");
                    break;
                }
            }
        }
    }
    
    
    
    /*
     * Change Impact: ListProject
     * ChangeID: CHGE305	
     * Author: Christian Stubbs
     * Date: 03.05.2018
     * Notes: Condition for loop operator changed to <= as first and last phase 
     * 		  were cut out.
     */
    /*
     * Change Impact: ListProject
     * ChangeID: CHGE307	
     * Author: Aidan Reed
     * Date: 03/05/2018
     * Notes: Condition for loop operator changed to <= as first and last phase 
     * 		  were cut out.
     */
    public static void ListPhases() {
        CompanyProject cp = AllProjects.get(currentProjShowing);
        /* CHGE305 Operator changed from < to <= */
        for (int x=0; x <= cp.getPhaseByID(); x++ ) {
        	/* CHGE307 - pass x to getPhaseByName */
            System.out.println((x+1)+") "+cp.getPhaseByName(x)+" - "+cp.getEmailsForPhase(x).size()+" Emails");
        }
    }
    
    public static void ListContacts() {
        CompanyProject cp = AllProjects.get(currentProjShowing);
        ArrayList<String> projectContacts = cp.getProjectContacts();
        for (int x=0; x < projectContacts.size(); x++ ) {
            System.out.println((x+1)+") "+projectContacts.get(x));
        }
    }
    
    
    /*
     * Change Impact: ListProject
     * ChangeID: CHGE312
     * Author: Aidan Reed
     * Date: 04/05/2018
     * Notes: Redesigned email function to scan for correct input
     * 		  loops round until correct item is provided
     */
    public static void AddEmail(Scanner in) {
        
        String fromAddress;
        String toAddress;
        String subject;
        String msg;
        String sysOut = "Which email address is it from?";
        CompanyProject cp = AllProjects.get(currentProjShowing);
        CompanyEmail ce = new CompanyEmail();
        in.nextLine(); //to remove read-in bug
        Boolean fromValErr = true;
        while (fromValErr) {
        	System.out.println(sysOut);
        	fromAddress = in.nextLine();
        	if (ce.setFrom(fromAddress)) {
        		fromValErr = false;
        	} else {
        		sysOut = "Invalid Email: Which email address is it from?";
        	}
        }
        
        sysOut = "Which email address is it to?";
        
        Boolean toValErr = true;
        while (toValErr) {
        	System.out.println(sysOut);
        	toAddress = in.nextLine();
        	if (ce.setTo(toAddress)) {
        		toValErr = false;
        	} else {
        		sysOut = "Invalid Email: Which email address is it to?";
        	}
        }
        
        sysOut = "What is the Subject?";
        
        Boolean subValErr = true;
        while (subValErr) {
        	System.out.println(sysOut);
        	subject = in.nextLine();
        	if (subject != null) {
        		subValErr = false;
        		ce.setSubject(subject);
        	} else {
        		sysOut = "Invalid Subject: What is the Subject?)";
        	}
        }
        
        sysOut = "What is the Message?";
        
        Boolean msgValErr = true;
        while (msgValErr) {
        	System.out.println(sysOut);
        	msg = in.nextLine();
        	if (msg.length() > 1) {
        		msgValErr = false;
        		ce.setMessage(msg);
        	} else {
        		sysOut = "Invalid Message: What is the Message?)";
        	}
        }
        
        cp.addEmail(ce);
        System.out.println("[Email added to " + cp.toString() + "]");
        
    }
    
    public static void ChangeProjectPhase() {
        CompanyProject cp = AllProjects.get(currentProjShowing);
        if (cp.nextPhase()) {
            System.out.println("[Phase changed: " + cp.toString());
        } else {
            System.out.println("Project already in last phase.");
        }
    }

}