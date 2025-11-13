package org.example.JavaSwingUI;

import org.example.PizzaBuilder;
import org.example.Size;
import org.example.Topping;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ToppingSelectionDialog extends JDialog {
    private final PizzaBuilder builder;
    private final Size size;
    private final List<Topping> selectedToppings = new ArrayList<>();

    public ToppingSelectionDialog(JFrame parent, PizzaBuilder builder, Size size) {
        super(parent, "Select Your Toppings", true);
        this.builder = builder;
        this.size = size;


        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(15, 18, 40));

        JLabel title = new JLabel("Customize Your Pizza", SwingConstants.CENTER);
        title.setFont(new Font("Orbitron", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(title, BorderLayout.NORTH);

        //PANELS
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Meats", createCategoryPanel());

        //Bottom buttons
        //Helper to build each category panel
    }


}
