package com.orange.ru.core.exception;

public class ProductCodeException extends Exception {
  public ProductCodeException(String message){ super(message); }
  public ProductCodeException(String absent_code, String curr_code){
    super("Код " + curr_code + " зависит от кода " + absent_code);
  }
}