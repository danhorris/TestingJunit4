package ar.gov.mecon.user.impl;

import ar.gov.mecon.user.IUsuario;
import ar.gov.mecon.user.exceptions.UsuarioLogueadoException;
import ar.gov.mecon.user.exceptions.UsuarioRechazadoException;

/**
 * @author dhorri
 */
public class Usuario implements IUsuario {

  private boolean logueado;

  private boolean rechazado;

  private String login;

  private int intentosFallidos = 0;

  @Override
  public void login() {
    if (!this.isLogueado()) {
      this.setLogueado(true);
    } else {
      throw new UsuarioLogueadoException();
    }

    if (this.isRechazado()) {
      throw new UsuarioRechazadoException();
    }

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

  public int getIntentosFallidos() {
    return intentosFallidos;
  }

  public void setIntentosFallidos(int intentosFallidos) {
    this.intentosFallidos = intentosFallidos;
  }

  public void setLogin(String login) {
    this.login = login;
  }

}
