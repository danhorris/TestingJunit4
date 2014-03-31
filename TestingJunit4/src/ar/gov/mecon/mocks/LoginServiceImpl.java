package ar.gov.mecon.mocks;

import ar.gov.mecon.mocks.exceptions.UsuarioLogueadoException;
import ar.gov.mecon.mocks.exceptions.UsuarioNoExisteException;
import ar.gov.mecon.mocks.exceptions.UsuarioRechazadoException;

/**
 * @author dhorri
 */
public class LoginServiceImpl implements LoginService {

  private IRepository repository;

  private int intentosFallidos = 0;

  private String usuarioAnteriorLoginFallido = "";

  private String usuarioAnteriorLogin = "";

  public LoginServiceImpl(IRepository repository) {
    this.repository = repository;
  }

  public void login(String usuarioString, String password) {
    IUsuario usuarioRecuperado = repository.find(usuarioString);

    if (usuarioRecuperado == null) {
      throw new UsuarioNoExisteException();
    }

    if (usuarioRecuperado.isRechazado()) {
      throw new UsuarioRechazadoException();
    }

    if (usuarioRecuperado.passwordCorrecta(password)) {
      if (!usuarioRecuperado.isLogueado() && !usuarioAnteriorLogin.equals(usuarioString)) {
        usuarioRecuperado.setLogueado(true);
        usuarioAnteriorLogin = usuarioString;
      } else {
        throw new UsuarioLogueadoException();
      }

    } else {
      if (usuarioAnteriorLoginFallido.equals(usuarioString)) {
        ++intentosFallidos;
      } else {
        usuarioAnteriorLoginFallido = usuarioString;
        intentosFallidos = 1;
      }
    }

    if (intentosFallidos == 3) {
      usuarioRecuperado.setRechazado(true);
    }
  }
}
