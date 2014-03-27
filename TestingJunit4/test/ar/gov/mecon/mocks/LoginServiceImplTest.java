package ar.gov.mecon.mocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * http://schuchert.wikispaces.com/Mockito.LoginServiceExample
 * 
 * @author dhorri
 */
public class LoginServiceImplTest {

  private IUsuario usuario;

  private IRepository repository;

  private LoginServiceImpl service;

  @Before
  public void init() {
    usuario = Mockito.mock(IUsuario.class);
    repository = Mockito.mock(IRepository.class);
    service = new LoginServiceImpl(repository);
  }

  @Test
  public void setearLogueadoEnTrueCuandoPasswordCoincide() {

    Mockito.when(usuario.passwordMatches(Mockito.anyString())).thenReturn(true);
    Mockito.when(repository.find(Mockito.anyString())).thenReturn(usuario);

    // inyeccion de dependencias

    // when
    service.login("dan", "password");

    // verifico que el metodo setLogueado(true) se haya llamado una vez
    Mockito.verify(usuario, Mockito.times(1)).setLogueado(true);

  }

  @Test
  public void setRevocadoCuandoFallaLogin3veces() throws Exception {

    Mockito.when(usuario.passwordMatches(Mockito.anyString())).thenReturn(false);
    Mockito.when(repository.find(Mockito.anyString())).thenReturn(usuario);

    LoginServiceImpl service = new LoginServiceImpl(repository);

    // when
    for (int i = 0; i < 3; i++) {
      service.login("dan", "password");
    }

    // then
    Mockito.verify(usuario, Mockito.times(1)).setRechazado(true);

  }
}
