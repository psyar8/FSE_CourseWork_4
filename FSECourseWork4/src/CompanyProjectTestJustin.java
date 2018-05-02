import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

/* FixMethodOrder is used to ensure tests run sequentially as they are written - JN: 25.04.18 */
@FixMethodOrder(MethodSorters.JVM)

public class CompanyProjectTestJustin {

	static CompanyProject newCompanyProject;
	/* @BeforeClass is run before tests to ensure a new project is made - JN: 25.04.18 */
	@BeforeClass
	public static void setUp() {
		newCompanyProject = new CompanyProject();
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 201
	 * Date Tested: 27.04.2018
	 * Test Result: FAIL
	 * Notes: Checks that the default constructor is created correctly and that array lists are initialised
	 */
	public void testDefaultConstructor() {
		assertEquals(1, CompanyEmailSystem.GlobalProjectCounter);
		/* Checks that the GlobalProjectCounter is incremented in PID - JN: 27.04.18 */
		assertEquals(1, newCompanyProject.getPID());
		assertEquals("New Project", newCompanyProject.getPTitle());
		assertTrue(newCompanyProject.getProjectContacts().isEmpty());
		/* Check project phase id is 0 - error if not - reason - it should start from 0 */
		assertEquals(0, newCompanyProject.getPhaseByID());
		//newCompanyProject.addEmail(newCompanyEmail);
		assertEquals(0, newCompanyProject.getEmailsForPhase().size());
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
		CompanyProject newCompanyProject_MainConstruct;
		newCompanyProject_MainConstruct = new CompanyProject("TestOneTitle");
		assertEquals(2, CompanyEmailSystem.GlobalProjectCounter);
		/* Checks that the GlobalProjectCounter is incremented in PID - JN: 30.04.18 */
		assertEquals(2, newCompanyProject_MainConstruct.getPID());
		assertEquals("TestOneTitle", newCompanyProject_MainConstruct.getPTitle());
		assertTrue(newCompanyProject_MainConstruct.getProjectContacts().isEmpty());
		/* Check project phase id is 0 - error if not - reason - it should start from 0 */
		assertEquals(0, newCompanyProject_MainConstruct.getPhaseByID());
		assertEquals(0, newCompanyProject_MainConstruct.getEmailsForPhase().size());
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 203.1
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks that PID inits from 1 on creation
	 */
	public void testGetPID() {
		assertEquals(1, newCompanyProject.getPID());
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 203.2
	 * Date Tested: 27.04.2018
	 * Test Result: PASS
	 * Notes: Checks that PID increments correctly after a new project is made
	 */
	public void testGetPID_SecondStage() {
		setUp();
		assertEquals(3, newCompanyProject.getPID());
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 204
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks that the project title is initialised correctly and can be returned
	 */
	public void testGetPTitle() {
		assertEquals("New Project", newCompanyProject.getPTitle());
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 205.1
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks that the title cannot be set if it is less than 10 characters long and should initialise title correctly
	 */
	public void testSetPTitleShort() {
		newCompanyProject.setPTitle("12345678");
		assertEquals("New Project", newCompanyProject.getPTitle());
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 205.2
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks that the title can be set and called again if more than 10 chars long
	 */
	public void testSetPTitle() {
		newCompanyProject.setPTitle("123456789000");
		assertEquals("123456789000", newCompanyProject.getPTitle());
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 206.1
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks if contact exists in newly created Array List
	 */
	public void testIsContact() {
		/* Check if contact exists in empty Array List - JN: 25.04.18 */
		assertFalse(newCompanyProject.isContact("test@gmail.com"));
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 206.2
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks if a contact can be created and read back
	 */
	public void testIsContact_SecondStage() {
		/* Add a new contact to check if it can be read back - JN: 25.04.18 */
		newCompanyProject.addContact("test@gmail.com");
		assertTrue(newCompanyProject.isContact("test@gmail.com"));
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 207.1
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks if multiple contacts can be created and read back
	 */
	public void testAddContact() {
		/* Add two new contacts to check if they can be read back - JN: 25.04.18 */
		newCompanyProject.addContact("raiu9s@gmail.com");
		newCompanyProject.addContact("q39ikdf@outlook.com");
		assertTrue(newCompanyProject.isContact("raiu9s@gmail.com"));
		assertTrue(newCompanyProject.isContact("q39ikdf@outlook.com"));
	}
}
