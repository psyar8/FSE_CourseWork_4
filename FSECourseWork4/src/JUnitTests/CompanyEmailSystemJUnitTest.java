package JUnitTests;

import static org.junit.Assert.*;
import EmailSystem.*;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

@FixMethodOrder(MethodSorters.JVM)
public class CompanyEmailSystemJUnitTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	/* String used to pass test inputs into scanner */
	String sysInput = "";
	Scanner inScan;
	InputStream inStream = new ByteArrayInputStream(sysInput.getBytes());
	
	

	String kPTITLE1 = "Email System";
	final String kDEFAULTTITLE = "New Project";
	final String kSENDER = "joe.bloggs@gmail.com";
	final String kRECIPIENT = "max.power@live.com";
	final String kCONTACT1 = "test@gmail.com";
	final String kCONTACT2 = "raiu9s@gmail.com";
	final String kCONTACT3 = "q39ikdf@outlook.com";
	final String kBADEMAIL1 = "joe.bloggs@";
	final String kBADEMAIL2 = "joe.@bloggs";
	final String kBADEMAIL3 = "joe.bloggs@gmail@.com";
	final String kBADEMAIL4 = "@joe.bloggs@gmail.com";
	final String kSUBJECT = "RE: Lorem ipsum";
	final String kBODY1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
	final String kBODY2 = "This is a test email for using unit testing";
	final String kBODY3 = "";
	final String kBODY4 = "Test Email";
	
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		inScan = new Scanner(sysInput);
		System.setIn(inStream);
	}
	
	@After
	public void cleanUpStreams() { 
		System.setOut(null);
		System.setIn(null);
	}
	
	@Test
	/* 
	 * Testing: Default Menu Output
	 * Author: Aidan Reed
	 * Co-Author: Athullya Roy
	 * Test ID: 301
	 * Date Tested: 01.05.2018
	 * Test Result: PASS
	 * Notes: On load the user should be presented with a menu list
	 * 		  No input is passed into the system
	 */
	
	public void testMenuOptions_OnStartUp_301() {
		CompanyEmailSystem.main(null);
		String expectedOutput = "What do you want to do?\n P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it\n";
		assertEquals(expectedOutput , outContent.toString());	
	}
	
	
	@Test 
	/* 
	 * Testing: Exiting the program Main method
	 * Author: Athullya Roy
	 * Co-Author: Aidan Reed
	 * Test ID: 302
	 * Date Tested: 01.05.2018
	 * Test Result: PASS
	 * Notes: When main loads user is presented with a menu
	 * 		  when selecting x the system should close down
	 * 
	 */
	
	public void testMenuOptions_ExitPrograms_302() {
		/* Setting up the in stream to simulate user entering from console - AR 30.04.2018*/
		sysInput = "X";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		/* After setting up the streams a call main is made - immediately takes the X and closes - AR 30.04.2018*/
		CompanyEmailSystem.main(null);
		String expectedOutput = "What do you want to do?\n P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it\nGoodbye!";
		assertEquals(expectedOutput + "\n", outContent.toString());
	}
	
	
	@Test 
	/* 
	 * Testing: Projects Array
	 * Author: Aidan Reed
	 * Co-Author: Athullya Roy
	 * Test ID: 303
	 * Date Tested: 03.05.2018 | 02/05/2018 | 01/05/2018
	 * Test Result: PASS       | FAIL		| FAIL
	 * Notes: Creates 10,000 projects for one project. Ensuring one project can hold
	 * 		  that many emails.  
	 * 		  
	 */
	
	public void testIncreaseProjectList_Large_303 () {
		/* Initialized the projects array because method is tested without running main - AR 30.04.2018 */
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		for (int i = 0; i < 10000; i++) {
			sysInput = "\n" + kPTITLE1 + i;
			inScan = new Scanner(sysInput);
			CompanyEmailSystem.AddProject(inScan);
		}
		assertEquals(10000, CompanyEmailSystem.AllProjects.size());
	}
	
	@Test 
	/* 
	 * Testing: List Projects
	 * Author: Athullya Roy
	 * Co-Author: Aidan Reed
	 * Test ID: 304
	 * Date Tested: 03.05.2018 | 02/05/2018 | 01/05/2018
	 * Test Result: PASS       | FAIL       | FAIL
	 * Notes: Tests the projects are listed correctly 
	 * 		  Manually initialises project array and creates 5 projects  
	 * 
	 */
	
	public void testListProjects_304 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		
		for (int i = 0; i < 5; i++) {
			sysInput = "\n" + kPTITLE1 + i; 
			inScan = new Scanner(sysInput);
			CompanyEmailSystem.AddProject(inScan);
		}
		
		/* REMOVING THE SYS OUT FROM CREATING THE PROJECTS - AR 30.04.2018 */
		outContent.reset();
		CompanyEmailSystem.ListProjects();
		
		String expectedOutput = 
				  "1) " + kPTITLE1 + "0 [Feasibility] - 0 emails\n"
				+ "2) " + kPTITLE1 + "1 [Feasibility] - 0 emails\n"
				+ "3) " + kPTITLE1 + "2 [Feasibility] - 0 emails\n"
				+ "4) " + kPTITLE1 + "3 [Feasibility] - 0 emails\n"
				+ "5) " + kPTITLE1 + "4 [Feasibility] - 0 emails";
		
		assertEquals(expectedOutput + "\n", outContent.toString());		
	}
	
	
	@Test 
	/* 
	 * Testing: List Projects
	 * Author: Aidan Reed
	 * Co-Author: Athullya Roy
	 * Test ID: 305
	 * Date Tested: 03.05.2018 | 02/05/2018 | 01/05/2018
	 * Test Result: PASS       | FAIL       | FAIL
	 * Notes: Tests the projects are listed correctly 
	 * 		  Manually initialises project array and creates 6 projects
	 * 		  Each project is set to one of the stages  
	 */
	
	public void testListProjects_AllStages_305 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		
		for (int i = 0; i < 6 ; i++) {
			sysInput = "\n" + kPTITLE1 + i; 
			inScan = new Scanner(sysInput);
			CompanyEmailSystem.AddProject(inScan);
			for (int y = 0; y < i; y++) {
				CompanyEmailSystem.AllProjects.get(i).nextPhase();
			}
		}
		
		/* REMOVING THE SYS OUT FROM CREATING THE PROJECTS - AR 30.04.2018 */
		outContent.reset();
		CompanyEmailSystem.ListProjects();
		
		String expectedOutput = 
				  "1) " + kPTITLE1 + "0 [Feasibility] - 0 emails\n"
				+ "2) " + kPTITLE1 + "1 [Design] - 0 emails\n"
				+ "3) " + kPTITLE1 + "2 [Implementation] - 0 emails\n"
				+ "4) " + kPTITLE1 + "3 [Testing] - 0 emails\n"
				+ "5) " + kPTITLE1 + "4 [Deployment] - 0 emails\n"
				+ "6) " + kPTITLE1 + "5 [Completed] - 0 emails";
		
		assertEquals(expectedOutput + "\n", outContent.toString());		
	}
	
	
	@Test(timeout=5000) 
	/* 
	 * Testing: List Projects
	 * Author: Athullya Roy
	 * Co-Author: Aidan Reed
	 * Test ID: 306
	 * Date Tested: 03.05.2018 | 02/05/2018 | 01/05/2018
	 * Test Result: PASS       | FAIL       | FAIL
	 * Notes: Create a project and then 10,000 emails to go inside
	 * 		  When listing the projects 10,000 emails should be displayed
	 * 		  
	 */
	public void testListProjects_10KEmail_306 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		/* Creating the project for 10k Emails */
		sysInput = "\n" + kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		CompanyEmailSystem.currentProjShowing = 0;
		for (int i = 0; i < 10000 ; i++) {
			sysInput = "\n" + kSENDER + "\n" + kRECIPIENT + "\n" + kSUBJECT + "\n" + kBODY1;
			inScan = new Scanner(sysInput);
			CompanyEmailSystem.AddEmail(inScan);
		}
		
		/* REMOVING THE SYS OUT FROM CREATING THE PROJECTS - AR 30.04.2018 */
		outContent.reset();
		CompanyEmailSystem.ListProjects();
		
		String expectedOutput = 
				  "1) " + kPTITLE1 + " [Feasibility] - 10000 emails";
		
		assertEquals(expectedOutput + "\n", outContent.toString());	
	}
	
	
	@Test 
	/* 
	 * Testing: Add Projects
	 * Author: Aidan Reed
	 * Co-Author: Athullya Roy
	 * Test ID: 307
	 * Date Tested: 03.05.2018 | 02/05/2017 | 01/05/2018
	 * Test Result: PASS       | FAIL       | FAIL
	 * Notes: Project is created with specified title Using Scanner Object
	 * 		  Project should be created. Checks system outputs [Project added]
	 * 		 
	 */
	
	public void testAddProject_WithTitle_307 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = "\n" + kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		String expectedOutput = "What is the title of the project?\n[Project added]";
		assertEquals(expectedOutput + "\n", outContent.toString());	
	}
	
	@Test 
	/* 
	 * Testing: Add Projects
	 * Author: Athullya Roy
	 * Co-Author: Aidan Reed
	 * Test ID: 308
	 * Date Tested: 03.05.2018 | 02/05/2017 | 01/05/2018
	 * Test Result: PASS       | FAIL       | FAIL
	 * Notes: Project is created with specified title Using Scanner Object
	 * 		  Project should be created. Checks system outputs [Project added]
	 * 		 
	 */
	
	public void testAddProject_WithTitle_ChecksIsSet_308 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = "\n" + kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		assertEquals(kPTITLE1, CompanyEmailSystem.AllProjects.get(0).getPTitle());
	}
	
	@Test 
	/* 
	 * Testing: Add Projects
	 * Author: Aidan Reed
	 * Co-Author: Athullya Roy
	 * Test ID: 309
	 * Date Tested: 03/05/2018 | 03.05.2018 | 02/05/2017 | 01/05/2018
	 * Test Result: PASS       | FAIL       | FAIL       | FAIL
	 * Notes: Project created with no title provided. 
	 * 		  As such default title should be given to project
	 * 
	 */
	
	public void testAddProject_NoTitle_309 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		/* SysInput similated user not inputting data AR - 1.05.2018*/
		sysInput = "\n \n";
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		/*kDEFAULTTITLE Is defined at the top of the document */
		assertEquals(kDEFAULTTITLE, CompanyEmailSystem.AllProjects.get(0).getPTitle());
	}
	
	
	@Test 
	/* 
	 * Testing: List Emails
	 * Author: Athullya Roy
	 * Co-Author: Aidan Reed
	 * Test ID: 310
	 * Date Tested: 03.05.2018 | 02/05/2017 | 01/05/2018
	 * Test Result: PASS       | FAIL       | FAIL
	 * Notes: Tests the list email function only returns emails from current stage
	 * 		  First test is the initial stage which should list all emails created
	 * 
	 */
	
	public void testListEmails_InitialPhase_310 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = "\n" + kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		/* Adding the email to the project - AR 1.05.2018 */
	
		sysInput = "\n" + kSENDER + "\n" + kRECIPIENT + "\n" + kSUBJECT + "\n" + kBODY1;
		inScan = new Scanner(sysInput);
		/* Manually setting the current project to add email to - AR 1.05.2018 */
		CompanyEmailSystem.currentProjShowing = 0;
		CompanyEmailSystem.AddEmail(inScan);
		/* Removing the out put from adding email and new project from the output stream */
		outContent.reset();
		CompanyEmailSystem.ListEmails(0);
		String expectedOutput = 
				"Email System [Feasibility]\n" + 
				"\n" + 
				"   From                Subject\n" + 
				"--------------------------------\n" + 
				"1) " + kSENDER + " - " + kSUBJECT + "\n";
		
		assertEquals(expectedOutput, outContent.toString());	
	}
	
	
	@Test 
	/* 
	 * Testing: List Emails
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 311
	 * Date Tested: 03.05.2017
	 * Test Result: Christian Stubbs
	 * Notes: Tests the list email function only returns an error when an 
	 * 		  invalid phaseID is passed
	 * 
	 */
	
	public void testListEmails_Invalid_311 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = "\n" +  kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		
		/* Removing the out put from adding email and new project from the output stream */
		outContent.reset();
		
		
		CompanyEmailSystem.ListEmails(20000);
		String expectedOutput = "Error: Unknown Phase\n";
		assertEquals(expectedOutput, outContent.toString());	
	}
	
	
	@Test 
	/* 
	 * Testing: List Phase
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 312
	 * Date Tested: 03.05.2018 
	 * Test Result: PASS (Post BUG304 fix) 
	 * Notes: Tests the list phase for a project in the initial stages
	 * 		  An error in the List Phases method prevented this test from
	 * 		  Initially Passing
	 */
	
	public void testListPhases_InitialPhase_312 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = "\n" +  kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		CompanyEmailSystem.currentProjShowing = 0;
		/* Adding some emails to the initial stage */
		for (int i = 0; i < 5 ; i++) {
				sysInput = "\n" + kSENDER + "\n" + kRECIPIENT + "\n" + kSUBJECT + "\n" + kBODY1;
				inScan = new Scanner(sysInput);
				CompanyEmailSystem.AddEmail(inScan);
		}
		outContent.reset();
		CompanyEmailSystem.ListPhases();
		
		String expectedOutput = 
				  "1) Feasibility - 5 Emails\n";
				
		assertEquals(expectedOutput, outContent.toString());	
	}
	
	
	
	@Test 
	/* 
	 * Testing: List Phase
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 313
	 * Date Tested: 03/05/2018
	 * Test Result: PASS (Post bug fix 304 and 305)
	 * Notes: Tests a project after it has been completed and ensures  
	 * 		  the function lists the correct phases and email counts
	 * 		  at each phase. 
	 * 
	 */
	
	public void testListPhases_AllPhases_313 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = "\n" +  kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		CompanyEmailSystem.currentProjShowing = 0;
		for (int i = 0; i < 6 ; i++) {
			for (int y = 0; y <= i; y++) {
				sysInput = "\n" + kSENDER + "\n" + kRECIPIENT + "\n" + kSUBJECT + "\n" + kBODY1;
				inScan = new Scanner(sysInput);
				CompanyEmailSystem.AddEmail(inScan);
			}
			CompanyEmailSystem.AllProjects.get(0).nextPhase();
		}
		
		outContent.reset();
		CompanyEmailSystem.ListPhases();
		
		String expectedOutput = 
				  "1) Feasibility - 1 Emails\n"
				+ "2) Design - 2 Emails\n"
				+ "3) Implementation - 3 Emails\n"
				+ "4) Testing - 4 Emails\n"
				+ "5) Deployment - 5 Emails\n"
				+ "6) Completed - 6 Emails";
				
			
		assertEquals(expectedOutput + "\n", outContent.toString());	
	}
	
	
	@Test 
	/* 
	 * Testing: List Contacts
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 314
	 * Date Tested: 03/05/2018
	 * Test Result: PASS
	 * Notes: Ensures the correct contacts are returned in the initial phase
	 */
	public void testListContacts_314 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = "\n" +  kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		CompanyEmailSystem.currentProjShowing = 0;
		
		/* Adding contacts */
		CompanyEmailSystem.AllProjects.get(0).addContact(kCONTACT1);
		CompanyEmailSystem.AllProjects.get(0).addContact(kCONTACT2);
		CompanyEmailSystem.AllProjects.get(0).addContact(kCONTACT3);
		/* Creating OutStream */
		outContent.reset();
		String expectedOutput = 
				  "1) " + kCONTACT1 + "\n"
				+ "2) " + kCONTACT2 + "\n"
				+ "3) " + kCONTACT3 + "\n";
				
		CompanyEmailSystem.ListContacts();
		assertEquals(expectedOutput, outContent.toString());	
	
	}
	
	@Test 
	/* 
	 * Testing: Add Email
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 315
	 * Date Tested: 03/05/2018
	 * Test Result: PASS
	 * Notes: Tests the system presents correct prompts when adding an email
	 */
	public void testAddEmail_315 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = "\n" +  kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		CompanyEmailSystem.currentProjShowing = 0;
		outContent.reset();
		
		sysInput = "\n" + kSENDER + "\n" + kRECIPIENT + "\n" + kSUBJECT + "\n" + kBODY1 + "\n";
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddEmail(inScan);
		String expectedOutput = 
				  "Which email address is it from?\n"
				  + "Which email address is it to?\n"
				  + "What is the Subject?\n"
				  + "What is the Message?\n"
				  + "[Email added to " + kPTITLE1 + " [Feasibility]]\n";
				
		assertEquals(expectedOutput, outContent.toString());	
	}
	
	
	
	
	
	@Test 
	/* 
	 * Testing: Next Phase
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 317
	 * Date Tested: 03/05/2018
	 * Test Result: PASS
	 * Notes: The system is tested to ensure the correct user feedback is provided 
	 * 		  When moving project to next stage
	 */
	public void testNextPhase_317 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = "\n" +  kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		CompanyEmailSystem.currentProjShowing = 0;
		outContent.reset();
		String expectedOutput = 
				  "[Phase changed: Email System [Design]\n";
		
		CompanyEmailSystem.ChangeProjectPhase();
		assertEquals(expectedOutput, outContent.toString());	
	}
	
	@Test 
	/* 
	 * Testing: Next Phase
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 318
	 * Date Tested: 03/05/2018
	 * Test Result: PASS
	 * Notes: The system is tested to ensure the correct user feedback is provided 
	 * 		  When moving project to next stage for all stages
	 */
	public void testNextPhaseAllPhases_318 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = "\n" +  kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		CompanyEmailSystem.currentProjShowing = 0;
		outContent.reset();
		String expectedOutput = 
				  "[Phase changed: Email System [Design]\n"
				  + "[Phase changed: Email System [Implementation]\n"
				  + "[Phase changed: Email System [Testing]\n"
				  + "[Phase changed: Email System [Deployment]\n"
				  + "[Phase changed: Email System [Completed]\n";
		for (int i = 0; i < 5; i++) {
			CompanyEmailSystem.ChangeProjectPhase();
		}
		assertEquals(expectedOutput, outContent.toString());	
	}
	
	@Test
	/* 
	 * Testing: Next Phase
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 319
	 * Date Tested: 03/05/2018
	 * Test Result: PASS
	 * Notes: The system is tested to ensure the correct user feedback is provided 
	 * 		  When moving project to next stage when the user is in the final 
	 * 		  stage and tries to move past.
	 */
	public void testNextPhasePastLast_319 () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = "\n" +  kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		CompanyEmailSystem.currentProjShowing = 0;
		
		String expectedOutput = 
				  "Project already in last phase.\n";
		for (int i = 0; i < 6; i++) {
			CompanyEmailSystem.ChangeProjectPhase();
		}
		outContent.reset();
		
		CompanyEmailSystem.ChangeProjectPhase();
		assertEquals(expectedOutput, outContent.toString());	
	}
	
	@Test
	/* 
	 * Testing:  Main Method
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 320
	 * Date Tested: 04/05/2018
	 * Test Result: PASS
	 * Notes: When a incorrect command is entered the program should not throw an exception
	 * 		  it should say command not recgonised and display the options again.
	 */
	public void testMenuOptionsException_320 () {
		sysInput = "z";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		String expectedOutput = "What do you want to do?\n" + 
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it\n" + 
				"Command not recognised\n" +
				"What do you want to do?\n" +
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it Software\n";
	
		assertEquals(expectedOutput, outContent.toString());
	}
	

	@Test
	/* 
	 * Testing: Project Menu Display Upon Project Selection 
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 321
	 * Date Tested: 03/05/2018 
	 * Test Result: PASS
	 * Notes: When main loads, user is presented with a menu.
	 * 		  When selecting project number, in this case "2",
	 * 		  system should display the project menu list.
	 * 
	 */
	public void testMenuOptions_ViewProject_321() {
		sysInput = "2";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		String expectedOutput = "What do you want to do?\n" + 
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it\n" + 
				"What do you want to do?\n" + 
				" L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project\n";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	
	
	
	
	@Test
	/* 
	 * Testing: Project Menu Display With Non-Existent Projects
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 322
	 * Date Tested: 03/05/2018 
	 * Test Result: PASS
	 * Notes: Testing projects "-1", "-7" and "7", "99" return 
	 *        "Command not recognised" when attempted to be loaded.
	 * 
	 */
	public void testMenuOptions_InvalidProjects_322() {
		String expectedOutput = "What do you want to do?\n" + 
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it\n" + 
				"Command not recognised\n" + 
				"What do you want to do?\n" + 
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it Software\n";
		
		sysInput = "-1";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput, outContent.toString());
		outContent.reset();
		
		sysInput = "-7";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput, outContent.toString());
		outContent.reset();
		
		sysInput = "7";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput, outContent.toString());
		outContent.reset();

		sysInput = "99";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput, outContent.toString());
		outContent.reset();

	}
	
	@Test
	/* 
	 * Testing: Project Menu Display With Multiple Existing Projects
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 323
	 * Date Tested: 03/05/2018 
	 * Test Result: FAIL
	 * Notes: Testing projects "1", "2" and "3" display project menu once loaded.
	 * 
	 */
	public void testMenuOptions_ExistingProjects_232() {
		String expectedOutput = "What do you want to do?\n" + 
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it\n" + 
				"What do you want to do?\n" + 
				" L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project\n";
		
		sysInput = "1";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput, outContent.toString());
		outContent.reset();
		
		sysInput = "2";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput, outContent.toString());
		outContent.reset();
		
		sysInput = "3";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput, outContent.toString());
		outContent.reset();

	}
	
	@Test
	/* 
	 * Testing: Exiting a Project Using Menu Option
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 324
	 * Date Tested: 03/05/2018 
	 * Test Result: PASS
	 * Notes: When a project is open, selecting "X" from menu
	 *        should close project and return user to the main menu.
	 * 
	 */
	public void testMenuOptions_ExitProject_324() {
		sysInput = "2\rX";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		String expectedOutput = "What do you want to do?\n" + 
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it\n" +
				"What do you want to do?\n" + 
				" L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project\n" +
				"What do you want to do?\n" + 
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it Software\n";
		assertEquals(expectedOutput, outContent.toString());
		
	}

	
	@Test
	/* 
	 * Testing: Add Email Validity 
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 325
	 * Date Tested: 04/05/2018 
	 * Test Result: FAIL
	 * Notes: Tests the process of adding an email after providing invalid recipient and 
	 * 		  Sender email addresses
	 * 
	 */
	public void testAddEmail_InvalidEmail_325() {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = "\n" +  kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		CompanyEmailSystem.currentProjShowing = 0;
		outContent.reset();
		
		sysInput = "\n" + kBADEMAIL1 + "\n" + kSENDER + "\n" + kBADEMAIL2 + "\n" + kRECIPIENT + "\n" + "\n" + kSUBJECT + "\n" + kBODY1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddEmail(inScan);
		String expectedOutput = 
				  "Which email address is it from?\n"
				  + "Invalid Email: Which email address is it from?\n"
				  + "Which email address is it to?\n"
				  + "Invalid Email: Which email address is it to?\n"
				  + "What is the Subject?\n"
				  + "What is the Message?\n"
				  + "[Email added to " + kPTITLE1 + " [Feasibility]]\n";
				  
		assertEquals(expectedOutput, outContent.toString());
		
	}
	
}
