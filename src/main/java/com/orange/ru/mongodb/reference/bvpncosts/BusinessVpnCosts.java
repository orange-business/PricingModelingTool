package com.orange.ru.mongodb.reference.bvpncosts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

/**
 * Справочник "Кост IP VPN на Кбит".
 * User: Зайнуллин Радик
 * Date: 03.07.13
 */
@Document(collection = "BusinessVpnCosts")
public class BusinessVpnCosts implements Serializable {
  @Id
  private BigInteger id;
  public BigInteger getId() { return id; }
  public void setId(BigInteger id) { this.id = id; }

  @Field("town")
  @Indexed(unique = true)
  private String town;
  public String getTown() { return town; }
  public void setTown(String town) { this.town = town; }
  // ключи - domestic, okrug, local
  @Field("costsIpVpn")
  private Map<String, CostIpVpn> costsIpVpn = new HashMap<String, CostIpVpn>();
  public Map<String, CostIpVpn> getCostsIpVpn() { return costsIpVpn; }
  public void setCostsIpVpn(Map<String, CostIpVpn> costsIpVpn) { this.costsIpVpn = costsIpVpn; }

  // ключи - domestic, okrug, local
  @Field("costsChannel")
  private Map<String, CostChannel> costsChannel = new HashMap<String, CostChannel>();
  public Map<String, CostChannel> getCostsChannel() { return costsChannel; }
  public void setCostsChannel(Map<String, CostChannel> costsChannel) { this.costsChannel = costsChannel; }

  @Field("info")
  private String info;
  public String getInfo() { return info; }
  public void setInfo(String info) { this.info = info; }
}
