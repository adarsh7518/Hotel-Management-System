# Hotel Management System

![Java](https://img.shields.io/badge/Java-100%25-orange)
![MongoDB](https://img.shields.io/badge/Database-MongoDB-green)

## Overview

This project is a Hotel Management System built using Java Swing for the user interface and MongoDB for the database. The system allows users to manage various aspects of hotel operations including room booking, customer management, and billing.

## Features

- **Room Booking**: Allows customers to book rooms, check availability, and manage reservations.
- **Customer Management**: Stores and manages customer information.
- **Billing System**: Generates bills for the customers based on their stay and services used.
- **User-friendly Interface**: Built using Java Swing for a responsive and interactive UI.
- **Database Management**: Uses MongoDB to store and manage data efficiently.

## Installation

### Prerequisites

- [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (version 11 or higher)
- [MongoDB](https://www.mongodb.com/try/download/community) (version 4.4 or higher)

### Steps

1. **Clone the repository:**
    ```sh
    git clone https://github.com/praths71018/Hotel-Management-System.git
    cd Hotel-Management-System
    ```

2. **Set up MongoDB:**
   - Install MongoDB and start the MongoDB server.
   - Create a database named `hotel_management`.

3. **Configure the application:**
   - Update the MongoDB connection settings in the `config` package.

4. **Compile and run the application:**
    ```sh
    javac -d bin src/*.java
    java -cp bin Main
    ```

## Usage

1. **Start the Application:**
   - Run the main class to launch the application.
   - The main UI window will appear.

2. **Room Booking:**
   - Navigate to the booking section.
   - Select the room type and dates.
   - Enter customer details and confirm the booking.

3. **Manage Customers:**
   - Navigate to the customer management section.
   - Add, update, or delete customer information.

4. **Billing:**
   - Navigate to the billing section.
   - Generate bills for the customers based on their stay and services used.

## Design Patterns Used

1. **Singleton Pattern**:
   - **Used In**: Database Connection Manager
   - **Description**: Ensures a class has only one instance and provides a global point of access to it. For example, a MongoDB connection manager that ensures only one instance of the database connection is used throughout the application.
   - **Code Example**:
     ```java
     public class MongoDBConnection {
         private static MongoDBConnection instance;
         private MongoClient client;
         
         private MongoDBConnection() {
             // Initialize MongoClient
         }

         public static MongoDBConnection getInstance() {
             if (instance == null) {
                 instance = new MongoDBConnection();
             }
             return instance;
         }

         public MongoClient getClient() {
             return client;
         }
     }
     ```

2. **Factory Pattern**:
   - **Used In**: Room Creation
   - **Description**: Defines an interface for creating an object, but lets subclasses alter the type of objects that will be created. For example, creating different types of rooms (Standard, Deluxe).
   - **Code Example**:
     ```java
     public abstract class RoomFactory {
         public abstract Room createRoom();
     }

     public class StandardRoomFactory extends RoomFactory {
         @Override
         public Room createRoom() {
             return new StandardRoom();
         }
     }

     public class DeluxeRoomFactory extends RoomFactory {
         @Override
         public Room createRoom() {
             return new DeluxeRoom();
         }
     }
     ```

3. **Iterator Pattern**:
   - **Used In**: Customer List Management
   - **Description**: Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation. For example, iterating over a list of customers.
   - **Code Example**:
     ```java
     public interface Iterator {
         boolean hasNext();
         Object next();
     }

     public class CustomerIterator implements Iterator {
         private List<Customer> customers;
         private int position;

         public CustomerIterator(List<Customer> customers) {
             this.customers = customers;
             this.position = 0;
         }

         @Override
         public boolean hasNext() {
             return position < customers.size();
         }

         @Override
         public Object next() {
             if (this.hasNext()) {
                 return customers.get(position++);
             }
             return null;
         }
     }
     ```

4. **Observer Pattern**:
   - **Used In**: Notification System
   - **Description**: Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically. For example, notifying staff members about new bookings.
   - **Code Example**:
     ```java
     public interface Observer {
         void update(String message);
     }

     public class Staff implements Observer {
         private String name;

         public Staff(String name) {
             this.name = name;
         }

         @Override
         public void update(String message) {
             System.out.println(name + " received notification: " + message);
         }
     }

     public class BookingNotifier {
         private List<Observer> observers;

         public BookingNotifier() {
             observers = new ArrayList<>();
         }

         public void addObserver(Observer observer) {
             observers.add(observer);
         }

         public void notifyObservers(String message) {
             for (Observer observer : observers) {
                 observer.update(message);
             }
         }
     }
     ```

5. **Strategy Pattern**:
   - **Used In**: Payment Processing
   - **Description**: Defines a family of algorithms, encapsulates each one, and makes them interchangeable. The strategy pattern lets the algorithm vary independently from clients that use it. For example, different payment methods (Credit Card, PayPal).
   - **Code Example**:
     ```java
     public interface PaymentStrategy {
         void pay(double amount);
     }

     public class CreditCardPayment implements PaymentStrategy {
         @Override
         public void pay(double amount) {
             // Process credit card payment
         }
     }

     public class PayPalPayment implements PaymentStrategy {
         @Override
         public void pay(double amount) {
             // Process PayPal payment
         }
     }

     public class PaymentContext {
         private PaymentStrategy strategy;

         public void setPaymentStrategy(PaymentStrategy strategy) {
             this.strategy = strategy;
         }

         public void executePayment(double amount) {
             strategy.pay(amount);
         }
     }
     ```

6. **Chain of Responsibility Pattern**:
   - **Used In**: Request Handling
   - **Description**: Avoids coupling the sender of a request to its receiver by giving more than one object a chance to handle the request. Chains the receiving objects and passes the request along the chain until an object handles it. For example, handling different service requests (Room Service, Laundry Service).
   - **Code Example**:
     ```java
     public abstract class ServiceHandler {
         protected ServiceHandler nextHandler;

         public void setNextHandler(ServiceHandler nextHandler) {
             this.nextHandler = nextHandler;
         }

         public abstract void handleRequest(String request);
     }

     public class RoomServiceHandler extends ServiceHandler {
         @Override
         public void handleRequest(String request) {
             if (request.equals("RoomService")) {
                 System.out.println("Handling room service request.");
             } else if (nextHandler != null) {
                 nextHandler.handleRequest(request);
             }
         }
     }

     public class LaundryServiceHandler extends ServiceHandler {
         @Override
         public void handleRequest(String request) {
             if (request.equals("LaundryService")) {
                 System.out.println("Handling laundry service request.");
             } else if (nextHandler != null) {
                 nextHandler.handleRequest(request);
             }
         }
     }
     ```
7. **Facade Pattern**:
   - **Used In**: Simplifying Complex Subsystem Interactions
   - **Description**: Provides a simplified interface to a complex subsystem. The Facade pattern is used when you want to provide a simple interface to a complex system. It hides the complexities of the system and provides an easier way to access the underlying functionality.
   - **Code Example**:
     ```java
     // Facade class to provide a simplified interface for hotel operations
     public class HotelFacade {
         private RoomBookingService roomBookingService;
         private CustomerManagementService customerManagementService;
         private BillingService billingService;

         public HotelFacade() {
             roomBookingService = new RoomBookingService();
             customerManagementService = new CustomerManagementService();
             billingService = new BillingService();
         }

         public void bookRoom(String roomType, String customerName) {
             customerManagementService.addCustomer(customerName);
             roomBookingService.bookRoom(roomType, customerName);
             billingService.generateBill(customerName, roomType);
         }

         public void manageCustomer(String customerName, String action) {
             switch (action) {
                 case "add":
                     customerManagementService.addCustomer(customerName);
                     break;
                 case "update":
                     customerManagementService.updateCustomer(customerName);
                     break;
                 case "delete":
                     customerManagementService.deleteCustomer(customerName);
                     break;
                 default:
                     throw new IllegalArgumentException("Unknown action: " + action);
             }
         }

         public void generateBill(String customerName, String roomType) {
             billingService.generateBill(customerName, roomType);
         }
     }

     // Example usage
     public class Main {
         public static void main(String[] args) {
             HotelFacade hotelFacade = new HotelFacade();
             
             // Book a room
             hotelFacade.bookRoom("Deluxe", "John Doe");

             // Manage customer
             hotelFacade.manageCustomer("John Doe", "add");

             // Generate bill
             hotelFacade.generateBill("John Doe", "Deluxe");
         }
     }
     ```


## MVC Design Pattern

### Model-View-Controller (MVC) Design Pattern

**1. Model:**
The Model represents the data and the business logic of the application. It is responsible for managing the data, processing business logic, and notifying the View of any data changes. In your Hotel Management System, the Model interacts with the MongoDB database to perform CRUD (Create, Read, Update, Delete) operations.

**Example:**
```java
public class HotelModel {
    private String hotelName;
    private String location;
    private int numberOfRooms;

    // Getters and Setters for the data fields
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    // Method to save hotel data to MongoDB
    public void saveToDatabase() {
        // MongoDB logic to save data
    }

    // Method to retrieve hotel data from MongoDB
    public void loadFromDatabase() {
        // MongoDB logic to load data
    }
}
```

**2. View:**
The View is responsible for displaying the data to the user and capturing user input. It represents the user interface of the application. In your Hotel Management System, the View is implemented using Java Swing components to create the graphical user interface (GUI).

**Example:**
```java
import javax.swing.*;

public class HotelView {
    private JFrame frame;
    private JTextField hotelNameField;
    private JTextField locationField;
    private JTextField numberOfRoomsField;
    private JButton saveButton;

    public HotelView() {
        frame = new JFrame("Hotel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel hotelNameLabel = new JLabel("Hotel Name:");
        hotelNameLabel.setBounds(10, 20, 80, 25);
        panel.add(hotelNameLabel);

        hotelNameField = new JTextField(20);
        hotelNameField.setBounds(150, 20, 160, 25);
        panel.add(hotelNameField);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(10, 50, 80, 25);
        panel.add(locationLabel);

        locationField = new JTextField(20);
        locationField.setBounds(150, 50, 160, 25);
        panel.add(locationField);

        JLabel numberOfRoomsLabel = new JLabel("Number of Rooms:");
        numberOfRoomsLabel.setBounds(10, 80, 120, 25);
        panel.add(numberOfRoomsLabel);

        numberOfRoomsField = new JTextField(20);
        numberOfRoomsField.setBounds(150, 80, 160, 25);
        panel.add(numberOfRoomsField);

        saveButton = new JButton("Save");
        saveButton.setBounds(150, 110, 80, 25);
        panel.add(saveButton);
    }

    public String getHotelName() {
        return hotelNameField.getText();
    }

    public String getLocation() {
        return locationField.getText();
    }

    public int getNumberOfRooms() {
        return Integer.parseInt(numberOfRoomsField.getText());
    }

    public void addSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
```

**3. Controller:**
The Controller acts as an intermediary between the Model and the View. It handles user input, updating the Model, and refreshing the View. The Controller receives input from the View, processes it (e.g., validating data), and updates the Model accordingly.

**Example:**
```java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelController {
    private HotelModel model;
    private HotelView view;

    public HotelController(HotelModel model, HotelView view) {
        this.model = model;
        this.view = view;

        // Add action listener to the save button in the view
        this.view.addSaveButtonListener(new SaveButtonListener());
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setHotelName(view.getHotelName());
            model.setLocation(view.getLocation());
            model.setNumberOfRooms(view.getNumberOfRooms());

            model.saveToDatabase();
            view.showMessage("Hotel data saved successfully!");
        }
    }
}
```

### Putting It All Together

To initialize and run your Hotel Management System application, you would create instances of the Model, View, and Controller in a main class and link them together.

**Example:**
```java
public class Main {
    public static void main(String[] args) {
        HotelModel model = new HotelModel();
        HotelView view = new HotelView();
        HotelController controller = new HotelController(model, view);
    }
}
```

### Summary

In summary, the MVC design pattern in your Hotel Management System can be structured as follows:
- **Model:** Manages the data and business logic (interacts with MongoDB).
- **View:** Represents the user interface (implemented using Java Swing).
- **Controller:** Handles user input, updates the Model, and refreshes the View.

This separation of concerns makes the application more modular, easier to maintain, and scalable.

## Project Structure

- `src/`: Contains the source code for the application.
- `bin/`: Contains the compiled Java classes.
- `config/`: Contains configuration files for the application.
- `README.md`: Project documentation.

## Contributing

Contributions are welcome! Please fork this repository and submit pull requests with your changes. For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any queries or issues, please contact [praths71018](https://github.com/praths71018).
```
