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
    // inyeccion de dependencias
    service = new LoginServiceImpl(repository);
  }

  private void pasaPassword(IUsuario usuario, boolean pasa) {
    Mockito.when(usuario.passwordMatches(Mockito.anyString())).thenReturn(pasa);
  }

  @Test
  public void logueadoEnTrueCuandoPasswordCoincide() {
    pasaPassword(usuario, true);
    Mockito.when(repository.find(Mockito.anyString())).thenReturn(usuario);
    // when
    service.login("dan", "password");
    // verifico que el metodo setLogueado(true) se haya llamado una vez
    Mockito.verify(usuario, Mockito.times(1)).setLogueado(true);

  }

  @Test
  public void revocadoCuandoFallaLogin3veces() throws Exception {
    pasaPassword(usuario, false);

    Mockito.when(repository.find(Mockito.anyString())).thenReturn(usuario);

    // when
    for (int i = 0; i < 3; i++) {
      service.login("dan", "password");
    }

    // then
    Mockito.verify(usuario, Mockito.times(1)).setRechazado(true);

  }

  @Test
  public void noSetearLogueadoTrueSiLoginFalla() throws Exception {
    // given
    pasaPassword(usuario, false);
    Mockito.when(repository.find(Mockito.anyString())).thenReturn(usuario);

    // when
    for (int i = 0; i < 3; i++) {
      service.login("dan", "password");
    }
    // then
    Mockito.verify(usuario, Mockito.never()).setLogueado(true);
  }

  @Test
  public void noSetearRevocadoAUnUsuarioEnElPrimerIntento() throws Exception {
    // given
    IUsuario segundoUsuario = Mockito.mock(IUsuario.class);
    Mockito.when(repository.find(Mockito.anyString())).thenReturn(usuario);
    Mockito.when(repository.find("Daniel")).thenReturn(segundoUsuario);

    pasaPassword(usuario, false);
    pasaPassword(segundoUsuario, false);

    // when
    service.login("dan", "password");
    service.login("dan", "password");
    service.login("Daniel", "password");

    // then
    Mockito.verify(segundoUsuario, Mockito.never()).setRechazado(true);

  }

}
