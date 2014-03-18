package ar.gov.mecon;

/**
 * Exception para cuando las monedas no coinciden
 * 
 * @author dhorri
 */
public class MonedaException extends RuntimeException {

  private static final long serialVersionUID = 3719405556620573751L;

  @Override
  public String getMessage() {

    return "Monedas no coinciden";
  }

}
