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
	/* @BeforeClass is run before each and every test to ensure a new project is made every time when necessary - JN: 25.04.18 */
	@BeforeClass
	public static void setUp() {
		newCompanyProject = new CompanyProject();
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
		assertEquals(1, newCompanyProject.getPID());
	}
	
	@Test
	/*
	 * Author: Justin Ng
	 * Co-Author: Christian Stubbs
	 * Test ID: 204
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
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
	 */
	public void testAddContact() {
		/* Add two new contacts to check if they can be read back - JN: 25.04.18 */
		newCompanyProject.addContact("raiu9s@gmail.com");
		newCompanyProject.addContact("q39ikdf@outlook.com");
		assertTrue(newCompanyProject.isContact("raiu9s@gmail.com"));
		assertTrue(newCompanyProject.isContact("q39ikdf@outlook.com"));
	}

}
