package com.orange.ru.domain.product;

import com.orange.ru.domain.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.orange.ru.core.Utils.compareObjects;
@Entity
@Table(name="business_vpn")
public class BusinessVpn extends Product implements Serializable {
  public String getName() { return "Business VPN"; }
  public String getCode() { return "115.00"; }
  public static String getClassCode() { return "115.00"; }

  /* Ближайший город, где находится маршрутизатор сети PE, значение берется из справочника */
  @Column(name="town")
  private String town;
  public String getTown() { return town; }
  public void setTown(String town) { this.town = town; }

  /* Клиентский сайт, куда будем подключать наш порт */
  @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
  @JoinColumn(name="site_id", nullable = true)
  private Site site;
  public Site getSite() { return site; }
  public void setSite(Site site) { this.site = site; }

  /* порт */
  @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
  @JoinColumn(name="port_id", nullable = false)
  private Port port;
  public Port getPort() { return port; }
  public void setPort(Port port) { this.port = port; }

  // --- override Object ---
  @Override
  public int hashCode() {
    if (port != null && site != null) return port.hashCode() + site.hashCode();
    else return 1;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;

    BusinessVpn other = (BusinessVpn) obj;
    if (super.getId() != null && other.getId() != null) return super.getId().equals(other.getId());
    else return compareObjects(this, other);
  }
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder("BusinessVpn: ");
    if (site != null) sb.append("site: " + site.toString());
    if (port != null) sb.append("port: " + port.toString());
    return sb.toString();
  }
}