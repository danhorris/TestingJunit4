package ar.gov.mecon.mocks;

public class UsuarioLogueadoException extends RuntimeException {

  private static final long serialVersionUID = -1916671864051492218L;

  @Override
  public String getMessage() {

    return "El usuario ya esta logueado";
  }

}
