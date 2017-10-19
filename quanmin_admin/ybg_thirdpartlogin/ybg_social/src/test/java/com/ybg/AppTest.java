package com.ybg;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/** @author https://gitee.com/YYDeament/88ybg
 * @date 2017/10/1 **/
public class AppTest extends TestCase {
	
	/** Create the test case
	 *
	 * @param testName
	 *            name of the test case */
	public AppTest(String testName) {
		super(testName);
	}
	
	/** @return the suite of tests being tested */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}
	
	/** Rigourous Test :-) */
	public void testApp() {
		assertTrue(true);
	}
}
