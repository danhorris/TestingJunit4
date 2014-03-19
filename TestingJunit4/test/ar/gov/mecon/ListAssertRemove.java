package ar.gov.mecon;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ListAssertRemove {

  /**
   * El assert debe testear intentar verificar exactamente lo que indica.
   * 
   * @throws Exception
   */
  @Test
  public void removeObject() throws Exception {
    // given
    List<String> lista = new MiLista();
    lista.add("Daniel");
    lista.add("Candela");
    lista.add("Juan");

    // when
    lista.remove("Daniel");

    Assert.assertTrue(!lista.contains("Daniel") && !lista.isEmpty());
  }

  private class MiLista extends ArrayList<String> {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean remove(Object o) {
        // super.clear();
        // return true;
      return super.remove(o);
    }

  }

}
