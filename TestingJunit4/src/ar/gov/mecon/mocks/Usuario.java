package ar.gov.mecon.mocks;

public class Usuario implements IUsuario {

  private boolean logueado;

  private boolean rechazado;

  private String login;

  @Override
  public boolean passwordCorrecta(String password) {

    return false;
  }

  public boolean isLogueado() {
    return logueado;
  }

  public boolean isRechazado() {
    return rechazado;
  }

  public void setLogueado(boolean logueado) {
    this.logueado = logueado;
  }

  public void setRechazado(boolean rechazado) {
    this.rechazado = rechazado;
  }

  public String getLogin() {
    return login;
  }

}
