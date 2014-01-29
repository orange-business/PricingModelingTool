package com.orange.ru.mongodb.reference.bvpntariffs;

import com.orange.ru.domain.Port;
import com.orange.ru.domain.product.enums.*;
import org.joda.money.BigMoney;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigInteger;
/**
 * Справочник "Тарифы для клиента".
 * User: Зайнуллин Радик
 * Date: 03.07.13
 */
@Document(collection = "BusinessVpnTariffs")
public class BusinessVpnTariffs implements Serializable {
  @Id
  private BigInteger id;
  public BigInteger getId() { return id; }
  public void setId(BigInteger id) { this.id = id; }

  public BusinessVpnTariffs(){
    base = new Base();
    domestic = new BvpnCoverage();
    okrug = new BvpnCoverage();
    local = new BvpnCoverage();
  }
  @Field("base")
  private Base base;
  public Base getBase() { return base; }
  public void setBase(Base base) { this.base = base; }

  @Field("town")
  @Indexed(unique = true)
  private String town;
  public String getTown() { return town; }
  public void setTown(String town) { this.town = town; }

  @Field("description")
  private String description;
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  @Field("caption")
  private String caption;
  public String getCaption() { return caption; }
  public void setCaption(String caption) { this.caption = caption; }

  @Field("source")
  private String source;
  public String getSource() { return source; }
  public void setSource(String source) { this.source = source; }

  @Field("domestic")
  private BvpnCoverage domestic;
  public BvpnCoverage getDomestic() { return domestic; }
  public void setDomestic(BvpnCoverage domestic) { this.domestic = domestic; }

  @Field("okrug")
  private BvpnCoverage okrug;
  public BvpnCoverage getOkrug() { return okrug; }
  public void setOkrug(BvpnCoverage okrug) { this.okrug = okrug; }

  @Field("local")
  private BvpnCoverage local;
  public BvpnCoverage getLocal() { return local; }
  public void setLocal(BvpnCoverage local) { this.local = local; }

  @Override
  public boolean equals(Object obj){
    if (!(obj instanceof BusinessVpnTariffs)) return false;
    BusinessVpnTariffs in = (BusinessVpnTariffs) obj;
    if (!in.getTown().equals(this.town)) return false;
    if (!in.getCaption().equals(this.caption)) return false;
    if (!in.getDescription().equals(this.description)) return false;
    if (!in.getSource().equals(this.source)) return false;

    if (!in.getDomestic().equals(this.domestic)) return false;
    if (!in.getOkrug().equals(this.okrug)) return false;
    if (!in.getLocal().equals(this.local)) return false;

    return true;
  }
  public Colors getColors(PortCoverage portCoverage) {
    if (portCoverage==PortCoverage.DOMESTIC) return domestic.getUbb().getColors();
    if (portCoverage==PortCoverage.OKRUG) return okrug.getUbb().getColors();
    if (portCoverage==PortCoverage.LOCAL) return local.getUbb().getColors();
    return null;
  }

  public BigMoney getD3Value(PortCoverage portCoverage){
    Colors colors = getColors(portCoverage);
    return colors.getData3().getValue();
  }
  public BigMoney getD2Value(PortCoverage portCoverage){
    Colors colors = getColors(portCoverage);
    return colors.getData2().getValue();
  }
  public BigMoney getD1Value(PortCoverage portCoverage){
    Colors colors = getColors(portCoverage);
    return colors.getData1().getValue();
  }
  public BigMoney getVoiceValue(PortCoverage portCoverage){
    Colors colors = getColors(portCoverage);
    return colors.getData1().getValue();
  }
  public BigMoney getVideoValue(PortCoverage portCoverage){
    Colors colors = getColors(portCoverage);
    return colors.getData1().getValue();
  }

  public BigMoney getMccValue(PortCoverage portCoverage, PortType portType){
    if (portCoverage==PortCoverage.DOMESTIC&portType== PortType.SILVER)   return domestic.getUbb().getMcc().getSilver().getValue();
    if (portCoverage==PortCoverage.DOMESTIC&portType== PortType.GOLD)     return domestic.getUbb().getMcc().getGold().getValue();
    if (portCoverage==PortCoverage.DOMESTIC&portType== PortType.PLATINUM) return domestic.getUbb().getMcc().getPlatinum().getValue();

    if (portCoverage==PortCoverage.OKRUG&portType== PortType.SILVER)   return okrug.getUbb().getMcc().getSilver().getValue();
    if (portCoverage==PortCoverage.OKRUG&portType== PortType.GOLD)     return okrug.getUbb().getMcc().getGold().getValue();
    if (portCoverage== PortCoverage.OKRUG&portType== PortType.PLATINUM) return okrug.getUbb().getMcc().getPlatinum().getValue();

    if (portCoverage==PortCoverage.LOCAL&portType== PortType.SILVER)   return local.getUbb().getMcc().getSilver().getValue();
    if (portCoverage==PortCoverage.LOCAL&portType== PortType.GOLD)     return local.getUbb().getMcc().getGold().getValue();
    if (portCoverage==PortCoverage.LOCAL&portType== PortType.PLATINUM) return local.getUbb().getMcc().getPlatinum().getValue();

    return BigMoney.parse("RUR 10000000000000");
  }

  public BigMoney getFixValue(PortCoverage portCoverage, PortType portType){
    if (portCoverage==PortCoverage.DOMESTIC&portType== PortType.SILVER)   return domestic.getFix().getSilver().getValue();
    if (portCoverage==PortCoverage.DOMESTIC&portType== PortType.GOLD)     return domestic.getFix().getGold().getValue();
    if (portCoverage==PortCoverage.DOMESTIC&portType== PortType.PLATINUM) return domestic.getFix().getPlatinum().getValue();

    if (portCoverage==PortCoverage.OKRUG&portType== PortType.SILVER)   return okrug.getFix().getSilver().getValue();
    if (portCoverage==PortCoverage.OKRUG&portType== PortType.GOLD)     return okrug.getFix().getGold().getValue();
    if (portCoverage==PortCoverage.OKRUG&portType== PortType.PLATINUM) return okrug.getFix().getPlatinum().getValue();

    if (portCoverage==PortCoverage.LOCAL&portType==PortType.SILVER)   return local.getFix().getSilver().getValue();
    if (portCoverage==PortCoverage.LOCAL&portType==PortType.GOLD)     return local.getFix().getGold().getValue();
    if (portCoverage==PortCoverage.LOCAL&portType==PortType.PLATINUM) return local.getFix().getPlatinum().getValue();

    return BigMoney.parse("RUR 10000000000000");
  }
}