package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;

public interface Tax {
    BigDecimal dutyAmount(BigDecimal netPrice);
}
