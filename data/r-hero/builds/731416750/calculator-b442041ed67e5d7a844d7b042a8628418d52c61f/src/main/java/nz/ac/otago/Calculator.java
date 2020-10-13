package nz.ac.otago;

import java.io.Console;

/**
 * My calculator
 *
 */
public class Calculator 
{
    private static final Console console = System.console();

    public static void main( String[] args )
    {
       Calculator c = new Calculator(); 
       System.out.println("First value? ");
       Integer x = Integer.valueOf(console.readLine());
       System.out.println("Second value? ");
       Integer y = Integer.valueOf(console.readLine());
       System.out.println("The sum is: " + c.sum(x,y));
    }

    public Integer sum(Integer x, Integer y){
	    //perform the sum
	    return x * y;
    }

    public Integer diff(Integer x, Integer y){
	    return x - y;
    }
}
