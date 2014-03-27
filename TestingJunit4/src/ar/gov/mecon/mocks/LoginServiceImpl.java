package ar.gov.mecon.mocks;

public class LoginServiceImpl {

  private IRepository repository;

  public LoginServiceImpl(IRepository repository) {
    this.repository = repository;
  }

  public void login(String usuario, String password) {
    IUsuario usuarioRecuperado = repository.find(usuario);
    usuarioRecuperado.setLogueado(true);
  }

}
