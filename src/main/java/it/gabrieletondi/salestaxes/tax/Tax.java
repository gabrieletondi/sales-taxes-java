package it.gabrieletondi.salestaxes.tax;

import java.math.BigDecimal;

public interface Tax {
    BigDecimal dutyAmount(BigDecimal netPrice);
}
