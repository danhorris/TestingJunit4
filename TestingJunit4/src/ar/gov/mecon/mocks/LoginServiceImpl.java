package ar.gov.mecon.mocks;

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

    if (usuarioRecuperado.passwordMatches(password)) {
      if (!usuarioRecuperado.estaLogueado() && !usuarioAnteriorLogin.equals(usuarioString)) {
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
