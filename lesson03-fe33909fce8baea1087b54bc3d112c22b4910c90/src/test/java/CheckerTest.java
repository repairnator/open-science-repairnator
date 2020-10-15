import com.company.util.Checker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckerTest {
    private Checker checker;

    @Before
    public void setup() {
        checker = new Checker();
    }

    @Test
    public void isEvenTest() {
        Assert.assertEquals("The number should be event", checker.isEven(0), true);
        Assert.assertEquals("The number should be event", checker.isEven(100), true);
        Assert.assertEquals("The number should be event", checker.isEven(99), false);
        Assert.assertEquals("The number should be event", checker.isEven(43), false);
        Assert.assertEquals("The number should be event", checker.isEven(-10), true);
        Assert.assertEquals("The number should be event", checker.isEven(-3), false);
    }

    @Test
    public void isOddTest() {
        Assert.assertEquals("The number should be event", checker.isOdd(0), false);
        Assert.assertEquals("The number should be event", checker.isOdd(100), false);
        Assert.assertEquals("The number should be event", checker.isOdd(99), true);
        Assert.assertEquals("The number should be event", checker.isOdd(43), true);
        Assert.assertEquals("The number should be event", checker.isOdd(-10), false);
        Assert.assertEquals("The number should be event", checker.isOdd(-3), true);

    }
}
