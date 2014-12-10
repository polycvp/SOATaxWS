/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxservice.groupf;

import dk.cphbusiness.soa.SimpleRegistry;
import dk.cphbusiness.soa.contract.NoMatchingServiceException;
import dk.cphbusiness.soa.contract.RegistryContract;
import dk.cphbusiness.soa.tax.TaxBill;
import dk.cphbusiness.soa.tax.TaxService;
import java.math.BigDecimal;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Paul
 */
@WebService(serviceName = "TaxWebService")
public class TaxWebService {
    
    private RegistryContract registryContract;
    
    public TaxWebService() {
         registryContract = new SimpleRegistry();
        registryContract.register(TaxService.class, new DKCalculator());
    }
    /**
     * Web service operation
     */
    @WebMethod(operationName = "printTaxReturn")
    public TaxBill printTaxReturn(@WebParam(name = "language") String language, @WebParam(name = "amount") double amount) {
       try {
            TaxService calc = registryContract.lookup(TaxService.class, language);
                return calc.calculateBill(new BigDecimal(amount));
        } catch (NoMatchingServiceException nmse) {
            nmse.printStackTrace(System.err);
            return null;
        }
    }
}
