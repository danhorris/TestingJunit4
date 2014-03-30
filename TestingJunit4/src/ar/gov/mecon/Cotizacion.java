package ar.gov.mecon;

/**
 * Prueba de update
 * @author dhorri
 */
public class Cotizacion {

  private Importe de;

  private Importe a;

  public Cotizacion(Importe unaMoneda, Importe otraMoneda) {
    de = unaMoneda;
    a = otraMoneda;
  }

  public Importe getDe() {
    return de;
  }

  public void setDe(Importe de) {
    this.de = de;
  }

  public Importe getA() {
    return a;
  }

  public void setA(Importe a) {
    this.a = a;
  }

}
