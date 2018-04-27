import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CompanyEmailTestAthullya {
	public final String SENDER = "joe.bloggs@gmail.com";
	public final String RECIPIENT = "max.power@live.com";
	
	public final String SUBJECT = "RE: Lorem ipsum";
	public final String BODY = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
	
	CompanyEmail nullSubject;
	CompanyEmail populatedSubject;

	@Before
	public void setup() {
		populatedSubject = new CompanyEmail(SENDER, RECIPIENT, SUBJECT, BODY);
		nullSubject = new CompanyEmail(null, null, null, null);

	}
	
	@Test
	/* 
	 * Testing subjectLine()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 109
	 * Date Tested: 26.04.2018
	 * Test Result: 
	 */
	public void testSubjectLineNull() {
		assertEquals(null, nullSubject.subjectLine());
	}
	

	 /* 
	 * Testing subjectLine()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 110
	 * Date Tested: 26.04.2018
	 * Test Result: 
	 */
	
	@Test
	public void testSubjectLineNotNull() {
		assertNotNull(populatedSubject.subjectLine());
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
		assertNotNull(populatedSubject.emailMessage());
	}
	

	@Test
	/* 
	 * Testing emaiLMessage()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 112
	 * Date Tested: 27.04.2018
	 * Test Result: 
	 */	
	public void testSubjectBodyNull(){
		assertNull(nullSubject.emailMessage());
	}


	@Test
	/* 
	 * Testing setFrom()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 113
	 * Date Tested: 27.04.2018
	 * Test Result: 
	 */	
	public void testSetFrom1(){
		populatedSubject.setFrom(SENDER);
		assertEquals(SENDER, populatedSubject.fromAddress());
	}
	
	@Test
	/* 
	 * Testing setFrom()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 114
	 * Date Tested: 27.04.2018
	 * Test Result: 
	 */	
	public void testSetFrom2(){
		try {
			populatedSubject.setFrom("joe.bloggs");
		}catch (Exception e) {
			populatedSubject=null;
			assertEquals(e.getClass(), Exception.class);
		}finally {
			System.out.println("fail");
		}
	}
	
	
	@Test
	/* 
	 * Testing setTo()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 115
	 * Date Tested: 27.04.2018
	 * Test Result: 
	 */	
	public void testSetTo1(){
		
		populatedSubject.setTo(RECIPIENT);
		assertEquals(RECIPIENT, populatedSubject.toAddress());
	}
	
	@Test
	/* 
	 * Testing setTo()
	 * Author: Athullya Roy
	 * Co-Author: Ram Raja
	 * Test ID: 116
	 * Date Tested: 27.04.2018
	 * Test Result: 
	 */	
	public void testSetTo2() {
		try {
			populatedSubject.setTo("max.power");

		}catch (Exception e) {
			populatedSubject=null;
			assertEquals(e.getClass(), Exception.class);
		}
		
	}

	
	
}