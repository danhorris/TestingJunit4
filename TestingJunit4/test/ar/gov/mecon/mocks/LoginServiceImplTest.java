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

  @Test
  public void setearLogueadoEnTrueCuandoPasswordCoincide() {
    pasaPassword(usuario, true);
    Mockito.when(repository.find(Mockito.anyString())).thenReturn(usuario);
    // when
    service.login("dan", "password");
    // verifico que el metodo setLogueado(true) se haya llamado una vez
    Mockito.verify(usuario, Mockito.times(1)).setLogueado(true);

  }

  private void pasaPassword(IUsuario usuario, boolean pasa) {
    Mockito.when(usuario.passwordMatches(Mockito.anyString())).thenReturn(pasa);
  }

  @Test
  public void setearRevocadoCuandoFallaLogin3veces() throws Exception {
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
    IUsuario otroUsuario = Mockito.mock(IUsuario.class);
    Mockito.when(repository.find(Mockito.anyString())).thenReturn(usuario);
    Mockito.when(repository.find("Daniel")).thenReturn(otroUsuario);

    pasaPassword(usuario, false);
    pasaPassword(otroUsuario, true);

    // when
    service.login("dan", "password");
    service.login("dan", "password");
    service.login("Daniel", "password");

    // then
    Mockito.verify(otroUsuario, Mockito.never()).setRechazado(true);

  }

}
