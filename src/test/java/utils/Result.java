package utils;

public class Result {
  public String name;
  public String price;
  public String link;

  public Result(String name, String price, String link) {
    this.name = name;
    this.price = price;
    this.link = link;
  }

  @Override
  public String toString() {
    return name + ", " + price + ", " + link + "\n";
  }
}
