package com.orange.ru.mongodb.reference.bvpntariffs;

/**
 * .
 * User: Зайнуллин Радик
 * Date: 23.07.13
 */
public class Colors {
  public Colors(){
    data3 = new Color();
    data2 = new Color();
    data1 = new Color();
    voice = new Color();
    video = new Color();
  }

  private String caption;
  public String getCaption() { return caption; }
  public void setCaption(String caption) { this.caption = caption; }

  private String description;
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  private Color data3, data2, data1, voice, video;
  public Color getData3() { return data3; }
  public void setData3(Color data3) { this.data3 = data3; }
  public Color getData2() { return data2; }
  public void setData2(Color data2) { this.data2 = data2; }
  public Color getData1() { return data1; }
  public void setData1(Color data1) { this.data1 = data1; }
  public Color getVoice() { return voice; }
  public void setVoice(Color voice) { this.voice = voice; }
  public Color getVideo() { return video; }
  public void setVideo(Color video) { this.video = video; }

  @Override
  public boolean equals(Object obj){
    if (!(obj instanceof Colors)) return false;

    Colors in = (Colors) obj;
    if (!in.getCaption().equals(this.caption)) return false;
    if (!in.getDescription().equals(this.description)) return false;
    if (!in.getData3().equals(this.data3)) return false;
    if (!in.getData2().equals(this.data2)) return false;
    if (!in.getData1().equals(this.data1)) return false;
    if (!in.getVoice().equals(this.voice)) return false;
    if (!in.getVideo().equals(this.video)) return false;

    return true;
  }
}