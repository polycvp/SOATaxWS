
package taxservice.groupf;

import dk.cphbusiness.soa.tax.TaxBill;
import dk.cphbusiness.soa.tax.TaxService;
import java.math.BigDecimal;


public class DefaultCalculator implements TaxService{

    @Override
    public TaxBill calculateBill(BigDecimal amount) {
        return new TaxBill(amount);
    }

    @Override
    public boolean supports(String argument) {
        return true;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

}
