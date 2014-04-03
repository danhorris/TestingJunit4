package ar.gov.mecon;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import ar.gov.mecon.constants.Moneda;

public class ImporteTest {

  @Test
  public void sumaImportemMismaMoneda() {
    // given
    Importe dosPesos = new Importe(new BigDecimal("2"), Moneda.PESOS);
    Importe cuatroPesos = new Importe(new BigDecimal("4"), Moneda.PESOS);

    // when and then
    Assert.assertEquals(dosPesos.mas(dosPesos).getMonto(), cuatroPesos.getMonto());
  }

  @Test
  public void sumaImporteMonedaDistinta() {
    // given
    Importe dosPesos = new Importe(new BigDecimal("2"), Moneda.PESOS);
    Importe dosPesosChilenos = new Importe(new BigDecimal("2"), Moneda.PESOS_CHILENOS);
    Importe cuatroPesos = new Importe(new BigDecimal("4"), Moneda.PESOS);

    // when and then
    Assert.assertFalse(dosPesos.mas(dosPesosChilenos).equals(cuatroPesos));
  }

  @Test
  public void equalsConMoneda() {

    Importe cincoPesos = new Importe(new BigDecimal("5"), Moneda.PESOS);
    Importe otroCincoPesos = new Importe(new BigDecimal("5"), Moneda.PESOS);

    Assert.assertEquals(cincoPesos, otroCincoPesos);

    Assert.assertFalse(cincoPesos.equals(new Importe(new BigDecimal("5"), Moneda.PESOS_CHILENOS)));
  }

  @Test
  public void restarImporte() {

    // given
    Importe diesPesos = new Importe(BigDecimal.TEN, Moneda.PESOS);
    Importe cincoPesos = new Importe(new BigDecimal("5"), Moneda.PESOS);

    // when
    Importe resta = diesPesos.menos(cincoPesos);

    // then
    Assert.assertEquals(resta, new Importe(new BigDecimal("5"), Moneda.PESOS));
  }

  @Test
  public void conversionSimple() {
    // given
    Importe dosPesos = new Importe(new BigDecimal("2"), Moneda.PESOS);
    Importe cuatroPesosChilenos = new Importe(new BigDecimal("4"), Moneda.PESOS_CHILENOS);
    Cotizacion cotizacionPesosAPesosChilenos = new Cotizacion(dosPesos, cuatroPesosChilenos);

    Importe diezPesos = new Importe(BigDecimal.TEN, Moneda.PESOS);
    Importe oncePesos = new Importe(new BigDecimal("11"), Moneda.PESOS);

    // when
    Importe importeEnPesosChilenos = diezPesos.convertirA(cotizacionPesosAPesosChilenos);
    Importe importeEnPesosChilenos2 = oncePesos.convertirA(cotizacionPesosAPesosChilenos);

    // then
    Assert.assertEquals(new Importe(new BigDecimal("20.00"), Moneda.PESOS_CHILENOS), importeEnPesosChilenos);
    Assert.assertEquals(new Importe(new BigDecimal("22.00"), Moneda.PESOS_CHILENOS), importeEnPesosChilenos2);

    Assert.assertFalse(Moneda.PESOS.equals(importeEnPesosChilenos.getMoneda()));

  }

  @Test(expected = MonedaException.class)
  public void cotizacionDistintaMonedaException() {
    // given
    Importe unPesoUruguayo = new Importe(BigDecimal.ONE, Moneda.PESOS_URUGUAYOS);
    Importe cuatroPesosChilenos = new Importe(new BigDecimal("4"), Moneda.PESOS_CHILENOS);
    Cotizacion cotizacionPesosChilenosAPesosUruguayos = new Cotizacion(cuatroPesosChilenos, unPesoUruguayo);
    Importe diezPesos = new Importe(BigDecimal.TEN, Moneda.PESOS);

    // when
    diezPesos.convertirA(cotizacionPesosChilenosAPesosUruguayos);
    // then
  }

  @Test
  public void conversionCompleja() {
    // given
    Importe dosPesosCon10 = new Importe(new BigDecimal("2.10"), Moneda.PESOS);
    Importe cuatroPesosCon75 = new Importe(new BigDecimal("4.33"), Moneda.PESOS_CHILENOS);
    Cotizacion cotizacionPesosAPesosChilenos = new Cotizacion(dosPesosCon10, cuatroPesosCon75);

    Importe sieteCon85 = new Importe(new BigDecimal("7.85"), Moneda.PESOS);
    Importe unDolar = new Importe(new BigDecimal("1.00"), Moneda.DOLARES);
    Cotizacion cotizacionPesosADolar = new Cotizacion(sieteCon85, unDolar);

    Importe diezPesos = new Importe(BigDecimal.TEN, Moneda.PESOS);
    Importe oncePesos = new Importe(new BigDecimal("11"), Moneda.PESOS);

    // when
    Importe importeEnPesosChilenos = diezPesos.convertirA(cotizacionPesosAPesosChilenos);
    Importe importeEnPesosChilenos2 = oncePesos.convertirA(cotizacionPesosAPesosChilenos);
    Importe importeEnDolares = diezPesos.convertirA(cotizacionPesosADolar);

    // then
    Assert.assertEquals(new Importe(new BigDecimal("20.62"), Moneda.PESOS_CHILENOS), importeEnPesosChilenos);
    Assert.assertEquals(new Importe(new BigDecimal("22.68"), Moneda.PESOS_CHILENOS), importeEnPesosChilenos2);
    Assert.assertEquals(new Importe(new BigDecimal("1.27"), Moneda.DOLARES), importeEnDolares);

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
    Importe dosPesosCon10 = new Importe(new BigDecimal("0"), Moneda.PESOS);
    // when and then
    Assert.assertFalse(dosPesosCon10.importeValido());
  }

}
