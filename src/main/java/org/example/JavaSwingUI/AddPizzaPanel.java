package org.example.JavaSwingUI;

import org.example.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddPizzaPanel extends JPanel {

    private final PizzaPlanetApp app;
    private final Order order;

    private JComboBox<Size> sizeBox;
    private JComboBox<CrustType> crustBox;
    private JCheckBox stuffedBox;

    private PizzaBuilder builder = new PizzaBuilder();   // <-- for topping dialog use

    private java.util.List<Topping> chosenToppings = new ArrayList<>();

    public AddPizzaPanel(PizzaPlanetApp app, Order order) {
        this.app = app;
        this.order = order;

        setLayout(new BorderLayout());
        setBackground(new Color(15,18,40));

        JLabel title = new JLabel("🚀 Build Your Galactic Pizza", SwingConstants.CENTER);
        title.setFont(new Font("Orbitron", Font.BOLD, 20));
        title.setForeground(new Color(240,240,255));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        add(title, BorderLayout.NORTH);

        // ==== CENTER PANEL ====
        JPanel form = new JPanel(new GridLayout(4,2,10,10));
        form.setBackground(new Color(15,18,40));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        //Size
        form.add(makeLabel("Size:"));
        sizeBox = new JComboBox<>(Size.values());
        form.add(sizeBox);

        //Crust
        form.add(makeLabel("Crust:"));
        crustBox = new JComboBox<>(CrustType.values());
        form.add(crustBox);

        //Stuffed Crust
        form.add(makeLabel("Stuffed Crust:"));
        stuffedBox = new JCheckBox("Yes");
        stuffedBox.setBackground(new Color(15,18,40));
        stuffedBox.setForeground(Color.WHITE);
        form.add(stuffedBox);

        //Toppings button
        form.add(makeLabel("Toppings:"));
        JButton toppingsBtn = new JButton("Select Toppings");
        styleButton(toppingsBtn);
        form.add(toppingsBtn);

        add(form, BorderLayout.CENTER);

        // ==== BOTTOM BUTTONS ====
        JPanel bottom = new JPanel(new GridLayout(1,2,12,12));
        bottom.setBackground(new Color(15,18,40));
        bottom.setBorder(BorderFactory.createEmptyBorder(20,100,30,100));

        JButton addPizzaBtn = new JButton("Add Pizza to Mission");
        JButton cancelBtn = new JButton("Cancel");

        for (JButton b : new JButton[]{addPizzaBtn, cancelBtn}) {
            styleButton(b);
        }

        bottom.add(cancelBtn);
        bottom.add(addPizzaBtn);

        add(bottom, BorderLayout.SOUTH);

        // ==== ACTIONS ====

        // Open topping dialog
        toppingsBtn.addActionListener(e -> openToppingDialog());

        // save pizza + return
        addPizzaBtn.addActionListener(e -> savePizza());

        cancelBtn.addActionListener(e -> app.showOrderScreen(order));
    }

    // ============ LOGIC ============

    private void openToppingDialog() {
        Size size = (Size) sizeBox.getSelectedItem();

        builder.startNewPizza(
                "Custom Pizza",
                size,
                PricingUtility.getBasePrice(size),
                (CrustType) crustBox.getSelectedItem(),
                stuffedBox.isSelected()
        );

        ToppingSelectionDialog dialog = new ToppingSelectionDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                builder,
                size
        );

        dialog.setVisible(true);

        // After the dialog closes, builder has toppings added
        chosenToppings = new ArrayList<>(builder.getPizza().getToppings());

        JOptionPane.showMessageDialog(this,
                "Toppings updated!\nSelected: " + chosenToppings.size());
    }

    private void savePizza() {
        Size size = (Size) sizeBox.getSelectedItem();
        CrustType crust = (CrustType) crustBox.getSelectedItem();
        boolean stuffed = stuffedBox.isSelected();

        double base = PricingUtility.getBasePrice(size);

        Pizza finalPizza = new Pizza(
                "Custom Pizza",
                size,
                base,
                crust,
                stuffed,
                chosenToppings
        );

        order.addProduct(finalPizza);

        JOptionPane.showMessageDialog(this,
                "Your cosmic pizza has been added to the mission!");

        app.showOrderScreen(order);
    }

    // ============ HELPERS ============

    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(new Color(162,102,255));
        lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        return lbl;
    }

    private void styleButton(JButton b) {
        b.setBackground(new Color(162,102,255));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif", Font.BOLD, 14));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }
}
