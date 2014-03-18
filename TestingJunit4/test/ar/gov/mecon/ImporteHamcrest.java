package ar.gov.mecon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ImporteHamcrest {

  private List<Importe> listaImportes;

  @Before
  public void inicializarListaimportes() {
    Importe dosPesosCon10 = new Importe(new BigDecimal("2.10"), "PESOS");
    Importe cuatroPesosCon75 = new Importe(new BigDecimal("4.33"), "PESOS");

    listaImportes = new ArrayList<Importe>();
    listaImportes.add(dosPesosCon10);
    listaImportes.add(cuatroPesosCon75);

  }

  @Test
  public void testContains() {
    Importe importeNuevo = new Importe(new BigDecimal("2.10"), "PESOS");
    Assert.assertTrue(listaImportes.contains(importeNuevo));
  }

  @Test
  public void testContainsHamcrest() {
    Importe importeNuevo = new Importe(new BigDecimal("2.10"), "PESOS");
    MatcherAssert.assertThat(listaImportes, CoreMatchers.hasItem(importeNuevo));
  }
}
