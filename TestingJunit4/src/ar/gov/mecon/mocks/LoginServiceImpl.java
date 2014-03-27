package ar.gov.mecon.mocks;

public class LoginServiceImpl implements LoginService {

  private IRepository repository;

  private int intentosFallidos = 0;

  public LoginServiceImpl(IRepository repository) {
    this.repository = repository;
  }

  public void login(String usuario, String password) {
    IUsuario usuarioRecuperado = repository.find(usuario);
    usuarioRecuperado.setLogueado(true);

    if (!usuarioRecuperado.passwordMatches(password)) {
      ++intentosFallidos;
    }

    if (intentosFallidos == 3) {
      usuarioRecuperado.setRechazado(true);
    }
  }

}
