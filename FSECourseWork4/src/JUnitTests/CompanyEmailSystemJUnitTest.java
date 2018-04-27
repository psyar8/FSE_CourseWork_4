package JUnitTests;

import static org.junit.Assert.*;
import EmailSystem.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CompanyEmailSystemJUnitTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void cleanUpStreams() { 
		System.setOut(null);
	}
	
	@Test 
	/* 
	 * Testing: Default Menu Output
	 * Author: Aidan Reed
	 * Co-Author: Athullya Roy
	 * Test ID: 
	 * Date Tested: 
	 * Test Result:
	 * Notes: On load the user should be presented with a menu list
	 */
	public void testMenuOptions_OnStartUp() {
		CompanyEmailSystem.main(null);
		String expectedOutput = "What do you want to do?\n P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it";
		assertEquals(expectedOutput + "\n", outContent.toString());
	}

}
