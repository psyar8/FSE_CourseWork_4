package JUnitTests;

import java.util.Scanner;

public class InOutSystem {

	public InOutSystem () {
		
	}
	
	public void printHello () {
		System.out.println("hello");
	}
	
	public void printwhatisay () { 
		Scanner in = new Scanner(System.in);
		String whatISay = in.nextLine();
		System.out.print(whatISay);
		in.close();
	}
}
