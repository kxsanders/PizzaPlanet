🌌 Pizza Planet POS System

A Point-of-Sale desktop application for building & managing pizza orders.

🚀 Overview

Pizza Planet is a fully interactive point-of-sale (POS) application designed for a custom pizza shop. Customers can build pizzas from scratch, choose signature pizzas, add drinks, garlic knots, and sides, and complete an order. The system supports:

A full Java Swing GUI

A complete Console/CLI interface

Object-oriented design with builders, helpers, enums, and inheritance

Automatic receipt generation (TXT files)

Persistent CSV order logging

Fully customizable toppings (premium, regular, sauces, extras)

Signature pizza templates

This is a complete implementation of the PIZZA-licious project requirements — with optional bonus features included.

🧠 Key Features
✔ Custom Pizza Builder

Customers can:

Select size (8", 12", 16")

Choose crust (thin, regular, thick, cauliflower)

Add meats, cheeses, veggies, sauces

Add extra premium toppings

Select stuffed crust

✔ Signature Pizzas (BONUS)

Pre-configured pizzas include:

Cosmic Margherita

Nebula Veggie

Supernova Supreme

Meteor Meatstorm

Lunar Hawaiian

Each signature pizza can still be customized through the toppings dialog.

✔ Additional Menu Items

🍹 Drinks (S/M/L, multiple flavors)

🥖 Garlic Knots

✨ Free sides (Red Pepper, Parmesan)

✔ Order Management

The order screen displays:

All items added (newest first)

Full order summary

Live total calculation

Actions:

Add pizza/drinks/knots/sides

Remove items

Review order

Checkout / cancel

✔ Receipt Generation

Every completed order automatically creates:

A timestamped TXT receipt

Saved under src/main/resources/receipts/yyyyMMdd-HHmmss.txt

A CSV history entry

Appended to src/main/resources/receipts/Receipt.csv

✔ GUI + Console Supported

You can run the:

GUI version (Swing UI — recommended)

Console version for testing or simple operation

🏛 Architectural Overview
🧩 Object-Oriented Design Principles

The system uses:

Inheritance (Pizza, Drink, GarlicKnots, and Sides inherit from Product)

Composition (Pizza contains many Topping objects)

Builder Pattern (PizzaBuilder for custom pizzas)

Helper Classes (SignaturePizzaHelper)

Enums for structured data (Size, CrustType, ToppingCategory, DrinkFlavor)

Utility Class (PricingUtility for all pricing logic)

🗃 File Structure
src/main/java/org/example/
│
├── PizzaPlanetApp.java         (Main GUI application)
├── OrderScreenPanel.java       (Shows order + add-item options)
├── AddPizzaPanel.java          (Pizza creation screen)
├── ToppingSelectionDialog.java (Scrollable topping picker)
│
├── Order.java                  (Holds products, calculates total)
├── Product.java                (Base class)
│   ├── Pizza.java
│   ├── Drink.java
│   ├── GarlicKnots.java
│   └── Sides.java
│
├── PizzaBuilder.java           (Build-your-own builder)
├── SignaturePizzaHelper.java   (Preset pizza templates)
├── Topping.java                (Premium/regular topping data)
├── ToppingCategory.java        (Enum)
│
├── Size.java                   (Enum: price logic per size)
├── CrustType.java              (Enum)
├── DrinkFlavor.java            (Enum)
│
└── FileManager.java            (Saves TXT + CSV receipts)

🧾 Pricing Logic

All price calculations follow the project’s rules:

Base Pizza Prices
Size	Price
8"	$8.50
12"	$12.00
16"	$16.50
Premium Toppings

Handled using PricingUtility:

Meat: 1 / 2 / 3

Extra meat: +.50 / +1.00 / +1.50

Cheese: .75 / 1.50 / 2.25

Extra cheese: +.30 / +.60 / +.90

Regular Toppings

Always free.

Sauces

Always free.

Other Items

Drinks: $2.00 / $2.50 / $3.00

Garlic Knots: $1.50

📦 Running the Application
GUI Version
Run PizzaPlanetApp.main()

Console Version
Run UserInterface.start()

🧪 Receipt Output Examples
📄 TXT Receipt
==================================================
                PIZZA PLANET RECEIPT              
==================================================
Order ID: 123456
Date: 2025-02-18T14:32
--------------------------------------------------
Large Pizza - Regular Crust
Toppings: Pepperoni, Mushrooms
Total: $18.50
--------------------------------------------------
TOTAL: $21.00
==================================================
Thank you for choosing Pizza Planet!
==================================================

📊 CSV Entry
OrderID,ProductType,Name,Size,Crust,Toppings,Stuffed,Qty,Price
123456,Pizza,Custom Pizza,MEDIUM,REGULAR,"Pepperoni | Basil",false,,14.50
123456,Drink,Cola,LARGE,,,,1,3.00
,,TOTAL,,,,,,17.50

🌟 Bonus Features Included

Signature pizza system (fully functional)

Toppings selection UI with scrolling & categories

Space-themed interface styling

Builder pattern enhancements

Order summary auto-updates

Guaranteed CSV append (no overwriting)

Unified PricingUtility class
