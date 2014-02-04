package com.orange.ru.domain.product;

import com.orange.ru.domain.Site;
import com.orange.ru.domain.product.enums.LinesType;
import javax.persistence.*;
import java.util.*;
import static com.orange.ru.core.Utils.compareObjects;
/**
 * Услуга Access Lines.
 * User: Зайнуллин Радик
 * Date: 27.06.13
 */
@Entity
@Table(name="access_lines")
public class AccessLines extends Product {
  public String getCode() { return "802.01"; }
  public String getName() { return "Access Lines"; }
  public static String getClassCode() { return "802.01"; }

  public AccessLines(){ this.site = new Site(); }

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "site_id")
  private Site site;
  public Site getSite() { return site; }
  public void setSite(Site site) { this.site = site; }

  @Column(name = "type_id")
  private Integer typeId;
  public LinesType getType() { return LinesType.getKey(this.typeId); }
  public void setType(LinesType right) { this.typeId = right.getValue(); }

  // --- override Object ---
  @Override
  public int hashCode() {
    int res = 1;
    if (typeId != null) res = res + typeId;
    if (site != null) res = res + site.hashCode();
    return res;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;

    AccessLines other = (AccessLines) obj;
    if (super.getId() != null && other.getId() != null) return super.getId().equals(other.getId());
    else return compareObjects(this, other);
  }
  @Override
  public String toString(){
    if (typeId == null || site == null)  return "Access Lines";
    else return "AccessLines: channel - " + getType().getDescription() + ", site = " + site.toString();
  }
}