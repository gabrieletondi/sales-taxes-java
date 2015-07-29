package it.gabrieletondi.salestaxes;

import it.gabrieletondi.salestaxes.tax.rounding.NearestToFiveCentsRounding;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NearestToFiveCentsRoundingTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"0.15", "0.124"},
                {"1.50", "1.499"},
                {"1.05", "1.05"},
                {"0.00", "0.00"},
                {"0.10", "0.09"}
        });
    }

    private String expected;
    private String toBeRounded;

    public NearestToFiveCentsRoundingTest(String expected, String toBeRounded) {
        this.expected = expected;
        this.toBeRounded = toBeRounded;
    }

    @Test
    public void roundToNearest0_05() throws Exception {
        NearestToFiveCentsRounding rounding = new NearestToFiveCentsRounding();

        assertEquals(new BigDecimal(expected), rounding.round(new BigDecimal(toBeRounded)));
    }
}
