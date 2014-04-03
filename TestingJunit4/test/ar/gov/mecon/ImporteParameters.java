package ar.gov.mecon;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ar.gov.mecon.constants.Moneda;

/**
 * ImporteTest con parametros
 * 
 * @author dhorri
 */
@RunWith(Parameterized.class)
public class ImporteParameters {

  private Importe valorImporte1;

  private Importe valorImporte2;

  private Importe valorImporteEsperado;

  public ImporteParameters(Importe valorImporte1, Importe valorImporte2, Importe valorImporteEsperado) {
    super();
    this.valorImporte1 = valorImporte1;
    this.valorImporte2 = valorImporte2;
    this.valorImporteEsperado = valorImporteEsperado;
  }

  @Parameters
  public static Collection<Importe[]> getParameters() {

    return Arrays.asList(new Importe[][] {
        {
            new Importe(new BigDecimal("2"), Moneda.PESOS), new Importe(new BigDecimal("2"), Moneda.PESOS),
            new Importe(new BigDecimal("4"), Moneda.PESOS)
        },
        {
            new Importe(new BigDecimal("2"), Moneda.PESOS), new Importe(new BigDecimal("6"), Moneda.PESOS),
            new Importe(new BigDecimal("8"), Moneda.PESOS)
        },
        {
            new Importe(new BigDecimal("2"), Moneda.PESOS), new Importe(new BigDecimal("1"), Moneda.PESOS),
            new Importe(new BigDecimal("3"), Moneda.PESOS)
        }
    });
  }

  @Test
  public void sumaImportemMismaMonedaConParameters() {

    // when and then
    Assert.assertEquals(valorImporte1.mas(valorImporte2).getMonto(), valorImporteEsperado.getMonto());
  }

  @Test
  @Ignore("Ignorado porque no se pasan parametros")
  public void metodoIgnoradoTest() {
    Assert.assertEquals(new Importe(BigDecimal.ONE, Moneda.PESOS), new Importe(BigDecimal.ONE, Moneda.PESOS));
  }

}
