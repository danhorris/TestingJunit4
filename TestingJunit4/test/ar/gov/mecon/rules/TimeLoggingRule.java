package ar.gov.mecon.rules;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Ejemplo de implementacion de una {@link Rule}.
 * 
 * @author dhorri
 */
public class TimeLoggingRule implements TestRule {

  public TimeLoggingRule() {
    super();
  }

  @Override
  public Statement apply(Statement statement, Description description) {
    return new TimeLogginStatement();
  }
}
