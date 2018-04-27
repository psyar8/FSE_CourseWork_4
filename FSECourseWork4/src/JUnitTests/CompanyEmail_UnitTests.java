package JUnitTests;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import EmailSystem.CompanyEmail;

public class CompanyEmail_UnitTests {
	
	final String kSENDER = "joe.bloggs@gmail.com";
	final String kRECIPIENT = "max.power@live.com";
	final String kSUBJECT = "RE: Lorem ipsum";
	final String kBODY1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
	final String kBODY2 = "This is a test email for using unit testing";
	final String kBODY3 = "";
	final String kBODY4 = "Test Email";
	
	CompanyEmail nullEmail;
	CompanyEmail populatedEmail;
	CompanyEmail senderEmail;
	CompanyEmail noRecipientEmail;
	CompanyEmail noEmailBody;
	
	@Before
	public void setup() {
		nullEmail = new CompanyEmail();
		populatedEmail = new CompanyEmail(kSENDER, kRECIPIENT, kSUBJECT, kBODY1);
		senderEmail = new CompanyEmail(kSENDER, null, null, null);
		noRecipientEmail = new CompanyEmail(kSENDER, null, kSUBJECT, kBODY1);
		noEmailBody = new CompanyEmail(kSENDER, kRECIPIENT, kSUBJECT, null);	
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
		assertEquals(kSENDER, givenFromAddress);
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
		assertEquals(kRECIPIENT, givenToAddress);
		assertEquals(null, nullToAddress);
	}
	
	@Test
	/* 
	 * Testing subjectLine()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 109
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 */
	public void testSubjectLineNull() {
		assertEquals(null, nullEmail.subjectLine()); 
	}
	

	 /* 
	 * Testing subjectLine()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 110
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 */
	
	@Test
	public void testSubjectLineNotNull() {
		assertNotNull(populatedEmail.subjectLine());
	}
	
	
	@Test
	/* 
	 * Testing emaiLMessage()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 111
	 * Date Tested: 26.04.2018
	 * Test Result: FAIL
	 */
	
	public void testSubjectBody() {
		assertNotNull(populatedEmail.emailMessage());
	}
	

	@Test
	/* 
	 * Testing emaiLMessage()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 112
	 * Date Tested: 27.04.2018
	 * Test Result: FAIL
	 */	
	public void testSubjectBodyNull(){
		assertNull(nullEmail.emailMessage());
	}


	@Test
	/* 
	 * Testing setFrom()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 113
	 * Date Tested: 27.04.2018
	 * Test Result: PASS
	 */	
	public void testSetFromCorrectFormat(){
		populatedEmail.setFrom(kSENDER);
		assertEquals(kSENDER, populatedEmail.fromAddress());
	}
	
	@Test
	/* 
	 * Testing setFrom()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 114
	 * Date Tested: 27.04.2018
	 * Test Result: FAIL
	 */	
	public void testSetFromIncorrectFormat(){
		assertFalse(populatedEmail.setFrom("joe.bloggs"));
	}
	
	
	@Test
	/* 
	 * Testing setTo()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 115
	 * Date Tested: 27.04.2018
	 * Test Result: PASS
	 */	
	public void testSetToCorrectFormat(){
		
		populatedEmail.setTo(kRECIPIENT);
		assertEquals(kRECIPIENT, populatedEmail.toAddress());
	}
	
	@Test
	/* 
	 * Testing setTo()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 116
	 * Date Tested: 27.04.2018
	 * Test Result:FAIL
	 */	
	public void testSetToIncorrectFormat() {
		assertFalse(populatedEmail.setTo("joe.bloggs"));

	}

	@Test
	/* 
	 * Testing setSubject()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 117
	 * Date Tested: 27.04.2018
	 * Test Result:PASS
	 */	
	public void testSetSubjectStringPassed() {
		populatedEmail.setSubject(kSUBJECT);
		assertEquals(kSUBJECT, populatedEmail.subjectLine());
	}
	
