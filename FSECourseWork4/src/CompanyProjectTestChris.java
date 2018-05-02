import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CompanyProjectTestChris {
	static CompanyProject newCompanyProject;
	static CompanyProject newCompanyProject1000Email;
	static CompanyProject newCompanyProject1Email;
	static CompanyEmail newcompemail;

	
	@BeforeClass
	public static void setUp() {
		newCompanyProject = new CompanyProject();
		newCompanyProject1000Email = new CompanyProject();
		newCompanyProject1Email = new CompanyProject();
		newcompemail = new CompanyEmail("m@me.co.uk","to@me.co.uk","Subject!","This is a email");		
		
		newCompanyProject1Email.addEmail(newcompemail);
		for (int i = 0; i < 1000; i++) {
			String fromAddr = "" + i + "@me.co.uk";
			String toAddr = "" + i + i + "@me.co.uk";
			String subject = "" + i + "subject";
			String body = "This is a body message";
			newCompanyProject1000Email.addEmail(new CompanyEmail(fromAddr, toAddr, subject, body));
		}
        
	}
	
	@Test
	public void addEmailtest() {
		/*Author: Christian Stubbs
		 * Co-author: Justin Ng
		 * Test ID: 208
		 * Date Tested: 27.04.2018
		 * Test Result: PASS
		 * Notes:
		 */
		assertTrue(newcompemail.isValid());
		if (!newcompemail.isValid())
		{
			fail("addemailtest fail");
		}
		
		
	}
	@Test
	public void ArrayListGetEmailsforPhase() {
			
		/*Author: Christian Stubbs
		 * Co-author: Justin Ng
		 * Test ID: 209
		 * Date Tested: 27.04.2018
		 * Test Result: FAIL
		 * Notes: checks if the number of emails is the same size as the array (0)
		 */
		
		assertEquals(0, newCompanyProject.getEmailsForPhase().size());
		System.out.println(newCompanyProject.getEmailsForPhase().size());

		
	}
	
	@Test
	public void ArrayListGetEmailsforPhase_FirstStage() {
		/*Author: Christian Stubbs
		 * Co-author: Justin Ng
		 * Test ID: 210
		 * Date Tested: 27.04.2018
		 * Test Result: Pass
		 * Notes: changed number of emails in array. checks if passes in different array sizes
		 */
	
		assertEquals(1, newCompanyProject1Email.getEmailsForPhase().size());
		System.out.println(newCompanyProject1Email.getEmailsForPhase());

		
	}
	
	@Test
	public void ArrayListGetEmailsforPhase_AdvStage_1000Emails() {
		
		/*Author: Christian Stubbs
		 * Co-author: Justin Ng
		 * Test ID: 211
		 * Date Tested: 27.04.2018
		 * Test Result: FAIL
		 * Notes: checks the email in array is same as number of emails in relevant phase, 
		 * then advances the phase and repeats previous process. 
		 * Fails due to predicted bug in nextPhase() 		 */
		
		System.out.println(newCompanyProject1000Email.getEmailsForPhase());
		
		newCompanyProject1000Email.nextPhase();	
		newCompanyProject1000Email.addEmail(newcompemail);
		
		System.out.println(newCompanyProject1000Email.getEmailsForPhase());
		
		assertEquals(1000, newCompanyProject1000Email.getEmailsForPhase().size());	
		
	}
	@Test
	public void ArrayListGetEmailsforPhase_6Stage_1000Emails() {
	   /*Author: Christian Stubbs
		* Co-author: Justin Ng
	 * Test ID: 212
	 * Date Tested: 27.04.2018
	 * Test Result: FAIL
	 * Notes: checks the email in array is same as number of emails in the last phase, 
	 * by repeatedly advancing the phase to the last one. 
	 * Fails due to predicted bug in nextPhase() 		 
		
		*/
		
		

		newCompanyProject1000Email.nextPhase();	
		newCompanyProject1000Email.nextPhase();	
		newCompanyProject1000Email.nextPhase();	
		newCompanyProject1000Email.nextPhase();	
		newCompanyProject1000Email.nextPhase();	
		
		
		newCompanyProject1000Email.addEmail(newcompemail);
		System.out.println(newCompanyProject1000Email.getEmailsForPhase()); 
		assertEquals(1000, newCompanyProject1000Email.getEmailsForPhase().size());	
	}
	@Test
	public void ArrayListGetEmailsforChosenPhase1() {

		/*Author: Christian Stubbs
		* Co-author: Justin Ng
	 * Test ID: 213
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: checks the email in array is same as number of emails in the chosen phase (first), 
	 *  fails due to phase starting at '1' and not 0 as it should.
		
		*/	    
		
		assertEquals(0, newCompanyProject.getEmailsForPhase(0).size());		
		
	}
	@Test
	public void ArrayListGetEmailsforChosenPhaseAll() {
		/*Author: Christian Stubbs
		* Co-author: Justin Ng
	 * Test ID: 214
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: checks the no. emails in array is same as number of emails in all phases, 
	 *  fails due to phase starting at '1' and not 0 as it should. Furthermore fails due to Arraylist not being created (bug)
		*/	  
		for (int i = 0; i < 6; i++) {
			assertEquals(0, newCompanyProject.getEmailsForPhase(i).size());	
		}
	}

	
	@Test
	public void ArrayListGetEmailsforChosenPhase1Email() {
		/*Author: Christian Stubbs
		* Co-author: Justin Ng
	 * Test ID: 215
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: checks the no. emails in array is same as number of emails in selected phase AFTER advancing phase, 
	 *  Fail due to phase starting at '1' and not 0 as it should. Furthermore, fails due to Arraylist not being created (bug)
		*/	  
		newCompanyProject1Email.nextPhase();
		assertEquals(1, newCompanyProject1Email.getEmailsForPhase(0).size());
		System.out.println(newCompanyProject1Email.getEmailsForPhase(0)); 
	}

	//Next Phase Tests
	
	@Test
	public void nextPhase1time() {
		/*Author: Christian Stubbs
		* Co-author: Justin Ng
	 * Test ID: 216
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: advances the phase once. Checks if the phase is equal to 1. 
	 *  Fail due to phase starting at '1' and not 0 as it should. Furthermore, fails due to Arraylist not being created (bug)
		*/	  
		newCompanyProject.nextPhase();
		assertEquals(1, newCompanyProject.getPhaseByID());
	}
	
	@Test
	public void nextPhase5time() {
		/*Author: Christian Stubbs
		* Co-author: Justin Ng
	 * Test ID: 217
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: advances the phase to the last phase. Checks if the phase is equal to 5 (last phase). 
	 *  Fail due to phase starting at '1' and not 0 as it should. Furthermore, fails due to Arraylist not being created (bug)
		*/	  
		for (int i = 0; i <5; i++) {
			newCompanyProject.nextPhase();
		}
		assertEquals(5, newCompanyProject.getPhaseByID());
	}	
	@Test
	public void nextPhasePastFinal() {
		/*Author: Christian Stubbs
		* Co-author: Justin Ng
	 * Test ID: 218
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: tries to advance the phase 1 past the last phase. Checks if the phase advances beyond it. 
	 * Should remain at last phase.
	 *  Fail due to phase starting at '1' and not 0 as it should. Furthermore, fails due to Arraylist not being created (bug)
		Can't see if goes past phase 6 until bug fixed. */	  
		for (int i = 0; i <6; i++) {
			newCompanyProject.nextPhase();
		}
		newCompanyProject.nextPhase();
		assertEquals(5, newCompanyProject.getPhaseByID());
	}	

	//Get Phase Tests
	
	
	@Test
	public void getphasename1() {
		/*Author: Christian Stubbs
		* Co-author: Justin Ng
	 * Test ID: 219
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: Reason for failing likely due to mismatched project phase numbers */	
		assertEquals("Feasibility",newCompanyProject.getPhaseByName());
		System.out.println(newCompanyProject.getPhaseByName());
		
	}
	@Test
	public void getphasenameAscendPhase() {
		/*Author: Christian Stubbs
		* Co-author: Justin Ng
	 * Test ID: 220
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: Reason for failing likely due to mismatched project phase numbers */	
		newCompanyProject.nextPhase();
		assertEquals("Design",newCompanyProject.getPhaseByName());
		System.out.println(newCompanyProject.getPhaseByName());
		
	}
	@Test
	public void getphasenameAscendPhasePast6() {
		/*Author: Christian Stubbs
		* Co-author: Justin Ng
	 * Test ID: 221
	 * Date Tested: 02.05.2018
	 * Test Result: FAIL
	 * Notes: Test to see if phase name is still the last phase name despite attempting to move phase after 6
	 * Reason for failing likely due to mismatched project phase numbers */	
		for (int i = 0; i <7; i++) {
			newCompanyProject.nextPhase();
		}
		assertEquals("Completed",newCompanyProject.getPhaseByName());
		System.out.println(newCompanyProject.getPhaseByName());
	}
}
	


