package JUnitTests;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import EmailSystem.CompanyEmail;
import EmailSystem.CompanyEmailSystem;
import EmailSystem.CompanyProject;
/* FixMethodOrder is used to ensure tests run sequentially as they are written - JN: 25.04.18 */
@FixMethodOrder(MethodSorters.JVM)
public class CompanyEmailProject_UnitTests {

	final static String kPTITLE1 = "12345678";
	final String kPTITLE2 = "123456789000";
	final String kDEFAULTTITLE = "New Project";
	final static String kCONTACT1 = "test@gmail.com";
	final static String kCONTACT2 = "raiu9s@gmail.com";
	final static String kCONTACT3 = "q39ikdf@outlook.com";

	final static String kSENDER = "joe.bloggs@gmail.com";
	final static String kRECIPIENT = "max.power@live.com";
	final static String kSUBJECT = "RE: Lorem ipsum";
	final static String kBODY1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
	
	int lengthProjects = CompanyEmailSystem.ProjectPhases.length - 1;
	static CompanyProject companyProjectFirst_Empty;
	static CompanyProject companyProjectSecond_Phases;
	static CompanyProject companyProjectThird_Complete;
	
	static CompanyProject companyProjectForth_Phases;
	
	@BeforeClass
	public static void setUp() {
		companyProjectFirst_Empty = new CompanyProject();
		companyProjectSecond_Phases = new CompanyProject();
		companyProjectThird_Complete = new CompanyProject(kPTITLE1);
		companyProjectThird_Complete.addContact(kCONTACT1);
		companyProjectThird_Complete.addContact(kCONTACT2);
		companyProjectThird_Complete.addContact(kCONTACT3);
		

	}
	
	@Before 
	public void setUpPhaseObject () {
		companyProjectForth_Phases = new CompanyProject();
	}

	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 201
	 * Date Tested: 27.04.2018
	 * Test Result: FAIL
	 * Notes: Checks that the default constructor is created correctly and that array lists are initialised
	 * 		
	 * 		  Checks project phase id is 0 i.e. the start of an array
	 * 		  Checks the email count for the project is 0
	 */
	
