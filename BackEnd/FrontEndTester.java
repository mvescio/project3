// --== CS400 File Header Information ==--
// Name: Jonathon Byrnes
// Email: jdbyrnes@gmail.com
// Team: Blue
// Role: Front End
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: Used starter code from 
//https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
//and 
//https://www.baeldung.com/java-testing-system-out-println
//in order to figure out how to test user input and what is printed. 

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

//Tests different aspects of the Front End
public class FrontEndTester {

	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	/*
	 * Tests the successful choice of a starting location
	 */
	@Test
	public void testSuccessfulFirstChoice() {
		System.setOut(new PrintStream(outputStreamCaptor));
		String input = "1";
		InputStream sysInBackup = System.in; // backup System.in to restore it later
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		FrontEndDijkstras f = new FrontEndDijkstras();
		String[] s = new String[1];
		s[0] = "Welcome to the UW Campus Map!";
		
		try {
			f.main(null);
		} catch (Exception e) {
			
		}
		
		Assert.assertEquals(true, outputStreamCaptor.toString().trim().contains("Dejope is your starting location."));
		System.setIn(sysInBackup);
	    
	}
	
	/*
	 * Tests the successful choice of an ending location
	 */
	@Test
	public void testSuccessfulSecondChoice() {
		System.setOut(new PrintStream(outputStreamCaptor));
		String input = "1" + System.lineSeparator() + "2";
		InputStream sysInBackup = System.in; // backup System.in to restore it later
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		FrontEndDijkstras f = new FrontEndDijkstras();
		String[] s = new String[1];
		s[0] = "Welcome to the UW Campus Map!";
		
		try {
			f.main(null);
		} catch (Exception e) {
			
		}
		
		Assert.assertEquals(true, outputStreamCaptor.toString().trim().contains("Waters is your ending location."));
		System.setIn(sysInBackup);
	    
	}
	
	/*
	 * Tests the successful choice of yes when asked for adjacent locations
	 */
	@Test
	public void testSuccessfulYesAdjacentChoice() {
		System.setOut(new PrintStream(outputStreamCaptor));
		String input = "1" + System.lineSeparator() + "2" + System.lineSeparator() + "y";
		InputStream sysInBackup = System.in; // backup System.in to restore it later
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		FrontEndDijkstras f = new FrontEndDijkstras();
		String[] s = new String[1];
		s[0] = "Welcome to the UW Campus Map!";
		
		try {
			f.main(null);
		} catch (Exception e) {
			
		}
		
		Assert.assertEquals(true, outputStreamCaptor.toString().trim().contains("Van Vleck Hall,"));
		System.setIn(sysInBackup);
	    
	}
	

}
