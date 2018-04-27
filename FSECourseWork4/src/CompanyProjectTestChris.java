import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CompanyProjectTestChris {
	static CompanyProject newCompanyProject;
	static CompanyEmail newcompemail;
	@BeforeClass
	public static void setUp() {
		newCompanyProject = new CompanyProject();
		newcompemail = new CompanyEmail();
		newcompemail.fromAddress("123@gmail.com");
	}
	
	@Test
	public void addEmailtest() {
		
		newCompanyProject.addEmail(newcompemail);
		
		//fail(" ");
	}
}
