package ar.gov.mecon.mocks;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * http://schuchert.wikispaces.com/Mockito.LoginServiceExample
 * 
 * @author dhorri
 */
public class LoginServiceImplTest {

  @Test
  public void setearLogueadoEnTrueCuandoPasswordCoincide() throws Exception {
    // given
    IUsuario usuario = Mockito.mock(IUsuario.class);
    Mockito.when(usuario.passwordMatches(Mockito.anyString())).thenReturn(true);
    IRepository repository = Mockito.mock(IRepository.class);
    Mockito.when(repository.find(Mockito.anyString())).thenReturn(usuario);

    LoginServiceImpl service = new LoginServiceImpl(repository);

    service.login("dan", "password");

    // verifico que el metodo setLogueado(true) se haya llamado una vez
    Mockito.verify(usuario, Mockito.times(1)).setLogueado(true);

  }
}
