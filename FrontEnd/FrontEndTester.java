// --== CS400 File Header Information ==--
// Name: Anubhav Kumaria
// Email: kumaria@wisc.edu
// Team: GG blue
// Role: Front End Developer
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: The implementation on the proposal was initially submitted by a member of the
// red team. However, after a good deal of back and forth with them, we figured some test methods
// were inadequate and incorrect as pointed out by the TA as well.
// These needed to be tweaked for instance "testRotation" as they were either found to be
// redundant or not well thought out originally.

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class FrontEndTester {

	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void tearDown() {
	    System.setOut(standardOut);
	}
	
	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	private void print(String output) {
	    System.out.println(output);
	}
	
	@Test
	public void givenSystemOutRedirection_whenInvokePrintln_thenOutputCaptorSuccess() {
	    
		FrontEndDijkstras f = new FrontEndDijkstras();
		String[] s = new String[1];
		s[0] = "Welcome to the UW Campus Map!";
		try {
			f.main(null);
		} catch (Exception e) {
			
		}
		
	    Assert.assertEquals("Welcome to the UW Campus Map!", outputStreamCaptor.toString()
	      .trim());
	}
	
	@Test
	public void testExceptions() {
		String c = "true";
		try {
			FrontEndDijkstras f = new FrontEndDijkstras();
			f.main(null);
		} catch (Exception e) {
			c = "false";
		} 
		
		Assert.assertEquals("true", c);
	}

}
