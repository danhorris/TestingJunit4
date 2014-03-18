package ar.gov.mecon;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author dhorri
 */
public class Importe {

  private BigDecimal monto;

  private String moneda;

  private int decimales = 2;

  public Importe(BigDecimal monto, String moneda) {
    this.monto = monto;
    this.moneda = moneda;
  }

  /**
   * @param importe
   * @return
   */
  public Importe mas(Importe importe) {

    if (importe.getMoneda().equals(moneda)) {
      Importe result = new Importe(monto.add(importe.getMonto()), moneda);
      return result;
    } else
      return this;
  }

  /**
   * @param importe
   * @return
   */
  public Importe menos(Importe importe) {
    return new Importe(this.monto.subtract(importe.getMonto()), moneda);
  }

  /**
   * Se redondea para arriba con dos decimales.
   * 
   * @param cotizacion
   * @return
   */
  public Importe convertirA(Cotizacion cotizacion) {

    if (moneda.equals(cotizacion.getDe().getMoneda())) {

      BigDecimal reglaDeTres = monto.multiply(cotizacion.getA().getMonto()).divide(
          cotizacion.getDe().getMonto(), decimales, RoundingMode.HALF_EVEN);

      return new Importe(reglaDeTres, cotizacion.getA().getMoneda());
    } else {
      throw new MonedaException();
    }
  }

  @Override
  public boolean equals(Object importe) {
    if (importe instanceof Importe) {
      Importe unImporte = (Importe) importe;
      return (monto.equals(unImporte.getMonto()) && unImporte.getMoneda().equals(this.getMoneda()));
    }
    return false;
  }

  @Override
  public String toString() {
    return monto.toPlainString() + " " + moneda;
  }

  public String getMoneda() {
    return moneda;
  }

  public void setMoneda(String moneda) {
    this.moneda = moneda;
  }

  public BigDecimal getMonto() {
    return monto;
  }

  public Boolean importeValido() {
    if (moneda.equals("")) {
      return false;
    }
    return monto.compareTo(BigDecimal.ZERO) > 0;
  }

}
