package ar.gov.mecon.mocks;

import org.junit.Test;

import ar.gov.mecon.mocks.exceptions.UsuarioLogueadoException;
import ar.gov.mecon.mocks.exceptions.UsuarioRechazadoException;
import ar.gov.mecon.user.Usuario;

public class UsuarioTest {

  /**
   * 
   */
  @Test(expected = UsuarioLogueadoException.class)
  public void noPermitirSegundoLogin() {
    // given
    Usuario usuario = new Usuario();
    usuario.setLogueado(true);

    // when and then
    usuario.login();

  }

  /**
   * 
   */
  @Test(expected = UsuarioRechazadoException.class)
  public void noLoguearAunUsuarioRechazado() {
    // given
    Usuario usuario = new Usuario();
    usuario.setRechazado(true);

    // when and then
    usuario.login();

  }

}
