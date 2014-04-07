package ar.gov.mecon;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.gov.mecon.constants.Moneda;

/**
 * Clase que usa {@link Rule} para verificar Exception. Se puede verificar mensajes.
 * <p>
 * Con {@link Test} expected podes chequar el tipo de la exception.
 * 
 * @author dhorri
 */
public class ImporteTestRuleException {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void debeLanzarExceptionPorCotizacionErroneaParaImporteAConvertir() {
    // given
    Importe unPesoUruguayo = new Importe(BigDecimal.ONE, Moneda.PESOS);
    Importe cuatroPesosChilenos = new Importe(new BigDecimal("4"), Moneda.PESOS_CHILENOS);
    Cotizacion cotizacionPesosChilenosAPesosUruguayos = new Cotizacion(cuatroPesosChilenos, unPesoUruguayo);
    Importe diezPesos = new Importe(BigDecimal.TEN, Moneda.PESOS);

    exception.expect(MonedaException.class);
    exception.expectMessage("Monedas no coinciden");

    // when
    diezPesos.convertirA(cotizacionPesosChilenosAPesosUruguayos);
    // then
  }
}
