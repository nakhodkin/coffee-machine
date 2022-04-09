package machine;

public enum Coffee {
  ESPRESSO(250, 0, 16, 4),
  LATTE(350, 75, 20, 7),
  CAPPUCCINO(200, 100, 12, 6);

  private final int water;
  private final int milk;
  private final int beans;
  private final int price;

  Coffee(int water, int milk, int beans, int price) {
    this.water = water;
    this.milk = milk;
    this.beans = beans;
    this.price = price;
  }

  static Coffee from(int coffeeId) {
    switch (coffeeId) {
      case 1:
        return Coffee.ESPRESSO;
      case 2:
        return Coffee.LATTE;
      case 3:
        return Coffee.CAPPUCCINO;
      default:
        throw new IllegalArgumentException("Invalid coffee identifier");
    }
  }

  public int getWater() {
    return water;
  }

  public int getMilk() {
    return milk;
  }

  public int getBeans() {
    return beans;
  }

  public int getPrice() {
    return price;
  }
}
