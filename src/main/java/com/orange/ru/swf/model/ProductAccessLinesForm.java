package com.orange.ru.swf.model;

import com.orange.ru.domain.Site;
import com.orange.ru.domain.product.AccessLines;
import com.orange.ru.domain.product.enums.LinesType;
import java.io.Serializable;
/**
 * Вспомогательная форма для потока
 * User: radik
 * Date: 15.05.13
 */
public class ProductAccessLinesForm implements Serializable{
  private Site selectedSite;
  public Site getSelectedSite() { return selectedSite; }
  public void setSelectedSite(Site selectedSite) { this.selectedSite = selectedSite; }

  private LinesType selectedChannel;
  public LinesType getSelectedChannel() { return selectedChannel; }
  public void setSelectedChannel(LinesType selectedChannel) { this.selectedChannel = selectedChannel; }

  public LinesType[] getAccessLinesChannels(){
    return new LinesType[]{ LinesType.BUILD, LinesType.LEASE };
  }
}