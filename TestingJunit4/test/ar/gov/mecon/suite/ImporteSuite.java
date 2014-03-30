package ar.gov.mecon.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ar.gov.mecon.ImporteParameters;
import ar.gov.mecon.ImporteTest;
import ar.gov.mecon.ListAssertRemove;
import ar.gov.mecon.ListsException;
import ar.gov.mecon.mocks.ListMockitoTest;

/**
 * Contiene la lista de test que corresponden al proyecto.
 * 
 * @author dhorri
 */
@RunWith(Suite.class)
@SuiteClasses({
    ImporteTest.class, ImporteParameters.class, ListsException.class, ListAssertRemove.class,
    ListMockitoTest.class
})
public class ImporteSuite {

}
