package JUnitTests;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import EmailSystem.CompanyEmail;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
@FixMethodOrder(MethodSorters.JVM)
public class CompanyEmail_UnitTests {
	
	final String kSENDER = "joe.bloggs@gmail.com";
	final String kRECIPIENT = "max.power@live.com";
	final String kBADEMAIL1 = "joe.bloggs@";
	final String kBADEMAIL2 = "joe.@bloggs";
	final String kBADEMAIL3 = "joe.bloggs@gmail@.com";
	final String kBADEMAIL4 = "@joe.bloggs@gmail.com";
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
	 * Date Tested: 27.04.2018 | 26.04.2018
	 * Test Result: PASS       | FAIL
	 */
	
	public void testDefaultConstructor_101() {
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
	 * Date Tested: 27.04.2018 | 26.04.2018
	 * Test Result: PASS       | FAIL
	 */
	
	public void testMainConstructor_allParam_102() {
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
	 * Date Tested: 27.04.2018 | 26.04.2018
	 * Test Result: PASS       | FAIL
	 */
	
	public void testMainConstructor_recipientParam_103() {
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
	 * Date Tested: 27.04.2018 | 26.04.2018
	 * Test Result: PASS       | FAIL
	 */
	
	public void testMainConstructor_senderParam_104() {
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
	public void testFromAddress_105_106() {
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
	
	public void testToAddress_107_108() {
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
	public void testSubjectLineNull_109() {
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
	public void testSubjectLineNotNull_110() {
		assertNotNull(populatedEmail.subjectLine());
	}
	
	
	@Test
	/* 
	 * Testing emaiLMessage()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 111
	 * Date Tested: 27.04.2018 | 26.04.2018
	 * Test Result: PASS       | FAIL
	 */
	
	public void testSubjectBody_111() {
		assertNotNull(populatedEmail.emailMessage());
	}
	

	@Test
	/* 
	 * Testing emaiLMessage()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 112
	 * Date Tested: 27.04.2018 | 26.04.2018
	 * Test Result: PASS       | FAIL
	 */	
	public void testSubjectBodyNull_112(){
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
	public void testSetFromCorrectFormat_113(){
		populatedEmail.setFrom(kSENDER);
		assertEquals(kSENDER, populatedEmail.fromAddress());
	}
	
	@Test
	/* 
	 * Testing setFrom()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 114
	 * Date Tested: 02/05/2018 | 27.04.2018
	 * Test Result: PASS       | FAIL
	 */	
	public void testSetFromIncorrectFormat_114(){
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
	public void testSetToCorrectFormat_115(){
		
		populatedEmail.setTo(kRECIPIENT);
		assertEquals(kRECIPIENT, populatedEmail.toAddress());
	}
	
	@Test
	/* 
	 * Testing setTo()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 116
	 * Date Tested: 02/05/2018 | 27.04.2018
	 * Test Result: PASS       | FAIL
	 */	
	public void testSetToIncorrectFormat_116() {
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
	public void testSetSubjectStringPassed_117() {
		populatedEmail.setSubject(kSUBJECT);
		assertEquals(kSUBJECT, populatedEmail.subjectLine());
	}
	
	@Test
	/* 
	 * Testing setSubject()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 118
	 * Date Tested: 27.04.2018 | 27.04.2018
	 * Test Result: PASS       | FAIL
	 */	
	public void testSetSubjectNullPassed_118() {
		populatedEmail.setSubject(null);
		assertEquals(null, populatedEmail.subjectLine());
	}
	
	
	@Test
	/* 
	 * Testing: setMessage()
	 * Author: Aidan Reed
	 * Co-Author: Ram Raja
	 * Test ID: 119
	 * Date Tested: 27.04.2018 | 27.04.2018
	 * Test Result: PASS       | FAIL
	 * Notes: Checks value String body is set in the object to the constant used
	 * 		  To check method call to emailMessage to retrieve
	 */
	
	public void setEmailBody_NormalString_119 () {
		noEmailBody.setMessage(kBODY1);
		/*Now need to use emailMessage method to check it was input*/
		assertEquals(kBODY1, noEmailBody.emailMessage());
	}
	
	@Test
	/* 
	 * Testing: setMessage()
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 120
	 * Date Tested: 27/04/2018 | 27/04/2018
	 * Test Result: PASS       | FAIL
	 * Notes: Checks value String emailBody is an empty string using the constant defined
	 * 		  To check method call to emailMessage to retrieve
	 */
	
	public void setEmailBody_EmptyString_120 () {
		noEmailBody.setMessage(kBODY3);
		/*Now need to use emailMessage method to check it was input*/
		assertEquals(kBODY3, noEmailBody.emailMessage());
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
	public void checkValidity_AllNull_121 () {
		assertFalse(nullEmail.isValid());
	}
	
	@Test
	/* 
	 * Testing: isValid()
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 122
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when all attributes are set
	 */
	public void checkValidity_AllSet_122 () {
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
	
	public void checkValidity_Individual_SetFrom_123 () {
		nullEmail.setFrom(kSENDER);
		assertFalse(nullEmail.isValid());
	}
	
	@Test
	/* Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 124
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when only one attribute is set
	 * 		  at once. This case is setTo
	 */
	public void checkValidity_Individual_SetTo_124 () {
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
	public void checkValidity_Individual_SetSubject_125 () {
		nullEmail.setSubject(kSUBJECT);
		assertFalse(nullEmail.isValid());	
	}
	
	@Test
	/* Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 126
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks the validity of a email object when only one attribute is set
	 * 		  at once. This case is setMessage
	 */
	public void checkValidity_Individual_SetMessage_126 () {
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
	
	public void toStringOverride_TypeCheck_127 () {
		assertThat(populatedEmail.toString(), instanceOf(String.class));
	}
	
	@Test 
	/*	
	 * Testing: toString()
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 128
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Checks toString method returns correct String 
	 * 		  In the form Correct Subject set
	 */
	
	public void toStringOverride_ValueCheck_SetSubject_128 () {
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
	
	public void toStringOverride_ValueCheck_EmptyString_129 () {
		nullEmail.setSubject("");
		assertEquals("[no subject]", nullEmail.toString());
	}
	
	@Test 
	/*	
	 * Testing: toString()
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 130
	 * Date Tested: 27.04.2018 | 27.04.2018
	 * Test Result: PASS       | FAIL
	 * Notes: Checks toString method returns correct String 
	 * 		  When subject is not set at all
	 */
	
	public void toStringOverride_ValueCheck_Null_130 () {
		assertEquals("[no subject]", nullEmail.toString());
	}
	
	
	@Test
	/* 
	 * Testing accessibility of all fields in CompanyEmail
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 131
	 * Date Tested: 26.04.2018
	 * Test Result: PASS
	 * Notes: Fields with access value of 2 is the Int representation
	 * 		  of the private access modifier
	 */
	public void testFieldAccessorType_131() {
		Field[] companyEmailMembers = CompanyEmail.class.getDeclaredFields();
		
	    for (Field field : companyEmailMembers) {
	        int accessValue = field.getModifiers();
	        assertTrue(Modifier.isPrivate(accessValue));
	    }
	}
	
	@Test
	/* 
	 * Testing validity of full email when using setTo
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 132
	 * Date Tested: 27.04.2018	
	 * Test Result: PASS
	 * Notes: Checks for bad email combinations and if they were set
	 */
	public void testValidateFullEmail_ToAddress_132 () {
		nullEmail.setTo(kBADEMAIL1);
		assertNull(nullEmail.toAddress());
		nullEmail.setTo(kBADEMAIL2);
		assertNull(nullEmail.toAddress());
		nullEmail.setTo(kBADEMAIL3);
		assertNull(nullEmail.toAddress());
		nullEmail.setTo(kBADEMAIL4);
		assertNull(nullEmail.toAddress());
	}
	
	
	@Test
	/* 
	 * Testing validity of full email when using setTo
	 * Author: Ram Raja
	 * Co-Author: Aidan Reed
	 * Test ID: 132
	 * Date Tested: 27.04.2018	
	 * Test Result: PASS
	 * Notes: Checks for bad email combinations and if they were set
	 */
	public void testValidateFullEmail_FromAddress () {
		nullEmail.setFrom(kBADEMAIL1);
		assertNull(nullEmail.fromAddress());
		nullEmail.setFrom(kBADEMAIL2);
		assertNull(nullEmail.fromAddress());
		nullEmail.setFrom(kBADEMAIL3);
		assertNull(nullEmail.fromAddress());
		nullEmail.setFrom(kBADEMAIL4);
		assertNull(nullEmail.fromAddress());
	}

}
