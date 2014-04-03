package ar.gov.mecon.user;

import org.junit.Test;

import ar.gov.mecon.user.exceptions.UsuarioLogueadoException;
import ar.gov.mecon.user.exceptions.UsuarioRechazadoException;
import ar.gov.mecon.user.impl.Usuario;

public class UsuarioTest {

  @Test(expected = UsuarioLogueadoException.class)
  public void noDebePermitirSegundoLogin() {
    // given
    Usuario usuario = new Usuario();
    usuario.setLogueado(true);

    // when and then
    usuario.login();

  }

  @Test(expected = UsuarioRechazadoException.class)
  public void noDebeLoguearAunUsuarioRechazado() {
    // given
    Usuario usuario = new Usuario();
    usuario.setRechazado(true);

    // when and then
    usuario.login();

  }

}
