package ar.gov.mecon.mocks.exceptions;

/**
 * @author dhorri
 */
public class UsuarioNoExisteException extends RuntimeException {

  private static final long serialVersionUID = 6899194725446822895L;

  @Override
  public String getMessage() {

    return "Usuario no existe";
  }

}
