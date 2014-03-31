package ar.gov.mecon.mocks.exceptions;

/**
 * @author dhorri
 */
public class UsuarioRechazadoException extends RuntimeException {

  private static final long serialVersionUID = 5021946567307825227L;

  @Override
  public String getMessage() {

    return "El usuario esta rechazado";
  }

}
