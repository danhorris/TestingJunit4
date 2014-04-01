package ar.gov.mecon.user;

import ar.gov.mecon.user.exceptions.UsuarioNoExisteException;

/**
 * @author dhorri
 */
public class LoginServiceImpl implements LoginService {

  private static final int maxIntentosFallidos = 3;

  private IRepository userRepository;

  private IAuthRepository authRepository;

  private String usuarioAnteriorLoginFallido = "";

  public LoginServiceImpl(IRepository repository, IAuthRepository repositoryLDAP) {
    this.userRepository = repository;
    this.authRepository = repositoryLDAP;
  }

  public void login(String login, String password) {
    IUsuario usuarioRecuperado = userRepository.find(login);

    if (usuarioRecuperado == null) {
      throw new UsuarioNoExisteException();
    }

    if (authRepository.verificarPassWord(login, password)) {
      usuarioRecuperado.login();
    } else {
      if (usuarioAnteriorLoginFallido.equals(login)) {
        usuarioRecuperado.setIntentosFallidos(usuarioRecuperado.getIntentosFallidos() + 1);
      } else {
        usuarioAnteriorLoginFallido = login;
        usuarioRecuperado.setIntentosFallidos(1);
      }
    }

    if (usuarioRecuperado.getIntentosFallidos() == maxIntentosFallidos) {
      usuarioRecuperado.setRechazado(true);
    }
  }
}
