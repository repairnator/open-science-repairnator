import com.company.Calculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void test() {
        calculator = new Calculator();
    }

    @Test
    public void addTest() {
        int random1 = (int) (Math.random() * 100000);
        int random2 = (int) (Math.random() * 100000);
        Assert.assertEquals(random1 + " + " + random2 + " = " + (random1 + random2),
                random1 + random2, calculator.add(random1, random2));
    }

    @Test
    public void minusTest() {
        int random1 = (int) (Math.random() * 100000);
        int random2 = (int) (Math.random() * 100000);
        Assert.assertEquals(random1 + " - " + random2 + " = " + (random1 - random2),
                random1 - random2, calculator.minus(random1, random2));
    }

    @Test
    public void multiplyTest() {
        long random1 = (long) (Math.random() * 10000000);
        long random2 = (long) (Math.random() * 10000000);
        Assert.assertEquals(random1 + " * " + random2 + " = " + (random1 * random2),
                random1 * random2, calculator.multiply((int)random1, (int)random2));
    }

    @Test
    public void divideTest() {
        int random1 = (int) (Math.random() * 100000);
        int random2 = (int) (Math.random() * 100000);
        Assert.assertEquals(random1 + " / " + random2 + " = " + (random1 / random2),
                (double) random1 / random2, calculator.division(random1, random2), 0.000001);
    }


}
