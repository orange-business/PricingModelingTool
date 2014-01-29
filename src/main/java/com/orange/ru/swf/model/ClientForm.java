package com.orange.ru.swf.model;

import java.io.Serializable;
/**
 * Для создания клиента
 * User: Зайнуллин Радик
 * Date: 14.05.13
 */
public class ClientForm implements Serializable {
  private String official;

  public String getOfficial() {
    return official;
  }
  public void setOfficial(String official) {
    this.official = official;
  }
}
