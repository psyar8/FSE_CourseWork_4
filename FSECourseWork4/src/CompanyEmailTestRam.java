import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.*;


public class CompanyEmailTestRam {
	
	private final String SENDER = "joe.bloggs@gmail.com";
	private final String RECIPIENT = "max.power@live.com";
	private final String SUBJECT = "RE: Lorem ipsum";
	private final String BODY = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
	
	private CompanyEmail nullEmail;
	private CompanyEmail populatedEmail;
	private CompanyEmail senderEmail;
	private CompanyEmail noRecipientEmail;
	
	@Before
	public void setup() {
		nullEmail = new CompanyEmail();
		populatedEmail = new CompanyEmail(SENDER, RECIPIENT, SUBJECT, BODY);
		senderEmail = new CompanyEmail(SENDER, null, null, null);
		noRecipientEmail = new CompanyEmail(SENDER, null, SUBJECT, BODY);
	}
	
	@Test
	/* 
	 * Testing Default Constructor
	 * Author: Ram Raja
	 * Co-Author: Athullya Roy
	 * Test ID: 101
	 * Date Tested: 26.04.2018
	 * Test Result: FAIL
	 */
	public void testDefaultConstructor() {
		assertNull(nullEmail.fromAddress());
		assertNull(nullEmail.toAddress());
		assertNull(nullEmail.subjectLine());
		assertNull(nullEmail.emailMessage());
	}
	
	@Test
	/* 
	 * Testing Main Constructor - Values assigned to all parameters
	 * Author: Ram Raja
	 * Co-Author: Athullya Roy
	 * Test ID: 102
	 * Date Tested: 26.04.2018
	 * Test Result: FAIL
	 */
	public void testMainConstructor_allParam() {
		assertNotNull(populatedEmail.fromAddress());
		assertNotNull(populatedEmail.toAddress());
		assertNotNull(populatedEmail.subjectLine());
		assertNotNull(populatedEmail.emailMessage());
	}
	
	@Test
	/* 
	 * Testing Main Constructor - Values assigned to all parameters except recipient
	 * Author: Ram Raja
	 * Co-Author: Athullya Roy
	 * Test ID: 103
	 * Date Tested: 26.04.2018
	 * Test Result: FAIL
	 */
	public void testMainConstructor_recipientParam() {
		assertNotNull(noRecipientEmail.fromAddress());
		assertNull(noRecipientEmail.toAddress());
		assertNotNull(noRecipientEmail.subjectLine());
		assertNotNull(noRecipientEmail.emailMessage());
	}
	
	@Test
	/* 
	 * Testing Main Constructor - Value only assigned to sender
	 * Author: Ram Raja
	 * Co-Author: Athullya Roy
	 * Test ID: 104
	 * Date Tested: 26.04.2018
	 * Test Result: FAIL
	 */
	public void testMainConstructor_senderParam() {
		assertNotNull(senderEmail.fromAddress());
		assertNull(senderEmail.toAddress());
		assertNull(senderEmail.subjectLine());
		assertNull(senderEmail.emailMessage());
	}
	
	@Test
	/* 
	 * Testing fromAddress()
	 * Author: Ram Raja
	 * Co-Author: Athullya Roy
	 * Test ID: 105/106
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 */
	public void testFromAddress() {
		String givenFromAddress = populatedEmail.fromAddress();
		String nullFromAddress = nullEmail.fromAddress();
		assertEquals(SENDER, givenFromAddress);
		assertEquals(null, nullFromAddress);
	}
	
	@Test
	/* 
	 * Testing toAddress()
	 * Author: Ram Raja
	 * Co-Author: Athullya Roy
	 * Test ID: 107/108
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 */
	public void testToAddress() {
		String givenToAddress = populatedEmail.toAddress();
		String nullToAddress = nullEmail.toAddress();
		assertEquals(RECIPIENT, givenToAddress);
		assertEquals(null, nullToAddress);
	}
	
	@Test
	/* 
	 * Testing accessibility of all fields in CompanyEmail
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 109
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 */
	public void testFieldAccessorType () {
		Field[] companyEmailFields = CompanyEmail.class.getDeclaredFields();
	    for (Field field : companyEmailFields) {
	        int modVal = field.getModifiers();
	        assertTrue(Modifier.isPrivate(modVal));
	        //Fields with a modVal of 2 (private) should return true
	    }
	}
}