

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

/* FixMethodOrder is used to ensure tests run sequentially as they are written - AR: 25.04.18 */
@FixMethodOrder(MethodSorters.JVM)
public class CompanyProjectTestAidan {

	static CompanyProject project1;
	/* Before class is run once at the very start of the test - AR: 25.04.18 
	 * Test checked to make sure object is created
	 * */
	@BeforeClass
	public static void settingUp() {
		project1 = new CompanyProject();
		assertNotNull(project1);
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
	 * Test ID: 215
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks phaseID value of a project moved to third stage
	 * 		  Also checks correct return value of nextPhase method
	 */
	public void getPhaseID_ThirdStage() {
		assertTrue(project1.nextPhase());
		assertEquals(3, project1.getPhaseByID());
	}
	
	@Test
	/*
	 * Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 216
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks phaseID value of a project moved to forth stage
	 * 		  Also checks correct return value of nextPhase method
	 */
	public void getPhaseID_ForthStage() {
		assertTrue(project1.nextPhase());
		assertEquals(4, project1.getPhaseByID());
	}
	
	@Test
	/*
	 * Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 217
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks phaseID value of a project moved to fith stage
	 * 		  Also checks correct return value of nextPhase method
	 */
	public void getPhaseID_FifthStage() {
		assertTrue(project1.nextPhase());
		assertEquals(5, project1.getPhaseByID());
	}
	
	@Test
	/*
	 * Author: Aidan Reed
	 * Co-Author: Christian Stubbs
	 * Test ID: 218
	 * Date Tested: 25.04.2018
	 * Test Result: PASS
	 * Notes: Checks phaseID value of a project moved to final (6th) stage
	 * 		  Also checks correct return value of nextPhase method
	 */
	
	public void getPhaseID_FinalStage() {
		assertTrue(project1.nextPhase());
		assertEquals(6, project1.getPhaseByID());
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
		assertFalse(project1.nextPhase());
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
	public void getPhaseID_FirstStageToPrevious() {
		/* there is no previous phase method in place to test this
			assertFalse(project1.nextPhase());
		*/
		assertEquals(5, project1.getPhaseByID());
	}
	
	@AfterClass
	public static void cleanUpTests () {
		/*Removes the object reference free for garbage collection - AR: 25.04.18 */
		project1 = null;
	}
	
	

}
