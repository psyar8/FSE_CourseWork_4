import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CompanyEmailTestRam {
	
	public final String SENDER = "joe.bloggs@gmail.com";
	public final String RECIPIENT = "max.power@live.com";
	public final String SUBJECT = "RE: Lorem ipsum";
	public final String BODY = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
	
	CompanyEmail nullEmail;
	CompanyEmail populatedEmail;
	CompanyEmail senderEmail;
	CompanyEmail noRecipientEmail;
	
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
	 * Test Result: PASS
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
	 * Test Result: PASS
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
	 * Test Result: PASS
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
	 * Test Result: PASS
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
	
}