package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;


public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car car1 = new Car("CarModel1", 101);
      Car car2 = new Car("CarModel2", 202);
      Car car3 = new Car("CarModel3", 303);
      Car car4 = new Car("CarModel4", 404);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println("Car series = "+user.getCar().getSeries());
         System.out.println();
      }

      System.out.println("Car1 -> " + userService.getUserByCar(car1.getModel(), car1.getSeries()).getFirstName());
      System.out.println("Car2 -> " + userService.getUserByCar(car2.getModel(), car2.getSeries()).getFirstName());
      System.out.println("Car3 -> " + userService.getUserByCar(car3.getModel(), car3.getSeries()).getFirstName());
      System.out.println("Car4 -> " + userService.getUserByCar(car4.getModel(), car4.getSeries()).getFirstName());

      context.close();
   }
}
