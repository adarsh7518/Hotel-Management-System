import controller.HotelController;
import view.HotelView4;
//import DAO.*;

public class Main {
    public static void main(String[] args) {
        // Instantiate your controller
        HotelController controller = new HotelController();

        // Instantiate your view with the controller
        HotelView4 view = new HotelView4(controller);

        // Set the controller for the view
        view.setController(controller);

        // Display the view
        view.setVisible(true);
    }
}
