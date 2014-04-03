package ar.gov.mecon;

import java.math.BigDecimal;

import junit.framework.Assert;
import junit.framework.TestCase;
import ar.gov.mecon.constants.Moneda;

/**
 * JUnit 4 es compatible con codigo legacy de JUnit3
 * <p>
 * Puede seguir ejecutando tests que extiendan de TestCase
 * 
 * @author dhorri
 */

public class ImporteJUnit3 extends TestCase {

  public void testImporteSuma() {
    Importe dosPesos = new Importe(new BigDecimal("2"), Moneda.PESOS);
    Importe cuatroPesos = new Importe(new BigDecimal("4"), Moneda.PESOS);

    // when and then
    Assert.assertEquals(dosPesos.mas(dosPesos).getMonto(), cuatroPesos.getMonto());

  }

}
