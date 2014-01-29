package gson_test5;

public class Event {
  private String name;
  private String source;
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getSource() { return source; }
  public void setSource(String source) { this.source = source; }

  public Event(String name, String source) {
    this.name = name;
    this.source = source;
  }
  @Override
  public String toString() { return String.format("(name=%s, source=%s)", name, source); }
}