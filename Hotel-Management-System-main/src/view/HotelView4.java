package view;

import controller.HotelController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HotelView4 extends JFrame implements ActionListener {
    private HotelController controller;
    private JButton customerButton, managerButton, receptionistButton, ownerButton;

    public HotelView4(HotelController controller) {
        this.controller = controller;

        setTitle("Hotel Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a gradient panel for the background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();

                // Background gradient (diagonal dark blue to red-pink)
                Color color1 = new Color(39, 60, 117); // Dark blue
                Color color2 = new Color(221, 78, 102); // Red-pink
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };

        mainPanel.setLayout(new BorderLayout());

        // Create a fancy header title
        JLabel titleLabel = new JLabel("Hotel Management System", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE); // White font color
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Transparent background
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Create styled buttons with black background and white font
        customerButton = createStyledButton("Customer");
        customerButton.addActionListener(this);
        managerButton = createStyledButton("Manager");
        managerButton.addActionListener(this);
        receptionistButton = createStyledButton("Receptionist");
        receptionistButton.addActionListener(this);
        ownerButton = createStyledButton("Owner");
        ownerButton.addActionListener(this);

        buttonPanel.add(customerButton);
        buttonPanel.add(managerButton);
        buttonPanel.add(receptionistButton);
        buttonPanel.add(ownerButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Set the main panel as the content pane
        setContentPane(mainPanel);

        // Set the controller
        setController(controller);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE); // White font color
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        // Set button dimensions and styling
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(new Color(39, 60, 117, 161)); // Default button color

        // Add hover effect using MouseListener
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change button color on hover
                button.setBackground(new Color(221, 78, 102, 137)); // Hover color
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Revert to default color when mouse exits
                button.setBackground(new Color(39, 60, 117, 161)); // Default button color
            }
        });

        return button;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == customerButton) {
            openCustomerWindow();
        } else if (e.getSource() == managerButton) {
            openManagerWindow();
        } else if (e.getSource() == receptionistButton) {
            openReceptionistWindow();
        } else if (e.getSource() == ownerButton) {
            openOwnerWindow();
        }
    }

    private void openCustomerWindow() {
        // Create a new JFrame for the customer window
        JFrame customerWindow = new JFrame("Customer Window");
        customerWindow.setSize(600, 600); // Increase window size
        customerWindow.setLayout(new BorderLayout());

        // Create a panel with a background gradient
        JPanel inputPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();

                // Background gradient (diagonal dark blue to red-pink)
                Color color1 = new Color(39, 60, 117); // Dark blue
                Color color2 = new Color(221, 78, 102); // Red-pink
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };

        inputPanel.setLayout(new GridLayout(10, 2, 10, 10));

        // Create and style labels with white Arial font
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        Color labelColor = Color.WHITE;

        JLabel nameLabel = createStyledLabel("Name:", labelFont, labelColor);
        JTextField nameField = new JTextField();

        JLabel idLabel = createStyledLabel("ID:", labelFont, labelColor);
        JTextField idField = new JTextField();

        JLabel phoneLabel = createStyledLabel("Phone Number:", labelFont, labelColor);
        JTextField phoneField = new JTextField();

        JLabel addressLabel = createStyledLabel("Address:", labelFont, labelColor);
        JTextField addressField = new JTextField();

        JLabel roomNumberLabel = createStyledLabel("Room Number:", labelFont, labelColor);
        JTextField roomNumberField = new JTextField();

        JLabel foodLabel = createStyledLabel("Order Food:", labelFont, labelColor);
        JComboBox<String> foodComboBox = new JComboBox<>(new String[]{"Burger", "Pizza", "Pasta", "Salad", "Sandwich"});

        JLabel quantityLabel = createStyledLabel("Quantity:", labelFont, labelColor);
        JTextField quantityField = new JTextField();

        // Add components to inputPanel
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);
        inputPanel.add(roomNumberLabel);
        inputPanel.add(roomNumberField);
        inputPanel.add(foodLabel);
        inputPanel.add(foodComboBox);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);

        // Create and style buttons
        JButton checkInButton = createStyledButton("Check-In", labelFont, labelColor);
        JButton checkOutButton = createStyledButton("Check-Out", labelFont, labelColor);
        JButton orderFoodButton = createStyledButton("Order Food", labelFont, labelColor);
        JButton displayBillButton = createStyledButton("Display Bill", labelFont, labelColor);

        // Add action listeners to buttons
        checkInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle check-in logic
                String name = nameField.getText();
                String id = idField.getText();
                String phoneNumber = phoneField.getText();
                String address = addressField.getText();
                int roomNumber = Integer.parseInt(roomNumberField.getText());

                boolean checkInSuccessful = controller.checkIn(name, id, phoneNumber, address, roomNumber);

                if (checkInSuccessful) {
                    JOptionPane.showMessageDialog(customerWindow, "Checked in successfully!");
                } else {
                    JOptionPane.showMessageDialog(customerWindow, "Room " + roomNumber + " is already booked.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        checkOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle check-out logic
                String roomNumberStr = JOptionPane.showInputDialog(customerWindow, "Enter Room Number:");
                if (roomNumberStr != null && !roomNumberStr.isEmpty()) {
                    int roomNumber = Integer.parseInt(roomNumberStr);
                    controller.checkOut(roomNumber);
                    JOptionPane.showMessageDialog(customerWindow, "Checked out successfully!");
                } else {
                    JOptionPane.showMessageDialog(customerWindow, "Please enter a valid room number.");
                }
            }
        });

        orderFoodButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle food ordering logic
                String selectedFood = (String) foodComboBox.getSelectedItem();
                int quantity = Integer.parseInt(quantityField.getText());
                String customerName = nameField.getText();
                controller.orderFood(selectedFood, quantity, customerName);
                JOptionPane.showMessageDialog(customerWindow, "Food ordered successfully!");
            }
        });

        displayBillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle displaying bill logic
                String roomNumberStr = roomNumberField.getText();
                if (!roomNumberStr.isEmpty()) {
                    int roomNumber = Integer.parseInt(roomNumberStr);
                    Bill bill = controller.getBill(roomNumber);
                    displayBillFrame(bill.getCustomerName(), bill);
                } else {
                    JOptionPane.showMessageDialog(customerWindow, "Please enter a room number.");
                }
            }
        });

        // Add buttons to inputPanel
        inputPanel.add(checkInButton);
        inputPanel.add(checkOutButton);
        inputPanel.add(orderFoodButton);
        inputPanel.add(displayBillButton);

        // Add inputPanel to customerWindow
        customerWindow.add(inputPanel, BorderLayout.NORTH);

        // Make the customerWindow visible
        customerWindow.setVisible(true);
    }

    // Helper method to create styled JLabel with specified font and color

    private void openManagerWindow() {
        // Create a new JFrame for the manager window
        JFrame managerWindow = new JFrame("Manager Window");
        managerWindow.setSize(600, 250); // Increase window size
        managerWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel with a background gradient
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();

                // Background gradient (diagonal dark blue to red-pink)
                Color color1 = new Color(39, 60, 117); // Dark blue
                Color color2 = new Color(221, 78, 102); // Red-pink
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };

        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));

        // Create and style buttons
        JButton addInventoryButton = createStyledButton("Add Inventory Item");
        JButton deleteInventoryButton = createStyledButton("Delete Inventory Item");
        JButton addFoodItemButton = createStyledButton("Add Food Item");
        JButton deleteFoodItemButton = createStyledButton("Delete Food Item");

        // Add action listeners to buttons
        addInventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName = JOptionPane.showInputDialog(managerWindow, "Enter Item Name:");
                if (itemName != null && !itemName.isEmpty()) {
                    String quantityStr = JOptionPane.showInputDialog(managerWindow, "Enter Quantity:");
                    try {
                        int quantity = Integer.parseInt(quantityStr);
                        if (quantity > 0) {
                            controller.addInventoryItem(itemName, quantity);
                            JOptionPane.showMessageDialog(managerWindow, "Item added to inventory.");
                        } else {
                            JOptionPane.showMessageDialog(managerWindow, "Quantity must be a positive integer.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(managerWindow, "Invalid quantity.");
                    }
                }
            }
        });

        deleteInventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName = JOptionPane.showInputDialog(managerWindow, "Enter Item Name to Delete:");
                if (itemName != null && !itemName.isEmpty()) {
                    controller.deleteInventoryItem(itemName);
                    JOptionPane.showMessageDialog(managerWindow, "Item deleted from inventory.");
                }
            }
        });

        addFoodItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String foodName = JOptionPane.showInputDialog(managerWindow, "Enter Food Item Name:");
                if (foodName != null && !foodName.isEmpty()) {
                    String priceStr = JOptionPane.showInputDialog(managerWindow, "Enter Price:");
                    try {
                        double price = Double.parseDouble(priceStr);
                        if (price > 0) {
                            controller.addFoodItem(foodName, price);
                            JOptionPane.showMessageDialog(managerWindow, "Food item added.");
                        } else {
                            JOptionPane.showMessageDialog(managerWindow, "Price must be a positive number.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(managerWindow, "Invalid price.");
                    }
                }
            }
        });

        deleteFoodItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String foodName = JOptionPane.showInputDialog(managerWindow, "Enter Food Item Name to Delete:");
                if (foodName != null && !foodName.isEmpty()) {
                    controller.deleteFoodItem(foodName);
                    JOptionPane.showMessageDialog(managerWindow, "Food item deleted.");
                }
            }
        });

        // Add buttons to mainPanel
        mainPanel.add(addInventoryButton);
        mainPanel.add(deleteInventoryButton);
        mainPanel.add(addFoodItemButton);
        mainPanel.add(deleteFoodItemButton);

        // Add mainPanel to managerWindow
        managerWindow.add(mainPanel, BorderLayout.NORTH);

        // Make the managerWindow visible
        managerWindow.setVisible(true);
    }

    private void openReceptionistWindow() {
        // Create a new JFrame for the receptionist window
        JFrame receptionistWindow = new JFrame("Receptionist Window");
        receptionistWindow.setSize(600, 400); // Set window size

        // Create a panel with a gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();

                // Background gradient (diagonal dark blue to red-pink)
                Color color1 = new Color(39, 60, 117); // Dark blue
                Color color2 = new Color(221, 78, 102); // Red-pink
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };

        mainPanel.setLayout(new GridLayout(3, 1, 10, 10));

        // Create and style labels with white Arial font
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        Color labelColor = Color.WHITE;
        // Room Number Input
        JLabel roomNumberLabel = createStyledLabel( "Enter Room Number:", labelFont, labelColor);
        JTextField roomNumberField = new JTextField();

