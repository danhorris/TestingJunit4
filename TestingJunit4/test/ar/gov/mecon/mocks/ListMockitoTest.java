package ar.gov.mecon.mocks;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ListMockitoTest {

  @Test
  public void configuracionMockito() {

    List<String> miLista = Mockito.mock(List.class);

    Mockito.when(miLista.get(0)).thenReturn("Daniel");

    Assert.assertTrue(miLista.get(0).equals("Daniel"));

  }
}
