package org.example.JavaSwingUI;

import org.example.Topping;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ToppingSelectionDialog extends JDialog {

    private final DefaultListModel<Topping> selectedModel;
    private final JList<Topping> selectedList;
    private final List<Topping> availableToppings;
    private boolean confirmed = false;

    public ToppingSelectionDialog(Frame parent, List<Topping> currentToppings) {
        super(parent, "Choose Your Toppings", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(15, 18, 40));

    }

}