//        "Enter Room Number:"
        mainPanel.add(roomNumberLabel);
        mainPanel.add(roomNumberField);

        // Create and style buttons
        JButton checkRoomAvailabilityButton = createStyledButton("Check Room Availability");
        JButton viewAvailableRoomsButton = createStyledButton("View Available Rooms");

        // Add action listeners to buttons
        checkRoomAvailabilityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int roomNumber = Integer.parseInt(roomNumberField.getText());
                    boolean isRoomAvailable = controller.checkRoomAvailability(roomNumber);

                    if (isRoomAvailable) {
                        JOptionPane.showMessageDialog(receptionistWindow, "Room " + roomNumber + " is available.");
                    } else {
                        JOptionPane.showMessageDialog(receptionistWindow, "Room " + roomNumber + " is already booked.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(receptionistWindow, "Invalid room number. Please enter a valid number.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(receptionistWindow, "Room " + roomNumberField.getText() + " not found in the database.");
                }
            }
        });

        viewAvailableRoomsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Room> availableRooms = controller.getAllAvailableRooms();

                if (availableRooms.isEmpty()) {
                    JOptionPane.showMessageDialog(receptionistWindow, "No rooms are currently available.");
                } else {
                    StringBuilder message = new StringBuilder("Available Rooms:\n");
                    for (Room room : availableRooms) {
                        message.append("Room ").append(room.getRoomNumber()).append("\n");
                    }
                    JOptionPane.showMessageDialog(receptionistWindow, message.toString());
                }
            }
        });

        // Add buttons to main panel
        mainPanel.add(checkRoomAvailabilityButton);
        mainPanel.add(viewAvailableRoomsButton);

        // Add main panel to receptionist window
        receptionistWindow.add(mainPanel, BorderLayout.CENTER);

        // Make the receptionist window visible
        receptionistWindow.setVisible(true);
    }


    private void openOwnerWindow() {
        // Create a new JFrame for the owner window
        JFrame ownerWindow = new JFrame("Owner Window");
        ownerWindow.setSize(600, 400); // Set window size

        // Create a panel with a gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();

                // Background gradient (diagonal dark blue to red-pink)
                Color color1 = new Color(39, 60, 117); // Dark blue
                Color color2 = new Color(221, 78, 102); // Red-pink
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };

        mainPanel.setLayout(new GridLayout(2, 1, 10, 10));

        // Create and style buttons
        JButton viewInventoryButton = createStyledButton("View Inventory");
        JButton viewFoodItemsButton = createStyledButton("View Food Items");

        // Add action listeners to buttons
        viewInventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayInventory(ownerWindow);
            }
        });

        viewFoodItemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayFoodItems();
            }
        });

        // Add buttons to main panel
        mainPanel.add(viewInventoryButton);
        mainPanel.add(viewFoodItemsButton);

        // Add main panel to owner window
        ownerWindow.add(mainPanel, BorderLayout.CENTER);

        // Make the owner window visible
        ownerWindow.setVisible(true);
    }


    private void displayInventory(JFrame parentFrame) {
        // Create a JTextArea to display the inventory
        JTextArea inventoryTextArea = new JTextArea(10, 30);
        inventoryTextArea.setEditable(false);

        // Retrieve the inventory items from the controller
        String inventoryItems = controller.getAllInventoryItems();

        // Clear existing text in the JTextArea and append the retrieved inventory items
        inventoryTextArea.setText("");
        inventoryTextArea.append(inventoryItems);

        // Create a JScrollPane to allow scrolling in case of large inventory data
        JScrollPane scrollPane = new JScrollPane(inventoryTextArea);

        // Clear the existing content of the parentFrame and add the new inventory display
        parentFrame.getContentPane().removeAll();
        parentFrame.add(scrollPane, BorderLayout.CENTER);
        parentFrame.revalidate();
        parentFrame.repaint();
    }


    private void displayFoodItems() {
        // Create a new JFrame for displaying food items
        JFrame foodItemsFrame = new JFrame("Food Items");
        foodItemsFrame.setSize(400, 300);

        // Create a JTextArea to display food items
        JTextArea foodItemsTextArea = new JTextArea(10, 30);
        foodItemsTextArea.setEditable(false);

        // Retrieve and display all food items
        String foodItemsList = controller.getAllFoodItems();
        foodItemsTextArea.append(foodItemsList);

        JScrollPane scrollPane = new JScrollPane(foodItemsTextArea);
        foodItemsFrame.add(scrollPane, BorderLayout.CENTER);

        // Make the foodItemsFrame visible
        foodItemsFrame.setVisible(true);
    }


    private void displayBillFrame(String customerName, Bill bill) {
        JFrame billFrame = new JFrame("Customer Bill");
        billFrame.setSize(300, 200);
        billFrame.setLayout(new BorderLayout());

        JTextArea billDetailsArea = new JTextArea(10, 30);
        billDetailsArea.setEditable(false);

        if (bill != null) {
            billDetailsArea.append("Customer: " + customerName + "\n");
            billDetailsArea.append("Total Bill Amount: $" + bill.getTotalAmount() + "\n");
        } else {
            billDetailsArea.append("No bill found for this customer.\n");
        }

        JScrollPane scrollPane = new JScrollPane(billDetailsArea);
        billFrame.add(scrollPane, BorderLayout.CENTER);

        billFrame.setVisible(true);
    }

    private JLabel createStyledLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    // Helper method to create styled JButton with specified font and color
    private JButton createStyledButton(String text, Font font, Color color) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        // Set button dimensions and styling
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(new Color(39, 60, 117, 161)); // Default button color

        // Add hover effect using MouseListener
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change button color on hover
                button.setBackground(new Color(221, 78, 102, 137)); // Hover color
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Revert to default color when mouse exits
                button.setBackground(new Color(39, 60, 117, 161)); // Default button color
            }
        });

        return button;
    }

    public void setController(HotelController controller) {
        this.controller = controller;
    }
}
