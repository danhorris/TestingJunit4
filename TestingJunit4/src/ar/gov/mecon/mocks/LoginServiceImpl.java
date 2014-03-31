package ar.gov.mecon.mocks;

/**
 * @author dhorri
 */
public class LoginServiceImpl implements LoginService {

  private IRepository repository;

  private int intentosFallidos = 0;

  private String usuarioAnteriorLoginFallido = "";

  public LoginServiceImpl(IRepository repository) {
    this.repository = repository;
  }

  public void login(String usuarioString, String password) {
    IUsuario usuarioRecuperado = repository.find(usuarioString);

    if (usuarioRecuperado.passwordMatches(password)) {
      usuarioRecuperado.setLogueado(true);

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
