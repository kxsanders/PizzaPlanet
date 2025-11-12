package org.example.JavaSwingUI;

import org.example.Order;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;

public class PizzaPlanetApp extends JFrame {

    public PizzaPlanetApp() {

        //window title and layout
        setTitle("Pizza Planet Command Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null); //center on screen

        // Set custom window icon!
        ImageIcon image = new ImageIcon("src/main/resources/pizza.png"); //creates an ImageIcon
        setIconImage(image.getImage());

        //Now that I have a window working, let's work on the color theme
        Color spaceBlue = new Color(15, 18, 40);
        Color neonPurple = new Color(162, 102, 255);
        Color cosmicWhite = new Color(240, 240, 255);

        // === LAYOUT ===
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(spaceBlue);

        //HEADER
        JLabel topBorder = new JLabel("╭────── · · ✦ · · ──────╮", SwingConstants.CENTER);
        topBorder.setFont(new Font("SansSerif", Font.BOLD, 28));
        topBorder.setForeground(cosmicWhite);

        //LOGO
        ImageIcon pizzaIcon = new ImageIcon("src/main/resources/pizza.png");
        Image scaledImage = pizzaIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);


        //Title
        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>Welcome to Pizza Planet!<br>Ready for takeoff?</div></html>", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Orbitron", Font.BOLD, 22));
        titleLabel.setForeground(cosmicWhite);

        //stack border and title vertically
        JPanel headerPanel = new JPanel(new GridLayout(3, 1));
        headerPanel.setBackground(spaceBlue);
        headerPanel.add(topBorder);
        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);

        /// Commented this out, I feel like it looks better without it
        //Add SUBTITLE

        //JLabel subLabel = new JLabel("Prepare for your next intergalactic pizza mission", SwingConstants.CENTER);
        //subLabel.setFont(new Font("Sansserif", Font.ITALIC, 15));
        //subLabel.setForeground(neonPurple);
        //subLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 10));

        //JPanel centerPanel = new JPanel(new BorderLayout());
       //centerPanel.setBackground(spaceBlue);
       //centerPanel.add(subLabel, BorderLayout.CENTER);



        //Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 12, 12));
        buttonPanel.setBackground(spaceBlue);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 30, 100));

        JButton newOrderButton = new JButton("Launch New Mission");
        JButton exitButton = new JButton("Abort Mission");

        //Some style for buttons
        for (JButton button : new JButton[]{newOrderButton, exitButton}) {
            button.setBackground(neonPurple);
            button.setForeground(cosmicWhite);
            button.setFocusPainted(false);
            button.setFont(new Font("SansSerif", Font.BOLD, 14));
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        }

        buttonPanel.add(newOrderButton);
        buttonPanel.add(exitButton);

        //BOTTOM BORDER
        JLabel bottomBorder = new JLabel("╰────── · · ✦ · · ──────╯", SwingConstants.CENTER);
        bottomBorder.setFont(new Font("SansSerif", Font.BOLD, 26));
        bottomBorder.setForeground(cosmicWhite);

        //Panel that holds bottom border and buttons neatly
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(spaceBlue);
        footerPanel.add(buttonPanel, BorderLayout.CENTER);
        footerPanel.add(bottomBorder, BorderLayout.SOUTH);

        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        //Put it all together
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        //mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);


        //=== ACTIONS ===
        newOrderButton.addActionListener(e -> openOrderScreen());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void openOrderScreen() {
        //capture current content to restore on "Back"
        final Container homeContent = getContentPane();

        //create new order
        Order order = new Order((int)(Math.random() * 900000) + 100000, new java.util.ArrayList<>());

        // create order panel with onBack action to restore home
        Runnable onBack = () -> {
            setContentPane(homeContent);
            revalidate();
            repaint();
        };

        OrderScreenPanel panel = new OrderScreenPanel(this, order, onBack);
        setContentPane(panel);
        revalidate();
        repaint();
    }


    public static void main (String[] args) {

        SwingUtilities.invokeLater(() -> {
           PizzaPlanetApp app = new PizzaPlanetApp();
           app.setVisible(true);
        });
    }
}
