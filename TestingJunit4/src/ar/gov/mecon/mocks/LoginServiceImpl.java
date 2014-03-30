package ar.gov.mecon.mocks;

public class LoginServiceImpl implements LoginService {

  private IRepository repository;

  private int intentosFallidos = 0;

  public LoginServiceImpl(IRepository repository) {
    this.repository = repository;
  }

  public void login(String usuarioString, String password) {
    IUsuario usuarioRecuperado = repository.find(usuarioString);

    if (usuarioRecuperado.passwordMatches(password)) {
      usuarioRecuperado.setLogueado(true);

    } else {
      ++intentosFallidos;
    }

    if (intentosFallidos == 3) {
      usuarioRecuperado.setRechazado(true);
    }
  }

}
