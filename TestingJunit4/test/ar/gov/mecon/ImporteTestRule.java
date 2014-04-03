package ar.gov.mecon;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ImporteTestRule {

  private String pesos = "PESOS";

  private String pesosChilenos = "PESOS_CHILENOS";

  private String pesoUruguayo = "PESO_URUGUAYO";

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void debeLanzarExceptionPorCotizacionErroneaParaImporteAConvertir() {
    // given
    Importe unPesoUruguayo = new Importe(BigDecimal.ONE, pesoUruguayo);
    Importe cuatroPesosChilenos = new Importe(new BigDecimal("4"), pesosChilenos);
    Cotizacion cotizacionPesosChilenosAPesosUruguayos = new Cotizacion(cuatroPesosChilenos, unPesoUruguayo);
    Importe diezPesos = new Importe(BigDecimal.TEN, pesos);

    exception.expect(MonedaException.class);
    exception.expectMessage("Monedas no coinciden");

    // when
    diezPesos.convertirA(cotizacionPesosChilenosAPesosUruguayos);
    // then
  }
}