	@Test
	/* 
	 * Testing setSubject()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 118
	 * Date Tested: 27.04.2018
	 * Test Result: FAIL
	 */	
	public void testSetSubjectNullPassed() {
		populatedEmail.setSubject(null);
		assertEquals(null, populatedEmail.subjectLine());
	}
	
	
	@Test
	/* 
	 * Testing: setMessage()
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 119
	 * Date Tested: 26.04.2018
	 * Test Result: FAIL
	 * Notes: Checks value String body is set in the object to the constant used
	 * 		  To check method call to emailMessage to retrieve
	 */
	
	public void setEmailBody_NormalString () {
		noEmailBody.setMessage(kBODY1);
		/*Now need to use emailMessage method to check it was input*/
		assertEquals(kBODY1, noEmailBody.emailMessage());
	}
	
	@Test
	/* 
	 * Testing: setMessage()
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 120
	 * Date Tested: 26.04.2018
	 * Test Result: FAIL
	 * Notes: Checks value String emailBody is an empty string using the constant defined
	 * 		  To check method call to emailMessage to retrieve
	 */
	
	public void setEmailBody_EmptyString () {
		noEmailBody.setMessage(kBODY3);
		/*Now need to use emailMessage method to check it was input*/
		assertEquals(kBODY2, noEmailBody.emailMessage());
	}
	
	@Test
	/* 
	 * Testing: isValid()
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 121
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when all attributes are not set
	 */
	public void checkValidity_AllNull () {
		assertFalse(nullEmail.isValid());
	}
	
	@Test
	/* 
	 * Testing: isValid()
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 122
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when all attributes are set
	 */
	public void checkValidity_AllSet () {
		assertTrue(populatedEmail.isValid());
	}
	
	@Test
	/* Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 123
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when only one attribute is set
	 * 		  at once. This case is setFrom
	 */
	
	public void checkValidity_Individual_SetFrom () {
		nullEmail.setFrom(kSENDER);
		assertFalse(nullEmail.isValid());
	}
	
	@Test
	/* Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 124
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when only one attribute is set
	 * 		  at once. This case is setTo
	 */
	public void checkValidity_Individual_SetTo () {
		nullEmail.setTo(kRECIPIENT);
		assertFalse(nullEmail.isValid());
	}
	
	@Test
	/* Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 125
	 * Date Tested: 26.04.2018	
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when only one attribute is set
	 * 		  at once. This case is setSubject
	 */
	public void checkValidity_Individual_SetSubject () {
		nullEmail.setSubject(kSUBJECT);
		assertFalse(nullEmail.isValid());	
	}
	
	@Test
	/* Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 126
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when only one attribute is set
	 * 		  at once. This case is setMessage
	 */
	public void checkValidity_Individual_SetMessage () {
		nullEmail.setMessage(kBODY1);
		assertFalse(nullEmail.isValid());	
	}
	
	@Test 
	/*	
	 * Testing: toString()
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 127
	 * Date Tested: PASS
	 * Test Result: 26.04.2018
	 * Notes: Checks toString method returns value is of type string
	 */
	
	public void toStringOverride_TypeCheck () {
		assertThat(populatedEmail.toString(), instanceOf(String.class));
	}
	
	@Test 
	/*	
	 * Testing: toString()
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 128
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks toString method returns correct String 
	 * 		  In the form Correct Subject set
	 */
	
	public void toStringOverride_ValueCheck_SetSubject () {
		nullEmail.setSubject(kSUBJECT);
		assertEquals(kSUBJECT, nullEmail.toString());
	}
	
	@Test 
	/*	
	 * Testing: toString()
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 129
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks toString method returns correct String 
	 * 		  When subject is empty string
	 */
	
	public void toStringOverride_ValueCheck_EmptyString () {
		nullEmail.setSubject("");
		assertEquals("[no subject]", nullEmail.toString());
	}
	
	@Test 
	/*	
	 * Testing: toString()
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 130
	 * Date Tested: 26.04.2018
	 * Test Result: FAIL
	 * Notes: Checks toString method returns correct String 
	 * 		  When subject is not set at all
	 */
	
	public void toStringOverride_ValueCheck_Null () {
		assertEquals("[no subject]", nullEmail.toString());
	}

}
