import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;



/* FixMethodOrder is used to ensure tests run sequentially as they are written - AR: 26.04.18 */
@FixMethodOrder(MethodSorters.JVM)
public class CompanyEmailAidan {

	String kFROMADDR1 = "nottingham@me.co.uk";
	String kTOADDR1 = "london@me.co.uk";
	String kSUBJECT1 = "New Email System Requirement Documents";
	String kEMAILBODY1 = "This is a test email for using unit testing";
	String kEMAILBODY2 = "";
	String kEMAILBODY3 = "Test Email";
	
	CompanyEmail email1;
	CompanyEmail email2;
	CompanyEmail email3;
	
	@Before 
	public void setUp() {
		email1 = new CompanyEmail("kFROMADDR1","kTOADDR1", kSUBJECT1 , null);
		email2 = new CompanyEmail();
		email3 = new CompanyEmail("kFROMADDR1","kTOADDR1", kSUBJECT1 , kEMAILBODY3);
	}
	
	@Test
	/* Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 100
	 * Date Tested: 26.04.2018
	 * Test Result: FAIL
	 * Notes: Checks value String body is set in the object to the constant used
	 * 		  To check method call to emailMessage to retrieve
	 */
	public void setEmailBody_NormalString () {
		email1.setMessage(kEMAILBODY1);
		/*Now need to use emailMessage method to check it was input*/
		assertEquals(kEMAILBODY1, email1.emailMessage());
	}
	
	@Test
	/* Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 101
	 * Date Tested: 26.04.2018
	 * Test Result: FAIL
	 * Notes: Checks value String emailBody is an empty using the constant defined
	 * 		  To check method call to emailMessage to retrieve
	 */
	
	public void setEmailBody_EmptyString () {
		email1.setMessage(kEMAILBODY2);
		/*Now need to use emailMessage method to check it was input*/
		assertEquals(kEMAILBODY2, email1.emailMessage());
	}
	
	@Test
	/* Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 102
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when all attributes are not set
	 */
	public void checkValidity_AllNull () {
		assertFalse(email2.isValid());
	}
	
	@Test
	/* Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 103
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when all attributes are set
	 */
	public void checkValidity_AllSet () {
		assertTrue(email3.isValid());
	}
	
	@Test
	/* Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 104
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when only one attribute is set
	 * 		  at once. This case is setFrom
	 */
	public void checkValidity_Individual_SetFrom () {
		email2.setFrom(kFROMADDR1);
		assertFalse(email2.isValid());
	}
	
	@Test
	/* Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 105
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when only one attribute is set
	 * 		  at once. This case is setTo
	 */
	public void checkValidity_Individual_SetTo () {
		email2.setTo(kTOADDR1);
		assertFalse(email2.isValid());
	}
	

	@Test
	/* Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 106
	 * Date Tested: 26.04.2018	
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when only one attribute is set
	 * 		  at once. This case is setSubject
	 */
	public void checkValidity_Individual_SetSubject () {
		email2.setSubject(kSUBJECT1);
		assertFalse(email2.isValid());	
	}
	
	@Test
	/* Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 107
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when only one attribute is set
	 * 		  at once. This case is setMessage
	 */
	public void checkValidity_Individual_SetMessage () {
		email2.setMessage(kEMAILBODY1);
		assertFalse(email2.isValid());	
	}
	
	
	@Test 
	/*	
	 *  Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 108
	 * Date Tested: PASS
	 * Test Result: 26.04.2018
	 * Notes: Checks toString method returns value is of type string
	 */
	
	public void toStringOverride_TypeCheck () {
		assertThat(email1.toString(), instanceOf(String.class));
	}
	
	
	@Test 
	/*	
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 109
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks toString method returns correct String 
	 * 		  In the form Correct Subject set
	 */
	
	public void toStringOverride_ValueCheck_SetSubject () {
		email2.setSubject(kSUBJECT1);
		assertEquals(kSUBJECT1, email2.toString());
	}
	
	@Test 
	/*	
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 110
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks toString method returns correct String 
	 * 		  When subject is empty string
	 */
	
	public void toStringOverride_ValueCheck_EmptyString () {
		email2.setSubject("");
		assertEquals("[no subject]", email2.toString());
	}
	
	@Test 
	/*	
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 111
	 * Date Tested: 26.04.2018
	 * Test Result: FAIL
	 * Notes: Checks toString method returns correct String 
	 * 		  When subject is not set at all
	 */
	
	public void toStringOverride_ValueCheck_Null () {
		assertEquals("[no subject]", email2.toString());
	}
	
	

}
