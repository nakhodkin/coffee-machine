package machine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int beans;

    private int cups;
    private int money;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);

        String action;

        do {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = scanner.next();

            System.out.println();

            switch (action) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    action = scanner.next();

                    if ("back".equalsIgnoreCase(action)) {
                        continue;
                    }

                    try {
                        coffeeMachine.buy(Coffee.from(Integer.parseInt(action)));
                        System.out.println("I have enough resources, making you a coffee!");
                    } catch (CoffeeMachineBuyException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "fill":
                    System.out.println("Write how many ml of water you want to add:");
                    int water = scanner.nextInt();

                    System.out.println("Write how many ml of milk you want to add:");
                    int milk = scanner.nextInt();

                    System.out.println("Write how many grams of coffee beans you want to add:");
                    int beans = scanner.nextInt();

                    System.out.println("Write how many disposable cups of coffee you want to add:");
                    int cups = scanner.nextInt();

                    coffeeMachine.fill(water, milk, beans, cups);
                    break;
                case "take":
                    System.out.println("I gave you $" + coffeeMachine.take());
                    break;
                case "remaining":
                    coffeeMachine.printState();
                    System.out.println();
                    break;
                case "exit":
                    break;
                default:
                    throw new InputMismatchException("Invalid action: '" + action + "'");
            }
        } while (!"exit".equalsIgnoreCase(action));

        scanner.close();
    }

    CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        fill(water, milk, beans, cups);
        this.money = money;
    }

    public void buy(Coffee coffee) throws CoffeeMachineBuyException {
        if (water < coffee.getWater()) {
            throw new CoffeeMachineBuyException("Sorry, not enough water!");
        } else if (milk < coffee.getMilk()) {
            throw new CoffeeMachineBuyException("Sorry, not enough milk!");
        } else if (beans < coffee.getBeans()) {
            throw new CoffeeMachineBuyException("Sorry, not enough coffee beans!");
        } else if (cups < 1) {
            throw new CoffeeMachineBuyException("Sorry, not enough disposable coffee cups!");
        }

        water -= coffee.getWater();
        milk -= coffee.getMilk();
        beans -= coffee.getBeans();
        cups -= 1;

        money += coffee.getPrice();
    }

    public void fill(int water, int milk, int beans, int cups) {
        this.water += water;
        this.milk += milk;
        this.beans += beans;
        this.cups += cups;
    }

    public int take() {
        final int moneyToTake = money;
        money = 0;

        return moneyToTake;
    }

    public void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
    }
}
