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

/**
 * ImporteTest con parametros
 * 
 * @author dhorri
 */
@RunWith(Parameterized.class)
public class ImporteParameters {

  private static final String pesos = "PESOS";

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
  public static Collection<Importe[]> data() {

    return Arrays.asList(new Importe[][] {
        {
            new Importe(new BigDecimal("2"), pesos), new Importe(new BigDecimal("2"), pesos),
            new Importe(new BigDecimal("4"), "PESOS")
        },
        {
            new Importe(new BigDecimal("2"), pesos), new Importe(new BigDecimal("6"), pesos),
            new Importe(new BigDecimal("8"), pesos)
        },
        {
            new Importe(new BigDecimal("2"), pesos), new Importe(new BigDecimal("1"), pesos),
            new Importe(new BigDecimal("3"), pesos)
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
    Assert.assertEquals(new Importe(BigDecimal.ONE, "PESOS"), new Importe(BigDecimal.ONE, "PESOS"));
  }

}
