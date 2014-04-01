package ar.gov.mecon.user;

public interface IAuthRepository {

  boolean verificarPassWord(String usuario, String password);

}
