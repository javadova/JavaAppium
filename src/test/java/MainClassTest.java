import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetLocalNumber() {
        Assert.assertTrue("Test Failed: Value !=14", getLocalNumber() == 14);
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("Test Failed: Value < 45",getClassNumber() >= 45);
    }

    @Test
    public void testGetClassString() {
        Assert.assertTrue("Test Failed: does not contain Hello or hello", (getClassString().contains("hello") || getClassString().contains("Hello") ));
    }
    }
