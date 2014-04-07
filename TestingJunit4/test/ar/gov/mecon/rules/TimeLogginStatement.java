package ar.gov.mecon.rules;

import org.junit.runners.model.Statement;

/**
 * Ejemplo de Rule para implementar el tiempo consumido en un metodo.
 * 
 * @author dhorri
 */
public class TimeLogginStatement extends Statement {

  @Override
  public void evaluate() throws Throwable {
    long initTime = System.currentTimeMillis();
    double totalTime = (System.currentTimeMillis() - initTime) / 1000d;
    System.out.println("consumio " + totalTime);
  }
}
