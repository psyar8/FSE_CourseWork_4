
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;



/* FixMethodOrder is used to ensure tests run sequentially as they are written - AR: 25.04.18 */
@FixMethodOrder(MethodSorters.JVM)
public class CompanyProjectTestAidan {

	CompanyProject project1;
	int lengthProjects = CompanyEmailSystem.ProjectPhases.length;
	String kCONTACT1 = "me@me.com";
	String kCONTACT2 = "bigben@me.com";
	String kCONTACT3 = "london@me.com";
	String kPROTITLE = "New Email Platform";
	
	/* Before  is run once at the very start of the test - AR: 25.04.18 
	 * Test checked to make sure object is created
	 * */
	@Before
	public  void settingUp() {
		project1 = new CompanyProject(kPROTITLE);
		assertNotNull(project1);
		project1.addContact(kCONTACT1);
		project1.addContact(kCONTACT2);
		project1.addContact(kCONTACT3);
	}
	
	@Test
	/*
	 * Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 213
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks phaseID value of newly initiated object
	 */
	
	public void getPhaseID_FirstStage() {
		assertEquals(1, project1.getPhaseByID());
	}
	
	
	@Test
	/*
	 * Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 214
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks phaseID value of a project moved to second stage
	 * 		  Also checks correct return value of nextPhase method
	 */
	
	public void getPhaseID_SecondStage() {
		assertTrue(project1.nextPhase());
		assertEquals(2, project1.getPhaseByID());
	}
	
	
	@Test
	/*
	 * Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: ?
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks phaseID value of a project moved from 2 until 6th
	 * 		  Also checks correct return value of nextPhase method
	 */
	
	public void getPhaseID_AllStages() {
		
		for (int i = 1; i <= lengthProjects; i++, project1.nextPhase()) {
			assertEquals(i, project1.getPhaseByID());
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
	public void getPhaseID_FinalStageToNext() {
		
		for (int i = 1; i <= lengthProjects; i++, project1.nextPhase())
			;
		assertEquals(6, project1.getPhaseByID());
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
	public void getPhaseID_FirstStageToPrevious () {
		/* there is no previous phase method in place to test this
			assertFalse(project1.nextPhase());
		*/
		assertEquals(5, project1.getPhaseByID());
	}
	
	
	@Test 
	/*	
	 *  Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 222
	 * Date Tested: 
	 * Test Result: 
	 * Notes: Checks get contacts method returns an Array List
	 * 		  
	 */
	
	public void getProjectContacts_TypeCheckArrayList () {
		assertThat(project1.getProjectContacts(), instanceOf(ArrayList.class));
	}
	
	
	@Test 
	/*	
	 *  Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 223
	 * Date Tested: 
	 * Test Result: 
	 * Notes: Checks get contacts method returns and the 
	 *        first element is equal to kCONTACT1 constant 
	 */
	
	public void getProjectContacts_FirstElement () {
		assertEquals(kCONTACT1, project1.getProjectContacts().get(0));
	}
	
	
	
	@Test 
	/*	
	 *  Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 224
	 * Date Tested: 
	 * Test Result: 
	 * Notes: Checks get contacts method type of element inside the ArrayList
	 * 		  is of type string
	 */
	
	public void getProjectContacts_ElementType () {
		assertThat(project1.getProjectContacts().get(0), instanceOf(String.class));
	}
	
	
	
	@Test 
	/*	
	 *  Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 225
	 * Date Tested: 
	 * Test Result: 
	 * Notes: Checks toString method returns value is of type string
	 */
	
	public void toStringOverride_TypeCheck () {
		assertThat(project1.toString(), instanceOf(String.class));
	}
	
	
	@Test 
	/*	
	 *  Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 226
	 * Date Tested: 
	 * Test Result: 
	 * Notes: Checks toString method returns correct String 
	 * 		  In the form "Project Title [Project Phase]
	 */
	
	public void toStringOverride_ValueCheck () {
		String expected = kPROTITLE + " [" + CompanyEmailSystem.ProjectPhases[0] + "]";
		assertEquals(expected, project1.toString());
	}
	
	
	@Test 
	/*	
	 *  Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 226
	 * Date Tested: 
	 * Test Result: 
	 * Notes: Checks toString method returns correct String 
	 * 		  In the form "Project Title [Project Phase]
	 */
	
	public void toStringOverride_ValueCheckFullProcess () {
		
	}
	
	
	@After
	public void cleanUpTests () {
		/*Removes the object reference free for garbage collection - AR: 25.04.18 */
		project1 = null;
	}
	

}
