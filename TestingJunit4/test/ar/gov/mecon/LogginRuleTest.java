package ar.gov.mecon;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import ar.gov.mecon.rules.TimeLoggingRule;

/**
 * @author dhorri
 */
public class LogginRuleTest {

  @Rule
  public TimeLoggingRule rule = new TimeLoggingRule();

  @Test
  public void decorarMetodo() {
    // given

    // when

    // then

    System.out.println("ejecutando");
    Assert.assertTrue(true);

  }

}
