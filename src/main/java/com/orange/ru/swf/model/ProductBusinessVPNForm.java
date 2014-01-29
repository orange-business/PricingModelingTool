package com.orange.ru.swf.model;

import com.orange.ru.domain.Site;
import java.io.Serializable;
import java.util.List;

public class ProductBusinessVPNForm implements Serializable {
  private String town;
  public String getTown() { return town; }
  public void setTown(String town) {this.town = town; }

  private Integer agreementPeriod;
  public Integer getAgreementPeriod() { return agreementPeriod; }
  public void setAgreementPeriod(Integer agreementPeriod) { this.agreementPeriod = agreementPeriod; }

  private Site selectedSite;
  public Site getSelectedSite() { return selectedSite; }
  public void setSelectedSite(Site selectedSite) { this.selectedSite = selectedSite; }

  public String isTownNative(String town){
    if (town == null) return "no"; else return "yes";
  }
  public String getTownNative(String siteName, List<String> towns){
    for (String town : towns){
      if (siteName.contains(town)) return town;
    }
    return null;
  }
}
