package snippet;

public class Snippet {
	@Test
		/*
		 * Author: Justin Ng
		 * Co-Author: Christian Stubbs
		 * Test ID: 203.2
		 * Date Tested: 27.04.2018
		 * Test Result: PASS
		 * Notes: Checks that PID increments correctly after two more projects are made
		 */
		public void testGetPID_SecondStage() {
			setUp();
			setUp();
			assertEquals(3, newCompanyProject.getPID());
		}
}

