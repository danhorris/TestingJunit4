package ar.gov.mecon;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ImporteMockTest {

  /**
   * No testear Mockito. Tiene su propia bateria de tests, testear la funcionalidad de nuestra clase bajo
   * test.
   * 
   * @throws Exception
   */
  @Test
  public void tieneCotizacionMock() throws Exception {

    Importe importeMock = Mockito.mock(Importe.class);
    Mockito.when(importeMock.getMoneda()).thenReturn("PESOS");

    Assert.assertTrue(importeMock.getMoneda() != null);

  }

}
