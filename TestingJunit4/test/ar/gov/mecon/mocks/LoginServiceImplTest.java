package ar.gov.mecon.mocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import ar.gov.mecon.mocks.exceptions.UsuarioNoExisteException;
import ar.gov.mecon.user.IAuthRepository;
import ar.gov.mecon.user.IRepository;
import ar.gov.mecon.user.IUsuario;
import ar.gov.mecon.user.LoginServiceImpl;
import ar.gov.mecon.user.Usuario;

/**
 * http://schuchert.wikispaces.com/Mockito.LoginServiceExample
 * 
 * @author dhorri
 */
public class LoginServiceImplTest {

  private IUsuario usuario;

  private IRepository userRepository;

  private IAuthRepository authRepository;

  private LoginServiceImpl service;

  /**
   * 
   */
  @Before
  public void init() {
    usuario = Mockito.mock(IUsuario.class);
    Mockito.when(usuario.getLogin()).thenReturn("dan");

    userRepository = Mockito.mock(IRepository.class);
    authRepository = Mockito.mock(IAuthRepository.class);

    // inyeccion de dependencias
    service = new LoginServiceImpl(userRepository, authRepository);
  }

  /**
   * If you are using argument matchers, all arguments have to be provided by matchers. {@link Matchers}
   * 
   * @param loginUsuario
   * @param loginCorrecto
   */
  private void pasaPasswordPorLdap(String loginUsuario, boolean loginCorrecto) {
    Mockito.when(authRepository.verificarPassWord(Mockito.eq(loginUsuario), Mockito.anyString())).thenReturn(
        loginCorrecto);
  }

  /**
   * 
   */
  @Test
  public void llamarALoginCuandoSeVerificoPass() {
    pasaPasswordPorLdap("dan", true);
    Mockito.when(userRepository.find(Mockito.anyString())).thenReturn(usuario);
    // when
    service.login("dan", "");
    // verifico que el metodo setLogueado(true) se haya llamado una vez
    Mockito.verify(usuario, Mockito.times(1)).login();
  }

  /**
   * 
   */
  @Test
  public void revocadoCuandoFallaLogin3veces() {
    pasaPasswordPorLdap("dan", false);

    Usuario usuarioDan = new Usuario();
    usuarioDan.setLogin("dan");

    Mockito.when(userRepository.find(Mockito.anyString())).thenReturn(usuarioDan);

    // when
    for (int i = 0; i < 3; i++) {
      service.login("dan", "password");
    }

    // then
    Assert.assertTrue(usuarioDan.isRechazado());

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
  @Test(expected = UsuarioNoExisteException.class)
  public void usuarioNoExisteException() {
    // given
    Mockito.when(userRepository.find("dan")).thenReturn(null);
    // when
    service.login("dan", "password");
    // then
  }

}
