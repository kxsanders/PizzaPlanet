package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);

    public void homeMenu() {
        while (true) {
            System.out.println("\nHome Menu");
            System.out.println("1) New Order");
            System.out.println("0) EXIT");

            System.out.print("Select: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> newOrder();
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid selection.");
            }
        }
    }

    public void newOrder() {
        Order order = new Order(generateOrderId(), new ArrayList<>());
        System.out.println("New Order Created (ID: " + order.getOrderId() + ")");
        orderMenu(order);
    }

    private int generateOrderId() {
        return (int)(Math.random() * 900000) + 100000; //6 digit random
    }

    private void orderMenu(Order order) {

        while (true) {
            System.out.println("\nOrder Menu");
            System.out.println("1) Add Pizza");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Garlic Knots");
            System.out.println("4) Checkout");
            System.out.println("5) Review Order");
            System.out.println("6) Remove Item");
            System.out.println("7) Add Sides");
            System.out.println("0) Cancel Order");

            System.out.print("Select: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> addPizza(order);
                case "2" -> addDrink(order);
                case "3" -> addGarlicKnots(order);
                case "4" -> checkout(order);
                case "5" -> reviewOrder(order);
                case "6" -> removeItem(order);
                case "7" -> addSides(order);
                case "0" -> {
                    System.out.println("Order canceled.");
                    return;
                }
                default -> System.out.println("Invalid selection.");
            }
        }
    }

    private void addPizza(Order order) {
        System.out.println("\nAdd Pizza: ");
        System.out.println("1) Build Your Own");
        System.out.println("2) Signature Pizza");
        System.out.println("0) Cancel");

        System.out.print("Select: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1" -> buildYourOwnPizza(order);
            case "2" -> addSignaturePizza(order);
            case "0" -> System.out.println("Cancelled.");
            default -> System.out.println("Invalid selection.");
        }
    }

    private void addSignaturePizza (Order order) {
        System.out.println("\nSelect Signature Pizza: ");
        System.out.println("1) Margherita");
        System.out.println("2) Veggie");
        System.out.println("3) Supreme");
        System.out.println("4) Meat Lovers");
        System.out.println("5) Hawaiian");
        System.out.println("0) CANCEL");

        System.out.print("Select: ");
        String input = scanner.nextLine();

        SignaturePizza pizza = null;

        switch (input) {
            case "1" -> pizza = SignaturePizzaHelper.margherita();
            case "2" -> pizza = SignaturePizzaHelper.veggie();
            case "3" -> pizza = SignaturePizzaHelper.supreme();
            case "4" -> pizza = SignaturePizzaHelper.meatLovers();
            case "5" -> pizza = SignaturePizzaHelper.hawaiian();
            case "0" -> {
                System.out.println("Cancelled.");
                return;
            }
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }
        order.addProduct(pizza);
        System.out.println("Added: " + pizza.getName());
    }

    private void buildYourOwnPizza (Order order) {
        //SELECT SIZE
        Size size = chooseSize();

        //DEFINE BASE PRICE
        double basePrice = PricingUtility.getBasePrice(size);

        //SELECT CRUST
        CrustType crust = chooseCrust();

        PizzaBuilder builder = new PizzaBuilder();
        builder.startNewPizza("Custom Pizza", size, basePrice, crust, false);

        //TOPPINGS LOOP
        addToppingsLoop(builder);

        //STUFFED CRUST
        System.out.println("Add Stuffed Crust? (Y/N): ");
        String stuffed = scanner.nextLine();

        if (stuffed.equalsIgnoreCase("Y")) {
            builder.toggleStuffedCrust();
        }

        Pizza pizza = builder.getPizza();
        order.addProduct(pizza);

        System.out.println("Pizza added!");
    }

    private Size chooseSize() {
        while (true) {
            System.out.println("Select Size: ");
            System.out.println("1) " + Size.SMALL.pizzaLabel());
            System.out.println("2) " + Size.MEDIUM.pizzaLabel());
            System.out.println("3) " + Size.LARGE.pizzaLabel());

            System.out.print("Select: ");
            String input = scanner.nextLine();

            Size result = switch (input) {
                case "1" -> Size.SMALL;
                case "2" -> Size.MEDIUM;
                case "3" -> Size.LARGE;
                default -> null;
            };

            if (result != null) {
                return result;
            }
            System.out.println("Invalid selection. Try again.");
        }
    }

    private CrustType chooseCrust() {
        while (true) {
            System.out.println("\nSelect Crust: ");
            System.out.println("1) Thin");
            System.out.println("2) Regular");
            System.out.println("3) Thick");
            System.out.println("4) Cauliflower");

            System.out.print("Select: ");
            String input = scanner.nextLine();

            CrustType result = switch (input) {
                case "1" -> CrustType.THIN;
                case "2" -> CrustType.REGULAR;
                case "3" -> CrustType.THICK;
                case "4" -> CrustType.CAULIFLOWER;
                default -> null;
            };

            if (result != null) {
                return result;
            }

            System.out.println("Invalid selection. Try again.");
        }
    }

    private void addToppingsLoop(PizzaBuilder builder) {

        while (true) {
            System.out.println("\nToppings Menu: ");
            System.out.println("1) Add Meat");
            System.out.println("2) Add Cheese");
            System.out.println("3) Add Veggie/Other");
            System.out.println("4) Add Sauce");
            System.out.println("5) Remove Topping");
            System.out.println("0) DONE");

            System.out.println("Select: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> selectMeat(builder);
                case "2" -> selectCheese(builder);
                case "3" -> selectRegular(builder);
                case "4" -> selectSauce(builder);
                case "5" -> removeTopping(builder);
                case "0" -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    //removes ingredient by ingredient
    private void removeTopping(PizzaBuilder builder) {
        var pizza = builder.getPizza();
        var list = pizza.getToppings();

        if (list.isEmpty()) {
            System.out.println("No toppings to remove.");
            return;
        }

        System.out.println("\nCurrent Toppings:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, list.get(i)); // Topping.toString() shows (extra) and price
        }

        System.out.print("Remove which item #? (0 to cancel): ");
        String input = scanner.nextLine();

        int idx;
        try {
            idx = Integer.parseInt(input) - 1;
        } catch (Exception e) {
            System.out.println("Invalid.");
            return;
        }
        if (idx == -1) return; // user typed 0
        if (idx < 0 || idx >= list.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        var removed = list.remove(idx);  // remove exactly one
        System.out.println("🗑️ Removed: " + removed.getName() + (removed.isExtra() ? " (extra)" : ""));
    }

    //Once I had selectMeat logic down, I utilized chatGPT to copy-paste the same format
    private void selectMeat(PizzaBuilder builder){
        System.out.println("1) Pepperoni");
        System.out.println("2) Sausage");
        System.out.println("3) Ham");
        System.out.println("4) Bacon");
        System.out.println("5) Chicken");
        System.out.println("6) Meatball");
        System.out.println("0) CANCEL");

        System.out.print("Select: ");
        String input = scanner.nextLine();
        if (input.equals("0")) return;

        String[] meats = {"Pepperoni", "Sausage", "Ham", "Bacon", "Chicken", "Meatball"};

        int idx;

        try {
            idx = Integer.parseInt(input) - 1;
        }

        catch (Exception e) {
            System.out.println("Invalid.");
            return;
        }

        if (idx < 0 || idx >= meats.length) {
            System.out.println("Invalid.");
            return;
        }

        Size size = builder.getPizza().getSize();

        System.out.println("Extra? (Y/N): ");
        boolean extra = scanner.nextLine().equalsIgnoreCase("Y");

        builder.addTopping(new Topping(meats[idx], ToppingCategory.MEAT, extra, size));
        System.out.println("Added " + meats[idx] + (extra ? " (extra)" : ""));
    }

    private void selectCheese(PizzaBuilder builder) {
        System.out.println("\nChoose Cheese:");
        System.out.println("1) Mozzarella");
        System.out.println("2) Mozzarella (extra)");
        System.out.println("3) Parmesan");
        System.out.println("4) Parmesan (extra)");
        System.out.println("5) Ricotta");
        System.out.println("6) Ricotta (extra)");
        System.out.println("7) Goat Cheese");
        System.out.println("8) Goat Cheese (extra)");
        System.out.println("9) Buffalo");
        System.out.println("10) Buffalo (extra)");
        System.out.println("0) Cancel");

        System.out.print("Select: ");
        String input = scanner.nextLine();
        if (input.equals("0"))
            return;

        String[] cheeses = {"Mozzarella", "Parmesan", "Ricotta", "Goat Cheese", "Buffalo"};
        boolean extra = false;
        int baseIndex;

        int option;
        try {
            option = Integer.parseInt(input);
        }
        catch (Exception e) {
            System.out.println("Invalid.");
            return;
        }

        if (option < 1 || option > cheeses.length * 2) {
            System.out.println("Invalid.");
            return;
        }

        //EVEN = extra
        if (option % 2 == 0) {
            extra = true;
            baseIndex = (option / 2) - 1;
        }
        else {
            baseIndex = (option - 1) /2;
        }

        Size size = builder.getPizza().getSize();
        String cheeseName = cheeses[baseIndex];

        //If it's extra but no cheese exists yet, auto add base cheese
        if (extra && !builder.hasCheese()) {
            System.out.println("Base cheese not found. Automatically adding base " + cheeseName + " first.");
            builder.addTopping(new Topping(cheeseName, ToppingCategory.CHEESE, false, size));
        }
        //now add user selected topping (base or extra)
        builder.addTopping(new Topping(cheeseName, ToppingCategory.CHEESE, extra, size));

        System.out.println("Added " + cheeseName + (extra ? " (extra)" : ""));
    }

    private void selectRegular(PizzaBuilder builder) {
        System.out.println("\nChoose Veggie/Other:");
        System.out.println("1) Onions");
        System.out.println("2) Mushrooms");
        System.out.println("3) Bell Peppers");
        System.out.println("4) Olives");
        System.out.println("5) Tomatoes");
        System.out.println("6) Spinach");
        System.out.println("7) Basil");
        System.out.println("8) Pineapple");
        System.out.println("9) Anchovies");
        System.out.println("0) Cancel");

        System.out.print("Select: ");
        String input = scanner.nextLine();
        if (input.equals("0")) return;

        String[] regulars = {
                "Onions","Mushrooms","Bell Peppers","Olives",
                "Tomatoes","Spinach","Basil","Pineapple","Anchovies"
        };

        int idx;
        try {
            idx = Integer.parseInt(input) - 1;
        } catch (Exception e) {
            System.out.println("Invalid.");
            return;
        }
        if (idx < 0 || idx >= regulars.length) {
            System.out.println("Invalid.");
            return;
        }

        Size size = builder.getPizza().getSize();

        // Regular toppings are included (price = 0). Still allow “extra”.
        // but PricingUtility returns 0 for non-premium categories.
        System.out.print("Add extra? (Y/N): ");
        boolean extra = scanner.nextLine().equalsIgnoreCase("Y");

        builder.addTopping(new Topping(regulars[idx], ToppingCategory.VEGGIE, extra, size));
        System.out.println("Added " + regulars[idx] + (extra ? " (extra)" : ""));
    }

    private void selectSauce(PizzaBuilder builder) {
        System.out.println("\nChoose Sauce:");
        System.out.println("1) Marinara");
        System.out.println("2) Alfredo");
        System.out.println("3) Pesto");
        System.out.println("4) BBQ");
        System.out.println("5) Buffalo");
        System.out.println("6) Olive Oil");
        System.out.println("0) Cancel");

        System.out.print("Select: ");
        String input = scanner.nextLine();
        if (input.equals("0")) return;

        String[] sauces = {"Marinara","Alfredo","Pesto","BBQ","Buffalo","Olive Oil"};

        int idx;
        try {
            idx = Integer.parseInt(input) - 1;
        } catch (Exception e) {
            System.out.println("Invalid.");
            return;
        }
        if (idx < 0 || idx >= sauces.length) {
            System.out.println("Invalid.");
            return;
        }

        Size size = builder.getPizza().getSize();

        // Sauce is included; extra doesn’t change price but customers can add extra sauce if they want!
        System.out.print("Extra? (Y/N): ");
        boolean extra = scanner.nextLine().equalsIgnoreCase("Y");

        builder.addTopping(new Topping(sauces[idx], ToppingCategory.SAUCE, extra, size));
        System.out.println("Added " + sauces[idx] + (extra ? " (extra)" : ""));
    }

    private void addDrink(Order order) {

        System.out.println("\nAdd Drink:");

        System.out.println("Select Flavor:");
        System.out.println("1) Cola");
        System.out.println("2) Lemonade");
        System.out.println("3) Water");
        System.out.println("4) Root Beer");
        System.out.println("5) Dr Pepper");
        System.out.println("0) Cancel");

        System.out.print("Select: ");
        String input = scanner.nextLine();
        if (input.equals("0")) return;

        DrinkFlavor[] flavors = {
                DrinkFlavor.COLA,
                DrinkFlavor.LEMONADE,
                DrinkFlavor.WATER,
                DrinkFlavor.ROOT_BEER,
                DrinkFlavor.DR_PEPPER
        };

        int idx;
        try {
            idx = Integer.parseInt(input) - 1;
        }

        catch (Exception e) {
            System.out.println("Invalid.");
            return;
        }

        if (idx < 0 || idx >= flavors.length) {
            System.out.println("Invalid.");
            return;
        }

        // select size
        System.out.println("Select Drink Size: ");
        System.out.println("1) " + Size.SMALL.drinkLabel());
        System.out.println("2) " + Size.MEDIUM.drinkLabel());
        System.out.println("3) " + Size.LARGE.drinkLabel());
        System.out.print("Select: ");
        String s = scanner.nextLine();

        Size size = switch (s) {
            case "1" -> Size.SMALL;
            case "2" -> Size.MEDIUM;
            case "3" -> Size.LARGE;
            default -> null;
        };

        if (size == null) {
            System.out.println("Invalid.");
            return;
        }

        double price = size.getDrinkPrice();
        DrinkFlavor flavor = flavors[idx];

        Drink drink = new Drink(flavor.toString(), size, price, flavor);

        order.addProduct(drink);
        System.out.println("Added: " + drink.getName() + " (" + size + ")");
    }

    private void addGarlicKnots(Order order) {

        System.out.println("\nAdd Garlic Knots");

        System.out.print("How many orders? (0 to cancel): ");
        String input = scanner.nextLine();
        int qty;

        try {
            qty = Integer.parseInt(input);
        }

        catch (Exception e) { System.out.println("Invalid.");
            return;
        }

        if (qty <= 0) {
            System.out.println("Canceled.");
            return;
        }

        double price = qty * 1.50;

        GarlicKnots knots = new GarlicKnots(qty);

        order.addProduct(knots);
        System.out.println("Added: Garlic Knots x" + qty);
    }

    private void addSides(Order order) {
        System.out.println("\nChoose a side (included in all sizes):");
        System.out.println("1) Red Pepper");
        System.out.println("2) Parmesan");
        System.out.println("0) Cancel");

        System.out.print("Select: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1" -> {
                order.addProduct(new Sides("Red Pepper"));
                System.out.println("Added Red Pepper (Included)");
            }
            case "2" -> {
                order.addProduct(new Sides("Parmesan"));
                System.out.println("Added Parmesan (Included)");
            }
            case "0" -> System.out.println("Canceled.");
            default -> System.out.println("Invalid selection.");
        }
    }

    private void removeItem(Order order) {
        var products = order.getProducts();

        if (products.isEmpty()) {
            System.out.println("No items to remove.");
            return;
        }

        //Show order (newest first)
        System.out.println("\nWhich item do you want to remove?");
        reviewOrder(order);

        System.out.println("Enter item number (0 to cancel): ");
        String input = scanner.nextLine();

        int choice;
        try {
            choice = Integer.parseInt(input);
        }
        catch (Exception e) {
            System.out.println("Invalid.");
            return;
        }

        if (choice == 0)
            return;

        //match the index to the customer's choice
        int realIndex = products.size() - choice;

        if (realIndex < 0 || realIndex >= products.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Product removed = products.remove(realIndex);
        System.out.println("Removed: " + removed.getName());
    }

    private void reviewOrder(Order order) {
        System.out.println("==CURRENT ORDER ITEMS==");

        var products = order.getProducts();
        if (products.isEmpty()) {
            System.out.println("No items yet.");
            return;
        }

        //Want newest first... so display in reverse order?
        for (int i = products.size() - 1, display = 1; i >= 0; i--, display++) {
            Product p = products.get(i);
            System.out.printf("%d) %s%n", display, p.toString());
        }
    }

    private void checkout(Order order) {

        var items = order.getProducts();

        //Block if no items
        if (items == null || items.isEmpty()) {
            System.out.println("Your order is empty.");
            System.out.println("Add at least one pizza OR a drink/garlic knots before checking out.");
            return;
        }

        // Display Summary
        System.out.println("====ORDER SUMMARY===");
        System.out.println(order.displaySummary());

        System.out.println("\nConfirm? (Y/N): ");
        String confirm = scanner.nextLine();

        if(!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Checkout cancelled. Returning to order menu...");
            return;
        }

        //save receipt
        FileManager fileManager = new FileManager();
        fileManager.saveOrder(order);

        System.out.println("\n Order saved!");
        System.out.println("Returning to HOME MENU...\n");
    }

    public void start() {
        System.out.println("====Welcome to Pizza Planet!====");
        homeMenu();
    }


}
