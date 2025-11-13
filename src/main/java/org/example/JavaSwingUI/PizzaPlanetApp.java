package org.example.JavaSwingUI;

import org.example.Order;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;

public class PizzaPlanetApp extends JFrame {

    private JPanel homePanel;

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
        JLabel titleLabel = new JLabel(
                "<html><div style='text-align: center;'>Welcome to Pizza Planet!<br>Ready for takeoff?</div></html>",
                SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("Orbitron", Font.BOLD, 22));
        titleLabel.setForeground(cosmicWhite);

        //stack border, logo, and title vertically
        JPanel headerPanel = new JPanel(new GridLayout(3, 1));
        headerPanel.setBackground(spaceBlue);
        headerPanel.add(topBorder);
        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);

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

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Save this as the "home screen" so we can return to it later
        homePanel = mainPanel;
        setContentPane(homePanel);

        //=== ACTIONS ===
        newOrderButton.addActionListener(e -> openOrderScreen());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void openOrderScreen() {
        //create new order
        Order order = new Order((int) (Math.random() * 900000) + 100000, new java.util.ArrayList<>());

        // when user hits "Back" from order screen → return to main menu
        Runnable onBack = this::showMainMenu;

        OrderScreenPanel panel = new OrderScreenPanel(this, order, onBack);
        setContentPane(panel);
        revalidate();
        repaint();
    }

    public void showOrderScreen(Order order) {
        OrderScreenPanel panel = new OrderScreenPanel(this, order, () -> showMainMenu());
        panel.updateSummary();
        setContentPane(panel);
        revalidate();
        repaint();
    }

    public void showMainMenu() {
        // Reuse the original main menu panel
        setContentPane(homePanel);
        revalidate();
        repaint();
    }

    public void showAddPizzaScreen(Order order) {
        setContentPane(new AddPizzaPanel(this, order));
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PizzaPlanetApp app = new PizzaPlanetApp();
            app.setVisible(true);
        });
    }
}
