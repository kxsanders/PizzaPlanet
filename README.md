# 🌌 Pizza Planet POS System  
*A Point-of-Sale desktop application for building & managing custom pizza orders.*

## 🚀 Overview
Pizza Planet is a fully interactive **Java POS system** built with a Swing GUI and optional console mode. Customers can build pizzas, select signature pizzas, add drinks, garlic knots, and sides, and complete orders. The system includes receipt generation, CSV order logging, and full OOP architecture.

This implementation meets **all required project specifications**, plus optional bonus features.

---

# 🧠 Key Features

### ✔ Custom Pizza Builder
- Choose size (8", 12", 16")
- Crust selection (thin, regular, thick, cauliflower)
- Meats, cheeses, veggies, sauces
- Premium toppings support (extra meat/cheese with pricing)
- Stuffed crust option

### ✔ Signature Pizzas (BONUS)
Pre-built templates that can be customized:
- Cosmic Margherita  
- Nebula Veggie  
- Supernova Supreme  
- Meteor Meatstorm  
- Lunar Hawaiian  

### ✔ Additional Menu Items
- 🍹 Drinks (multiple flavors & sizes)  
- 🥖 Garlic Knots  
- ✨ Free sides: Red Pepper, Parmesan  

### ✔ Order Management
- Add/remove items  
- View order summary  
- Auto-updating totals  
- Newest items shown first  
- Cancel or checkout an order  

### ✔ Receipt System
Every checkout creates:
1. **TXT receipt** named `yyyyMMdd-HHmmss.txt`  
2. **CSV log entry** appended to:  
   `src/main/resources/receipts/Receipt.csv`

### ✔ GUI + Console Support
- **Swing UI** (primary user experience)  
- **Console version** for testing or alternative use  

---

# 🏛 Architecture & Design

### Object-Oriented Concepts Used
- **Inheritance:** `Pizza`, `Drink`, `GarlicKnots`, `Sides` → `Product`
- **Composition:** `Pizza` contains many `Topping` objects
- **Builder Pattern:** `PizzaBuilder` manages custom pizza construction
- **Utility Classes:** `PricingUtility` centralizes all pricing rules
- **Enums:** `Size`, `CrustType`, `ToppingCategory`, `DrinkFlavor`
- **Helper Classes:** `SignaturePizzaHelper` manages preset pizzas

### File Structure
```
src/main/java/org/example/
│
├── PizzaPlanetApp.java         # Main GUI
├── OrderScreenPanel.java
├── AddPizzaPanel.java
├── ToppingSelectionDialog.java
│
├── Order.java                  # Stores products for one order
├── Product.java                # Abstract base class
│   ├── Pizza.java
│   ├── Drink.java
│   ├── GarlicKnots.java
│   └── Sides.java
│
├── PizzaBuilder.java
├── SignaturePizzaHelper.java
├── Topping.java
├── ToppingCategory.java
│
├── Size.java
├── CrustType.java
├── DrinkFlavor.java
│
└── FileManager.java            # Saves TXT & CSV receipts
```

---

# 💰 Pricing Rules

### Base Pizza Prices  
| Size | Price |
|------|--------|
| 8"   | $8.50  |
| 12"  | $12.00 |
| 16"  | $16.50 |

### Premium Toppings  
Meats: 1 / 2 / 3  
Extra Meat: +0.50 / +1.00 / +1.50  
Cheese: 0.75 / 1.50 / 2.25  
Extra Cheese: +0.30 / +0.60 / +0.90  

### Regular Toppings & Sauces  
Always **free**.

### Other Items  
- Drinks → $2.00 / $2.50 / $3.00  
- Garlic Knots → $1.50  

All logic is calculated through `PricingUtility`.

---

# ▶ Running the Application

### GUI Version  
```
Run PizzaPlanetApp.main()
```

### Console Version  
```
Run UserInterface.start()
```

---

# 🧾 Sample Receipt (TXT)
```
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
```

# 📊 CSV Entry
```
OrderID,ProductType,Name,Size,Crust,Toppings,Stuffed,Qty,Price
123456,Pizza,Custom Pizza,MEDIUM,REGULAR,"Pepperoni | Basil",false,,14.50
123456,Drink,Cola,LARGE,,,,1,3.00
,,TOTAL,,,,,,17.50
```

---

# 🌟 Bonus Features Included
- Signature pizza system  
- Space-themed Swing UI  
- Scrollable topping picker  
- Builder pattern customization  
- Live order summary updates  
- Guaranteed CSV append  
- Custom pricing engine  

---

# ✅ Project Status: COMPLETE
All required features + optional signature pizza challenge have been implemented with clean OOP structure, GUI support, and persistent receipt storage.

Let me know if you want:
- A presentation script  
- A class diagram image  
- A flowchart  
- More README styling  
- A link-style table of contents  

Happy to help polish it! 🚀