	public void testDefaultConstructor() {
		assertEquals(1, companyProjectFirst_Empty.getPID());
		
		assertEquals(kDEFAULTTITLE, companyProjectFirst_Empty.getPTitle());
		assertTrue(companyProjectFirst_Empty.getProjectContacts().isEmpty());
		
		assertEquals(0, companyProjectFirst_Empty.getPhaseByID());
		assertEquals(0, companyProjectFirst_Empty.getEmailsForPhase().size());
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 202
	 * Date Tested: 30.04.2018
	 * Test Result: FAIL
	 * Notes: Checks that the main constructor is created correctly and that the title is read
	 */
	public void testMainConstructor() {
		/* Checks that the GlobalProjectCounter is incremented in PID - JN: 30.04.18 */
		assertEquals(3, companyProjectThird_Complete.getPID());
		
		assertEquals(kPTITLE1, companyProjectThird_Complete.getPTitle());
		assertFalse(companyProjectThird_Complete.getProjectContacts().isEmpty());
		
		assertEquals(0, companyProjectThird_Complete.getPhaseByID());
		assertEquals(0, companyProjectThird_Complete.getEmailsForPhase().size());
	}

	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 203
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks that PID inits from 1 on creation
	 */
	public void testGetPID() {
		assertEquals(1, companyProjectFirst_Empty.getPID());
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 204
	 * Date Tested: 27.04.2018
	 * Test Result: PASS
	 * Notes: Checks that PID increments correctly after two more projects are made
	 */
	public void testGetPID_SecondStage() {
		assertEquals(3, companyProjectThird_Complete.getPID());
	}
	
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 205
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks that the project title is initialised correctly and can be returned
	 */
	public void testGetPTitle() {
		assertEquals("New Project", companyProjectFirst_Empty.getPTitle());
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 206
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks that the title cannot be set if it is less than 10 characters long and should initialise title correctly
	 */
	public void testSetPTitleShort() {
		companyProjectFirst_Empty.setPTitle(kPTITLE1);
		assertEquals("New Project", companyProjectFirst_Empty.getPTitle());
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 207
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks that the title can be set and called again if more than 10 chars long
	 */
	public void testSetPTitle() {
		companyProjectFirst_Empty.setPTitle(kPTITLE2);
		assertEquals(kPTITLE2, companyProjectFirst_Empty.getPTitle());
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 208
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks if contact exists in newly created Array List
	 */
	public void testIsContact() {
		/* Check if contact exists in empty Array List - JN: 25.04.18 */
		assertFalse(companyProjectFirst_Empty.isContact(kCONTACT1));
	}
	
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 209
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 */
	public void testIsContact_SecondStage() {
		/* Add a new contact to check if it can be read back - JN: 25.04.18 */
		companyProjectFirst_Empty.addContact(kCONTACT1);
		assertTrue(companyProjectFirst_Empty.isContact(kCONTACT1));
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 210
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 */
	public void testAddContact() {
		/* Add two new contacts to check if they can be read back - JN: 25.04.18 */
		companyProjectFirst_Empty.addContact(kCONTACT2);
		companyProjectFirst_Empty.addContact(kCONTACT3);
		assertTrue(companyProjectFirst_Empty.isContact(kCONTACT2));
		assertTrue(companyProjectFirst_Empty.isContact(kCONTACT3));
	}
	
	
	@Test
	/*
	 * Author: Christian Stubbs
	 * Co-author: Justin Ng
	 * Test ID: 209
	 * Date Tested: 27.04.2018
	 * Test Result: Pass
	 * Notes: checks if the number of emails is the same size as the array (0)
	 */
	public void testGetEmailsforPhase() {
		assertEquals(0, companyProjectForth_Phases.getEmailsForPhase().size());
	}
	
	
	
	@Test
	/*
	 * Author: Christian Stubbs
	 * Co-author: Justin Ng
	 * Test ID: 210
	 * Date Tested: 27.04.2018
	 * Test Result: Pass
	 * Notes: changed number of emails in array. checks if passes in different array sizes
	 */
	public void testGetEmailsforPhase_FirstStage() {
		companyProjectForth_Phases.addEmail(new CompanyEmail (kSENDER, kRECIPIENT, kSUBJECT, kBODY1));
		assertEquals(1, companyProjectForth_Phases.getEmailsForPhase().size());
	}
	
	@Test
	/*
	 * Author: Christian Stubbs
	 * Co-author: Justin Ng
	 * Test ID: 211
	 * Date Tested: 27.04.2018
	 * Test Result: FAIL
	 * Notes: checks the email in array is same as number of emails in relevant phase, 
	 * 		  then advances the phase and checks count is 0. 
	 * 		  Fails due to predicted bug in nextPhase() 		 
	 */
	public void testGetEmailsforPhase_AdvStage_1000Emails() {
		/*Creating 1000 emails */
		for (int i = 0 ; i < 1000; i++) {
			companyProjectForth_Phases.addEmail(new CompanyEmail (kSENDER, kRECIPIENT, kSUBJECT, kBODY1));
		}
		assertEquals(1000, companyProjectForth_Phases.getEmailsForPhase().size());	
		companyProjectForth_Phases.nextPhase();
		/* Next phase emails should now be 0 */
		assertEquals(0, companyProjectForth_Phases.getEmailsForPhase().size());	
	}
	
	
	@Test
	/*Author: Christian Stubbs
	 * Co-author: Justin Ng
	 * Test ID: 212
	 * Date Tested: 27.04.2018
	 * Test Result: FAIL
	 * Notes: checks the email in array in current stage does not continue to be accessible 
	 * when moving through stages. each stage has a unique number of emails
	 * Fails due to predicted bug in nextPhase() 		 	
	 */
	public void testGetEmailsforPhase_6Stage_1000Emails() {	
		for (int i = 0 ; i < 6; i++) {
			for (int y = 0; y < i; y ++) {
				companyProjectForth_Phases.addEmail(new CompanyEmail (kSENDER, kRECIPIENT, kSUBJECT, kBODY1));
			}
			assertEquals(i, companyProjectForth_Phases.getEmailsForPhase().size());
			companyProjectForth_Phases.nextPhase();
		}
	}
	
	@Test
	/*
	 * Author: Christian Stubbs
	 * Co-author: Justin Ng
	 * Test ID: 213
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: checks the email in array is same as number of emails in the chosen phase (first), 
	 *  fails due to phase starting at '1' and not 0 as it should.
	 */	    
	public void testGetEmailsforChosenPhase_FirstPhase() {
		assertEquals(0, companyProjectForth_Phases.getEmailsForPhase(0).size());		
	}
	
	@Test
	/*Author: Christian Stubbs
	 * Co-author: Justin Ng
	 * Test ID: 214
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: checks the no. emails in array is same as number of emails in all phases, 
	 *  fails due to phase starting at '1' and not 0 as it should. Furthermore fails due to Arraylist not being created (bug)
	 */
	public void testGetEmailsforChosenPhase_AllPhases() {
		for (int i = 0; i < 6; i++) {
			for (int y = 0; y < i; y++) {
				companyProjectForth_Phases.addEmail(new CompanyEmail (kSENDER, kRECIPIENT, kSUBJECT, kBODY1));
				companyProjectForth_Phases.nextPhase();
			}
			assertEquals(i, companyProjectForth_Phases.getEmailsForPhase(i).size());	
		}
	}
	
	
	@Test
	/*
	 * Author: Christian Stubbs
	 * Co-author: Justin Ng
	 * Test ID: 215
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: checks the no. emails in array is same as number of emails in selected phase AFTER advancing phase, ensuring
	 * 	Emails are not removed.
	 *  Fail due to phase starting at '1' and not 0 as it should. Furthermore, fails due to Arraylist not being created (bug)
	*/
	public void testGetEmailsforChosenPhase_FirstPhaseAfterAdvance() { 
		companyProjectForth_Phases.addEmail(new CompanyEmail (kSENDER, kRECIPIENT, kSUBJECT, kBODY1));
		companyProjectForth_Phases.nextPhase();
		assertEquals(1, companyProjectForth_Phases.getEmailsForPhase(0).size()); 
	}
	
	
	
	@Test
	/*
	 * Author: Christian Stubbs
	 * Co-author: Justin Ng
	 * Test ID: 216
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: advances the phase once. Checks if the phase is equal to 1. 
	 *  Fail due to phase starting at '1' and not 0 as it should. Furthermore, fails due to Arraylist not being created (bug)
	 */
	public void testNextPhase_FromFirst() {  
		companyProjectForth_Phases.nextPhase();
		assertEquals(1, companyProjectForth_Phases.getPhaseByID());
	}
	
	@Test
	/*
	 * Author: Christian Stubbs
	 * Co-author: Justin Ng
	 * Test ID: 217
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: advances the phase to the last phase. Checks if the phase is equal to 5 (last phase). 
	 *  Fail due to phase starting at '1' and not 0 as it should. Furthermore, fails due to Arraylist not being created (bug)
	 */
	public void testNextPhase_AdvanceLast() { 
		for (int i = 0; i < 6; i++) {
			companyProjectForth_Phases.nextPhase();
		}
		assertEquals(5, companyProjectForth_Phases.getPhaseByID());
	}
	
	
	@Test
	/*
	 * Author: Christian Stubbs
	 * Co-author: Justin Ng
	 * Test ID: 218
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: tries to advance the phase 1 past the last phase. Checks if the phase advances beyond it. 
	 * Should remain at last phase.
	 * Fail due to phase starting at '1' and not 0 as it should. Furthermore, fails due to Arraylist not being created (bug)
	 * Can't see if goes past phase 6 until bug fixed. 
	 */	
	public void testNextPhase_PastFinal() {
		for (int i = 0; i < 6; i++) {
			companyProjectForth_Phases.nextPhase();
		}
		companyProjectForth_Phases.nextPhase();
		assertEquals(5, companyProjectForth_Phases.getPhaseByID());
	}
	
	
	@Test
	/*
	 * Author: Christian Stubbs
	 * Co-author: Justin Ng
	 * Test ID: 219
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: Gets the Phase of project when initially created
	 * Reason for failing likely due to mismatched project phase numbers 
	 */	
	public void testGetPhaseName_InitialPhase() {
		assertEquals(CompanyEmailSystem.ProjectPhases[0],companyProjectForth_Phases.getPhaseByName());
	}
	
	@Test
	/*
	 * Author: Christian Stubbs
	 * Co-author: Justin Ng
	 * Test ID: 220
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: Tests the get Phase name method when moving project to next phase
	 * Reason for failing likely due to mismatched project phase numbers 
	 */	
	public void testGetPhaseName_MoveNext() {
		companyProjectForth_Phases.nextPhase();
		int phaseID = companyProjectForth_Phases.getPhaseByID();
		assertEquals(CompanyEmailSystem.ProjectPhases[phaseID],companyProjectForth_Phases.getPhaseByName());
	}
	
	@Test
	/*Author: Christian Stubbs
	 * Co-author: Justin Ng
	 * Test ID: 221
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: Test to see if phase name is still the last phase name despite attempting to move phase after 6
	 * Reason for failing likely due to mismatched project phase numbers 
	 */	
	public void testGetPhaseName_MovePastEnd() {
		for (int i = 0; i < lengthProjects; i++) {
			companyProjectForth_Phases.nextPhase();
		}
		int phaseID = lengthProjects;
		assertEquals(CompanyEmailSystem.ProjectPhases[phaseID],companyProjectForth_Phases.getPhaseByName());
	}


	@Test
	/*
	 * Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 213
	 * Date Tested: 25.04.2018
	 * Test Result: FAIL
	 * Notes: Checks phaseID value of newly initiated object as set by constructor
	 * 		  To start of array
	 */
	
	public void testGetPhaseID_FirstStage() {
		assertEquals(0, companyProjectFirst_Empty.getPhaseByID());
	}
	
	
	@Test
	/*
	 * Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 214
	 * Date Tested: 25.04.2018
	 * Test Result: FAIL
	 * Notes: Checks phaseID value of a project moved to second stage
	 * 		  Also checks correct return value of nextPhase method
	 */
	
	public void testGetPhaseID_SecondStage() {
		assertTrue(companyProjectFirst_Empty.nextPhase());
		assertEquals(1, companyProjectFirst_Empty.getPhaseByID());
	}

	@Test
	/*
	 * Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: ?
	 * Date Tested: 25.04.2018
	 * Test Result: FAIL
	 * Notes: Checks phaseID value of a project moved from first phase until last 
	 * 		  in the ProjectPhases array
	 * 		  Also checks correct return value of nextPhase method
	 */
	
	public void testGetPhaseID_AllStages() {	
		for (int i = 0; i < lengthProjects; i++, companyProjectSecond_Phases.nextPhase()) {
			assertEquals(i, companyProjectSecond_Phases.getPhaseByID());
		}
	}
	
	@Test
	/*
	 * Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 219
	 * Date Tested: 25.04.2018
	 * Test Result: FAIL 
	 * Notes: Checks phaseID value of a project when trying to move
	 * 		  passed the limit of the list of projects.
	 * 		  Also checks correct return value of nextPhase method
	 */
	public void testGetPhaseID_FinalStageToNext() {
		
		for (int i = 0; i < lengthProjects ; i++, companyProjectSecond_Phases.nextPhase())
			;
		assertEquals(5, companyProjectSecond_Phases.getPhaseByID());
	}
	
	@Test
	/*
	 * Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 220
	 * Date Tested: 25.04.2018
	 * Test Result: FAIL
	 * Notes: Checks phaseID value of a project moved back a stage
	 * 		  from the final stage.
	 * 		  Also checks correct return value of nextPhase method
	 */
	public void testGetPhaseID_FirstStageToPrevious () {
		/* there is no previous phase method in place to test this
			assertFalse(project1.nextPhase());
		*/
		assertEquals(4, companyProjectSecond_Phases.getPhaseByID());
	}
	
	@Test 
	/*	
	 *  Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 221
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks get contacts method returns an Array List
	 * 		  
	 */
	
	public void testGetProjectContacts_TypeCheckArrayList () {
		assertThat(companyProjectFirst_Empty.getProjectContacts(), instanceOf(ArrayList.class));
	}
	
	@Test 
	/*	
	 *  Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 222
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks get contacts method returns and the 
	 *        first element is equal to kCONTACT1 constant 
	 */
	
	public void testGetProjectContacts_FirstElement () {
		assertEquals(kCONTACT1, companyProjectFirst_Empty.getProjectContacts().get(0));
	}
	
	@Test 
	/*	
	 *  Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 224
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks get contacts method type of element inside the ArrayList
	 * 		  is of type string
	 */
	
	public void testGetProjectContacts_ElementType () {
		assertThat(companyProjectFirst_Empty.getProjectContacts().get(0), instanceOf(String.class));
	}
	
	
	
	@Test 
	/*	
	 *  Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 225
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks toString method returns value is of type string
	 */
	
	public void testToStringOverride_TypeCheck () {
		assertThat(companyProjectFirst_Empty.toString(), instanceOf(String.class));
	}
	
	
	@Test 
	/*	
	 *  Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 227
	 * Date Tested: 25.04.2018
	 * Test Result: FAIL
	 * Notes: Checks toString method returns correct String 
	 * 		  In the form "Project Title [Project Phase]
	 */
	
	public void testToStringOverride_ValueCheck () {
		String expected = kPTITLE1 + " [" + CompanyEmailSystem.ProjectPhases[0] + "]";
		assertEquals(expected, companyProjectThird_Complete.toString());
	}
	
	@Test 
	/*	
	 *  Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 226
	 * Date Tested: 25.04.2018
	 * Test Result: FAIL
	 * Notes: Checks toString method returns correct String 
	 * 		  In the form "Project Title [Project Phase]
	 * 		  for all stages of the project
	 */
	
	public void testToStringOverride_ValueCheck_AllStages () {
		String expected;
		for (int i = 0; i < lengthProjects ; i++) {
			 expected = kPTITLE1 + " [" + CompanyEmailSystem.ProjectPhases[i] + "]";
			 assertEquals(expected, companyProjectThird_Complete.toString());
			 companyProjectThird_Complete.nextPhase();
		}	
	}
		
}
