package ar.gov.mecon.mocks;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import ar.gov.mecon.Importe;
import ar.gov.mecon.constants.Moneda;

public class ImporteMockTest {

  /**
   * No testear Mockito. Tiene su propia bateria de tests, testear la funcionalidad de nuestra clase bajo
   * test.
   * 
   * @throws Exception
   */
  @Test
  public void tieneCotizacionMock() {

    Importe importeMock = Mockito.mock(Importe.class);
    Mockito.when(importeMock.getMoneda()).thenReturn(Moneda.PESOS);

    assertTrue(importeMock.getMoneda() != null);

  }

}
