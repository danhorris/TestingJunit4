package ar.gov.mecon.user;

/**
 * @author dhorri
 */
public interface IUsuario {

  void setLogueado(boolean isLogueado);

  void setRechazado(boolean isRechazado);

  boolean isLogueado();

  boolean isRechazado();

  String getLogin();

  void login();

  int getIntentosFallidos();

  void setIntentosFallidos(int intentosFallidos);

}
