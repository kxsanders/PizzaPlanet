package org.example.JavaSwingUI;

import org.example.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ToppingSelectionDialog extends JDialog {


    private final PizzaBuilder builder;
    private final Size size;
    private final List<Topping> selectedToppings = new ArrayList<>();

    Color spaceBlue = new Color(15, 18, 40);
    Color neonPurple = new Color(162, 102, 255);
    Color cosmicWhite = new Color(240, 240, 255);

    public ToppingSelectionDialog(JFrame parent, PizzaBuilder builder, Size size) {
        super(parent, "Select Your Toppings", true);
        this.builder = builder;
        this.size = size;


        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        //title
        JLabel title = new JLabel("<html><div style='text-align:center;'>Customize Your Pizza<br><span style='font-size:11pt;'>Fuel your intergalactic flavor mission</span></div></html>",
                SwingConstants.CENTER);
        title.setFont(new Font("Orbitron", Font.BOLD, 18));
        title.setForeground(cosmicWhite);
        title.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(title, BorderLayout.NORTH);

        //Scrollable list of topping options
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(spaceBlue);

        //Meats
        mainPanel.add(makeCategoryLabel("Meats (Premium)"));
        addToppingCategory(mainPanel, ToppingCategory.MEAT, new String[]{"Pepperoni", "Sausage", "Ham", "Bacon", "Chicken","Meatball"}, true);

        //Cheeses
        mainPanel.add(makeCategoryLabel("Cheeses (Premium)"));
        addToppingCategory(mainPanel, ToppingCategory.CHEESE, new String[]{"Mozzarella", "Parmesan", "Ricotta", "Goat Cheese", "Buffalo"}, true);

        //Regular Toppings
        mainPanel.add(makeCategoryLabel("Veggies & Other (Included)"));
        addToppingCategory(mainPanel, ToppingCategory.VEGGIE, new String[]{"Onions", "Mushrooms", "Bell Peppers", "Olives", "Tomatoes", "Spinach", "Basil", "Pineapple", "Anchovies"}, false);

        //Sauces
        mainPanel.add(makeCategoryLabel("Sauces (Included)"));
        addToppingCategory(mainPanel, ToppingCategory.SAUCE, new String[]{"Marinara", "Alfredo", "Pesto", "BBQ", "Buffalo", "Olive Oil"}, false);

        //Sides
        mainPanel.add(makeCategoryLabel("Sides (Included)"));
        addSideCategory(mainPanel, new String[]{"Red Pepper", "Parmesan"});

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getViewport().setBackground(spaceBlue);
        add(scrollPane, BorderLayout.CENTER);


        //BUTTONS
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(spaceBlue);

        JButton confirm = new JButton("Confirm Selection");
        JButton cancel = new JButton("Cancel");
        styleButton(confirm);
        styleButton(cancel);

        confirm.addActionListener(e -> {
            for (Topping t : selectedToppings) {
                builder.addTopping(t);
            }

            dispose();

        });

        cancel.addActionListener(e -> dispose());

        buttonPanel.add(cancel);
        buttonPanel.add(confirm);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel makeCategoryLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(neonPurple);
        label.setFont(new Font("Orbitron", Font.BOLD, 14));
        label.setBorder(BorderFactory.createEmptyBorder(12, 8, 4, 8));
        return label;
    }

    private void styleButton(JButton b) {
        b.setBackground(neonPurple);
        b.setForeground(cosmicWhite);
        b.setFocusPainted(false);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    }

    private void addToppingCategory(JPanel parent, ToppingCategory category, String[] options, boolean isPremium) {
        for (String name : options) {
            JCheckBox check = new JCheckBox(name);
            JCheckBox extra = new JCheckBox("Extra");
            check.setForeground(cosmicWhite);
            check.setBackground(spaceBlue);
            extra.setForeground(neonPurple);
            extra.setBackground(spaceBlue);

            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.setBackground(spaceBlue);
            row.add(check);
            if (isPremium) row.add(extra);

            //Show the pricing text for clarity
            JLabel priceLabel = new JLabel(priceText(category, isPremium));
            priceLabel.setForeground(Color.LIGHT_GRAY);
            row.add(priceLabel);

            check.addActionListener(e -> {
                if (check.isSelected()) {
                    boolean isExtra = extra.isSelected();
                    double price = isPremium ? (category == ToppingCategory.MEAT ? PricingUtility.getMeatPrice(size, isExtra)
                            : PricingUtility.getCheesePrice(size, isExtra)) : 0.00;

                    selectedToppings.add(new Topping(name, category, isPremium, isExtra, price));
                } else {
                    selectedToppings.removeIf(t -> t.getName().equalsIgnoreCase(name));
                }
            });

            if (isPremium) {
                extra.addActionListener(e -> {
                    if (check.isSelected()) {
                        selectedToppings.removeIf(t -> t.getName().equalsIgnoreCase(name));
                        selectedToppings.add(new Topping(
                                name,
                                category,
                                true,
                                extra.isSelected(),
                                (category == ToppingCategory.MEAT
                                        ? PricingUtility.getMeatPrice(size, extra.isSelected())
                                        : PricingUtility.getCheesePrice(size, extra.isSelected()))
                        ));
                    }
                });
            }

            // Reset "extra" if topping turned off
            check.addActionListener(e -> {
                if (!check.isSelected()) {
                    extra.setSelected(false);
                }
            });

            // Add row to parent panel (critical)
            parent.add(row);
        }

    }

    private void addSideCategory(JPanel parent, String[] sides) {
        for (String name : sides) {
            JCheckBox box = new JCheckBox(name + " (Included)");
            box.setForeground(cosmicWhite);
            box.setBackground(spaceBlue);

            box.addActionListener(e -> {
                if (box.isSelected()) {
                    selectedToppings.add(new Topping(name, ToppingCategory.OTHER, false, false, 0.00));
                }
                else {
                    selectedToppings.removeIf(t -> t.getName().equalsIgnoreCase(name));
                }
            });

            parent.add(box);
        }
    }

    private String priceText(ToppingCategory cat, boolean isPremium) {
        if (!isPremium)
            return "(Included)";

        double base = (cat == ToppingCategory.MEAT) ? PricingUtility.getMeatPrice(size, false) : PricingUtility.getCheesePrice(size, false);
        double extra = (cat == ToppingCategory.MEAT) ? PricingUtility.getMeatPrice(size, true) : PricingUtility.getCheesePrice(size, true);
        return String.format("($%.2f / extra +$%.2f)", base, extra);
    }
}
