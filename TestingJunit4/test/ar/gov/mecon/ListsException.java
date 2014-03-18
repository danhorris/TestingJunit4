package ar.gov.mecon;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ListsException {

  /**
   * El test solo va a pasar si se lanza una IndexOutOfBoundsException
   * 
   * @throws Exception
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testIndexException() throws Exception {
    List<String> lista = Arrays.asList("Daniel", "Candela", "Juan");

    Assert.assertEquals("Daniel", lista.get(10));
  }

}
