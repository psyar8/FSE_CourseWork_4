package JUnitTests;

import static org.junit.Assert.*;
import EmailSystem.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


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
	 * Date Tested: 
	 * Test Result:
	 * Notes: On load the user should be presented with a menu list
	 * 		  No input is passed into the system
	 */
	
	public void testMenuOptions_OnStartUp() {
		CompanyEmailSystem.main(null);
		String expectedOutput = "What do you want to do?\n P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it\r";
		assertEquals(expectedOutput + "\n", outContent.toString());	
	}
	
	
	@Test 
	/* 
	 * Testing: Exiting the program Main method
	 * Author: Aidan Reed
	 * Co-Author: Athullya Roy
	 * Test ID: 302
	 * Date Tested: 
	 * Test Result:
	 * Notes: When main loads user is presented with a menu
	 * 		  when selecting x the system should close down
	 * 
	 */
	
	public void testMenuOptions_ExitPrograms() {
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
	 * Date Tested: 
	 * Test Result:
	 * Notes: Creates 10,000 projects for one project. Ensuring one project can hold
	 * 		  that many emails.  
	 * 		  
	 */
	
	public void testIncreaseProjectList_Large () {
		/* Initialized the projects array because method is tested without running main - AR 30.04.2018 */
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		for (int i = 0; i < 10000; i++) {
			sysInput = kPTITLE1 + i;
			inScan = new Scanner(sysInput);
			CompanyEmailSystem.AddProject(inScan);
		}
		assertEquals(10000, CompanyEmailSystem.AllProjects.size());
	}
	
	@Test 
	/* 
	 * Testing: List Projects
	 * Author: Aidan Reed
	 * Co-Author: Athullya Roy
	 * Test ID: 304
	 * Date Tested: 
	 * Test Result:
	 * Notes: Tests the projects are listed correctly 
	 * 		  Manually initialises project array and creates 5 projects  
	 * 
	 */
	
	public void testListProjects () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		
		for (int i = 0; i < 5; i++) {
			sysInput = kPTITLE1 + i; 
			inScan = new Scanner(sysInput);
			CompanyEmailSystem.AddProject(inScan);
		}
		
		/* REMOVING THE SYS OUT FROM CREATING THE PROJECTS - AR 30.04.2018 */
		outContent.reset();
		CompanyEmailSystem.ListProjects();
		
		String expectedOutput = 
				  "1) " + kPTITLE1 + "0 [Design] - 0 emails\n"
				+ "2) " + kPTITLE1 + "1 [Design] - 0 emails\n"
				+ "3) " + kPTITLE1 + "2 [Design] - 0 emails\n"
				+ "4) " + kPTITLE1 + "3 [Design] - 0 emails\n"
				+ "5) " + kPTITLE1 + "4 [Design] - 0 emails";
		
		assertEquals(expectedOutput + "\n", outContent.toString());		
	}
	
	
	@Test(timeout=5000) 
	/* 
	 * Testing: List Projects
	 * Author: Aidan Reed
	 * Co-Author: Athullya Roy
	 * Test ID: 305
	 * Date Tested: 
	 * Test Result:
	 * Notes: Create a project and then 10,000 emails to go inside
	 * 		  When listing the projects 10,000 emails should be displayed
	 * 		  
	 */
	public void testListProjects_10KEmail () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		/* Creating the project for 10k Emails */
		sysInput = kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		
		for (int i = 0; i < 10000 ; i++) {
			sysInput = "\n" + kSENDER + "\n" + kRECIPIENT + "\n" + kSUBJECT + "\n" + kBODY1;
			inScan = new Scanner(sysInput);
			CompanyEmailSystem.AddEmail(inScan);
		}
		
		/* REMOVING THE SYS OUT FROM CREATING THE PROJECTS - AR 30.04.2018 */
		outContent.reset();
		CompanyEmailSystem.ListProjects();
		
		String expectedOutput = 
				  "1) " + kPTITLE1 + " [Design] - 10000 emails";
		
		assertEquals(expectedOutput + "\n", outContent.toString());	
	}
	
	
	@Test 
	/* 
	 * Testing: Add Projects
	 * Author: Aidan Reed
	 * Co-Author: Athullya Roy
	 * Test ID: 306
	 * Date Tested: 
	 * Test Result:
	 * Notes: Project is created with specified title Using Scanner Object
	 * 		  Project should be created. Checks system outputs [Project added]
	 * 		 
	 */
	
	public void testAddProject_WithTitle () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		String expectedOutput = "What is the title of the project?\n[Project added]";
		assertEquals(expectedOutput + "\n", outContent.toString());	
	}
	
	@Test 
	/* 
	 * Testing: Add Projects
	 * Author: Aidan Reed
	 * Co-Author: Athullya Roy
	 * Test ID: 307
	 * Date Tested: 
	 * Test Result:
	 * Notes: Project is created with specified title Using Scanner Object
	 * 		  Project should be created. Checks system outputs [Project added]
	 * 		 
	 */
	
	public void testAddProject_WithTitle_ChecksIsSet () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		assertEquals(kPTITLE1, CompanyEmailSystem.AllProjects.get(0).getPTitle());
	}
	
	@Test 
	/* 
	 * Testing: Add Projects
	 * Author: Aidan Reed
	 * Co-Author: Athullya Roy
	 * Test ID: 308
	 * Date Tested: 
	 * Test Result:
	 * Notes: Project created with no title provided. 
	 * 		  As such default title should be given to project
	 * 
	 */
	
	public void testAddProject_NoTitle () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		/* SysInput similated user not inputting data AR - 1.05.2018*/
		sysInput = "\n";
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		/*kDEFAULTTITLE Is defined at the top of the document */
		assertEquals(kDEFAULTTITLE, CompanyEmailSystem.AllProjects.get(0).getPTitle());
		
	}
	
	
	@Test 
	/* 
	 * Testing: List Emails
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 309
	 * Date Tested: 
	 * Test Result:
	 * Notes: Tests the list email function only returns emails from current stage
	 * 		  First test is the initial stage which should list all emails created
	 * 
	 */
	
	public void testListEmails_InitialPhase () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = kPTITLE1;
		inScan = new Scanner(sysInput);
		CompanyEmailSystem.AddProject(inScan);
		/* Adding the email to the project - AR 1.05.2018 */
	
		sysInput = kSENDER + "\n" + kRECIPIENT + "\n" + kSUBJECT + "\n" + kBODY1;
		inScan = new Scanner(sysInput);
		/* Manually setting the current project to add email to - AR 1.05.2018 */
		CompanyEmailSystem.currentProjShowing = 0;
		CompanyEmailSystem.AddEmail(inScan);
		/* Removing the out put from adding email and new project from the output stream */
		outContent.reset();
		CompanyEmailSystem.ListEmails(0);
		String expectedOutput = 
				"Email System [Design]\n" + 
				"\n" + 
				"   From                Subject\n" + 
				"--------------------------------\n" + 
				"1) " + kSENDER + " - " + kSUBJECT + "\n";
		
		assertEquals(expectedOutput, outContent.toString());	
	}
	
	
	@Test 
	/* 
	 * Testing: List Emails
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 310
	 * Date Tested: 
	 * Test Result:
	 * Notes: Tests the list email function only returns an error when an 
	 * 		  invalid phaseID is passed
	 * 
	 */
	
	public void testListEmails_InvalidPhase () {
		CompanyEmailSystem.AllProjects = new ArrayList<CompanyProject>();
		sysInput = kPTITLE1;
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
	 * Testing: Project Menu Display Upon Project Selection 
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 316
	 * Date Tested: 03/05/2018 
	 * Test Result: PASS
	 * Notes: When main loads, user is presented with a menu.
	 * 		  When selecting project number, in this case "2",
	 * 		  system should display the project menu list.
	 * 
	 */
	public void testMenuOptions_ViewProject() {
		sysInput = "2";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		String expectedOutput = "What do you want to do?\n" + 
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it\r\n" + 
				"What do you want to do?\n" + 
				" L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project\r";
		assertEquals(expectedOutput + "\n", outContent.toString());
	}
	
	@Test
	/* 
	 * Testing: Project Menu Display With Non-Existent Projects
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 317
	 * Date Tested: 03/05/2018 
	 * Test Result: FAIL
	 * Notes: Testing projects "-1", "-7" and "7", "99" return 
	 *        "Command not recognised" when attempted to be loaded.
	 * 
	 */
	public void testMenuOptions_InvalidProjects() {
		String expectedOutput = "What do you want to do?\n" + 
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it\r\n" + 
				"Command not recognised\r\n" + 
				"What do you want to do?\n" + 
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it Software\r";
		
		sysInput = "-1";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput + "\n", outContent.toString());
		outContent.reset();
		
		sysInput = "-7";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput + "\n", outContent.toString());
		outContent.reset();
		
		sysInput = "7";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput + "\n", outContent.toString());
		outContent.reset();

		sysInput = "99";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput + "\n", outContent.toString());
		outContent.reset();

	}
	
	@Test
	/* 
	 * Testing: Project Menu Display With Multiple Existing Projects
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 318
	 * Date Tested: 03/05/2018 
	 * Test Result: FAIL
	 * Notes: Testing projects "1", "2" and "3" display project menu once loaded.
	 * 
	 */
	public void testMenuOptions_ExistingProjects() {
		String expectedOutput = "What do you want to do?\n" + 
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it\r\n" + 
				"What do you want to do?\n" + 
				" L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project\r";
		
		sysInput = "1";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput + "\n", outContent.toString());
		outContent.reset();
		
		sysInput = "2";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput + "\n", outContent.toString());
		outContent.reset();
		
		sysInput = "3";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		assertEquals(expectedOutput + "\n", outContent.toString());
		outContent.reset();

	}
	
	@Test
	/* 
	 * Testing: Exiting a Project Using Menu Option
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 319
	 * Date Tested: 03/05/2018 
	 * Test Result: PASS
	 * Notes: When a project is open, selecting "X" from menu
	 *        should close project and return user to the main menu.
	 * 
	 */
	public void testMenuOptions_ExitProject() {
		sysInput = "2\rX";
		inStream = new ByteArrayInputStream(sysInput.getBytes());
		System.setIn(inStream);
		CompanyEmailSystem.main(null);
		String expectedOutput = "What do you want to do?\n" + 
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it\r\n" +
				"What do you want to do?\n" + 
				" L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project\r\n" +
				"What do you want to do?\n" + 
				" P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it Software\r";
		assertEquals(expectedOutput + "\n", outContent.toString());
		
	}

}
