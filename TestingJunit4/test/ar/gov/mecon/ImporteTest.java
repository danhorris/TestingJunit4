package ar.gov.mecon;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class ImporteTest {

  private String pesos = "PESOS";

  private String pesosChilenos = "PESOS_CHILENOS";

  private String pesoUruguayo = "PESO_URUGUAYO";

  private String dolar = "DOLAR";

  @Test
  public void sumaImportemMismaMoneda() {
    // given
    Importe dosPesos = new Importe(new BigDecimal("2"), pesos);
    Importe cuatroPesos = new Importe(new BigDecimal("4"), pesos);

    // when and then
    Assert.assertEquals(dosPesos.mas(dosPesos).getMonto(), cuatroPesos.getMonto());
  }

  @Test
  public void sumaImporteMonedaDistinta() {
    // given
    Importe dosPesos = new Importe(new BigDecimal("2"), pesos);
    Importe dosPesosChilenos = new Importe(new BigDecimal("2"), pesosChilenos);
    Importe cuatroPesos = new Importe(new BigDecimal("4"), pesos);

    // when and then
    Assert.assertFalse(dosPesos.mas(dosPesosChilenos).equals(cuatroPesos));
  }

  @Test
  public void equalsConMoneda() {

    Importe cincoPesos = new Importe(new BigDecimal("5"), pesos);
    Importe otroCincoPesos = new Importe(new BigDecimal("5"), pesos);

    Assert.assertEquals(cincoPesos, otroCincoPesos);

    Assert.assertFalse(cincoPesos.equals(new Importe(new BigDecimal("5"), pesosChilenos)));
  }

  @Test
  public void restarImporte() {

    // given
    Importe diesPesos = new Importe(BigDecimal.TEN, pesos);
    Importe cincoPesos = new Importe(new BigDecimal("5"), pesos);

    // when
    Importe resta = diesPesos.menos(cincoPesos);

    // then
    Assert.assertEquals(resta, new Importe(new BigDecimal("5"), pesos));
  }

  @Test
  public void conversionSimple() {
    // given
    Importe dosPesos = new Importe(new BigDecimal("2"), pesos);
    Importe cuatroPesosChilenos = new Importe(new BigDecimal("4"), pesosChilenos);
    Cotizacion cotizacionPesosAPesosChilenos = new Cotizacion(dosPesos, cuatroPesosChilenos);

    Importe diezPesos = new Importe(BigDecimal.TEN, pesos);
    Importe oncePesos = new Importe(new BigDecimal("11"), pesos);

    // when
    Importe importeEnPesosChilenos = diezPesos.convertirA(cotizacionPesosAPesosChilenos);
    Importe importeEnPesosChilenos2 = oncePesos.convertirA(cotizacionPesosAPesosChilenos);

    // then
    Assert.assertEquals(new Importe(new BigDecimal("20.00"), pesosChilenos), importeEnPesosChilenos);
    Assert.assertEquals(new Importe(new BigDecimal("22.00"), pesosChilenos), importeEnPesosChilenos2);

    Assert.assertFalse(pesos.equals(importeEnPesosChilenos.getMoneda()));

  }

  @Test(expected = MonedaException.class)
  public void cotizacionDistintaMonedaException() {
    // given
    Importe unPesoUruguayo = new Importe(BigDecimal.ONE, pesoUruguayo);
    Importe cuatroPesosChilenos = new Importe(new BigDecimal("4"), pesosChilenos);
    Cotizacion cotizacionPesosChilenosAPesosUruguayos = new Cotizacion(cuatroPesosChilenos, unPesoUruguayo);
    Importe diezPesos = new Importe(BigDecimal.TEN, pesos);

    // when
    diezPesos.convertirA(cotizacionPesosChilenosAPesosUruguayos);
    // then
  }

  @Test
  public void conversionCompleja() {
    // given
    Importe dosPesosCon10 = new Importe(new BigDecimal("2.10"), pesos);
    Importe cuatroPesosCon75 = new Importe(new BigDecimal("4.33"), pesosChilenos);
    Cotizacion cotizacionPesosAPesosChilenos = new Cotizacion(dosPesosCon10, cuatroPesosCon75);

    Importe sieteCon85 = new Importe(new BigDecimal("7.85"), pesos);
    Importe unDolar = new Importe(new BigDecimal("1.00"), dolar);
    Cotizacion cotizacionPesosADolar = new Cotizacion(sieteCon85, unDolar);

    Importe diezPesos = new Importe(BigDecimal.TEN, pesos);
    Importe oncePesos = new Importe(new BigDecimal("11"), pesos);

    // when
    Importe importeEnPesosChilenos = diezPesos.convertirA(cotizacionPesosAPesosChilenos);
    Importe importeEnPesosChilenos2 = oncePesos.convertirA(cotizacionPesosAPesosChilenos);
    Importe importeEnDolares = diezPesos.convertirA(cotizacionPesosADolar);

    // then
    Assert.assertEquals(new Importe(new BigDecimal("20.62"), pesosChilenos), importeEnPesosChilenos);
    Assert.assertEquals(new Importe(new BigDecimal("22.68"), pesosChilenos), importeEnPesosChilenos2);
    Assert.assertEquals(new Importe(new BigDecimal("1.27"), dolar), importeEnDolares);

  }

  /**
   * Ejemplo de Code Coverage
   */
  @Test
  public void importeInValidoPorMoneda() {
    // given
    Importe dosPesosCon10 = new Importe(new BigDecimal("11"), "");
    // when and then
    Assert.assertFalse(dosPesosCon10.importeValido());
  }

  @Test
  public void importeInvalidoPorMonto() {
    // given
    Importe dosPesosCon10 = new Importe(new BigDecimal("0"), pesos);
    // when and then
    Assert.assertFalse(dosPesosCon10.importeValido());
  }

}
