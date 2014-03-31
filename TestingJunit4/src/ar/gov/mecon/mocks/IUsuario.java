package ar.gov.mecon.mocks;

public interface IUsuario {

  boolean passwordMatches(String password);

  void setLogueado(boolean isLogueado);

  void setRechazado(boolean isRechazado);

  boolean estaLogueado();

  boolean estaRechazado();

}
