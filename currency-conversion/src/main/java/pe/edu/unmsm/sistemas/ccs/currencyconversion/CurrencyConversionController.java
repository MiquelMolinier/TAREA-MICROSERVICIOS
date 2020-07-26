/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.unmsm.sistemas.ccs.currencyconversion;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.unmsm.sistemas.ccs.currencyconversion.CurrencyConversionBean;
import pe.edu.unmsm.sistemas.ccs.currencyconversion.ForexServiceProxy;

@RestController
public class CurrencyConversionController {
    @Autowired private ForexServiceProxy proxy;
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ){
      CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
      response.setQuantity(quantity);
      response.setTotalCalculatedAmount(
              quantity.multiply(response.getConversionMultiple())
      );
      return response;
    };
}
