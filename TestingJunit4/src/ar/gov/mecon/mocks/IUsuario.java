package ar.gov.mecon.mocks;

/**
 * @author dhorri
 */
public interface IUsuario {

  boolean passwordCorrecta(String password);

  void setLogueado(boolean isLogueado);

  void setRechazado(boolean isRechazado);

  boolean isLogueado();

  boolean isRechazado();

}
