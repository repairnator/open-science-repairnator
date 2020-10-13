package nz.ac.otago;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CalculatorTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CalculatorTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CalculatorTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testSum()
    {
	Calculator c = new Calculator();
        assertEquals((int) c.sum(1,1), (int) 2);
    }
}
