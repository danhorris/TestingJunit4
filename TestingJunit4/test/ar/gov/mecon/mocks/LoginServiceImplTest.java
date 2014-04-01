package ar.gov.mecon.mocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import ar.gov.mecon.mocks.exceptions.UsuarioLogueadoException;
import ar.gov.mecon.mocks.exceptions.UsuarioNoExisteException;
import ar.gov.mecon.mocks.exceptions.UsuarioRechazadoException;

/**
 * http://schuchert.wikispaces.com/Mockito.LoginServiceExample
 * 
 * @author dhorri
 */
public class LoginServiceImplTest {

  private IUsuario usuario;

  private IRepository userRepository;

  private IAuthRepository repositoryLDAP;

  private LoginServiceImpl service;

  /**
   * 
   */
  @Before
  public void init() {
    usuario = Mockito.mock(IUsuario.class);
    Mockito.when(usuario.getLogin()).thenReturn("dan");

    userRepository = Mockito.mock(IRepository.class);
    repositoryLDAP = Mockito.mock(IAuthRepository.class);

    // inyeccion de dependencias
    service = new LoginServiceImpl(userRepository, repositoryLDAP);
  }

  /**
   * If you are using argument matchers, all arguments have to be provided by matchers. {@link Matchers}
   * 
   * @param loginUsuario
   * @param loginCorrecto
   */
  private void pasaPasswordPorLdap(String loginUsuario, boolean loginCorrecto) {
    Mockito.when(repositoryLDAP.verificarPassWord(Mockito.eq(loginUsuario), Mockito.anyString())).thenReturn(
        loginCorrecto);
  }

  /**
   * 
   */
  @Test
  public void logueadoEnTrueCuandoPasswordCoincide() {
    pasaPasswordPorLdap("dan", true);
    Mockito.when(userRepository.find(Mockito.anyString())).thenReturn(usuario);
    // when
    service.login("dan", "");
    // verifico que el metodo setLogueado(true) se haya llamado una vez
    Mockito.verify(usuario, Mockito.times(1)).setLogueado(true);

  }

  /**
   * 
   */
  @Test
  public void revocadoCuandoFallaLogin3veces() {
    pasaPasswordPorLdap("dan", false);

    Mockito.when(userRepository.find(Mockito.anyString())).thenReturn(usuario);

    // when
    for (int i = 0; i < 3; i++) {
      service.login("dan", "password");
    }

    // then
    Mockito.verify(usuario, Mockito.times(1)).setRechazado(true);

  }

  /**
   * 
   */
  @Test
  public void noSetearLogueadoTrueSiLoginFalla() {
    // given
    pasaPasswordPorLdap("dan", false);
    Mockito.when(userRepository.find(Mockito.anyString())).thenReturn(usuario);

    // when
    for (int i = 0; i < 3; i++) {
      service.login("dan", "password");
    }
    // then
    Mockito.verify(usuario, Mockito.never()).setLogueado(true);
  }

  /**
   * 
   */
  @Test
  public void noSetearRevocadoAUnUsuarioEnElPrimerIntento() {
    // given
    IUsuario segundoUsuario = Mockito.mock(IUsuario.class);
    Mockito.when(userRepository.find(Mockito.anyString())).thenReturn(usuario);
    Mockito.when(userRepository.find("Daniel")).thenReturn(segundoUsuario);

    pasaPasswordPorLdap("dan", false);
    pasaPasswordPorLdap("Daniel", false);

    // when
    service.login("dan", "password");
    service.login("dan", "password");
    service.login("Daniel", "password");

    // then
    Mockito.verify(segundoUsuario, Mockito.never()).setRechazado(true);

  }

  /**
   * 
   */
  @Test(expected = UsuarioLogueadoException.class)
  public void noPermitirSegundoLogin() {
    // given
    pasaPasswordPorLdap("dan", true);

    Mockito.when(userRepository.find(Mockito.anyString())).thenReturn(usuario);
    Mockito.when(usuario.isLogueado()).thenReturn(true);
    // when
    service.login("dan", "password");

  }

  /**
   * 
   */
  @Test(expected = UsuarioNoExisteException.class)
  public void usuarioNoExisteException() {
    // given
    Mockito.when(userRepository.find("dan")).thenReturn(null);
    // when
    service.login("dan", "password");
    // then

  }

  /**
   * 
   */
  @Test(expected = UsuarioRechazadoException.class)
  public void NoLoguearAunUsuarioRechazado() {
    // given
    Mockito.when(userRepository.find("dan")).thenReturn(usuario);
    Mockito.when(usuario.isRechazado()).thenReturn(true);
    // when
    service.login("dan", "password");
    // then

  }
}
