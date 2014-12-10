package taxservice.groupf;

import dk.cphbusiness.soa.tax.TaxBill;
import dk.cphbusiness.soa.tax.TaxBillLine;
import dk.cphbusiness.soa.tax.TaxService;
import java.math.BigDecimal;

public class DKCalculator implements TaxService {

    @Override
    public TaxBill calculateBill(BigDecimal amount) {
        BigDecimal lowTax = new BigDecimal(400000);
        BigDecimal mediumTax = new BigDecimal(3700000);
        BigDecimal bigTax = new BigDecimal(12000000);
        if (amount.compareTo(lowTax) == -1) {
            TaxBill tb = new TaxBill(amount.multiply(new BigDecimal(8)).divide(new BigDecimal(100)));
            tb.add(new TaxBillLine("Church tax", new BigDecimal(1200)));
            return tb;
        } else if (amount.compareTo(mediumTax) == -1) {
            TaxBill tb = new TaxBill(amount.multiply(new BigDecimal(36)).divide(new BigDecimal(100)));
            tb.add(new TaxBillLine("Pool tax", new BigDecimal(17000)));
            return tb;
        } else if (amount.compareTo(bigTax) == -1) {
            TaxBill tb = new TaxBill(amount.multiply(new BigDecimal(56)).divide(new BigDecimal(100)));
            tb.add(new TaxBillLine("Street worker tax", new BigDecimal(100000)));
            return tb;
        } else {
            TaxBill tb = new TaxBill(amount.multiply(new BigDecimal(60)).divide(new BigDecimal(100)));
            tb.add(new TaxBillLine("Pharmacy tax", new BigDecimal(120000)));
            return tb;
        }
    }

    @Override
    public boolean supports(String argument) {
        return "da".equals(argument);
    }

    @Override
    public int getPriority() {
        return 1;
    }

}
