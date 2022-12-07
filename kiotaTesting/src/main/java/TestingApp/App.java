package TestingApp;

import java.util.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Java graph test proj");
        System.out.println();

        //Load Oauth
        final Properties oAuthProperties = new Properties();
        try {
            oAuthProperties.load(App.class.getResourceAsStream("oAuth.properties"));
        } catch (IOException ex) {
            System.out.println("Unable to read OAuth configuration. Make sure you have a properly formatted oAuth.properties file. See README for details.");
            return;
        }

        final String appId = oAuthProperties.getProperty("app.id");
        final List<String> appScopes = Arrays
                .asList(oAuthProperties.getProperty("app.scopes").split(","));
        String[] scopes = appScopes.toArray(new String[appScopes.size()]);


        GraphSignIn.initializeGraphAuth(appId, scopes);
        GraphSignIn.getUser();

        System.out.println();


        Scanner input = new Scanner(System.in);

        int choice = -1;

        while(choice!=0) {
            System.out.println("Please choose one of the following options:");
            System.out.println("0. Exit");
            System.out.println("1. Get User ID");
            System.out.println("2. Get Number of Users");
            System.out.println("3. Custom Call");

            try {
                choice = input.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println(ex.getStackTrace());
            }

            input.nextLine();

            switch (choice) {
                case 0:
                    // Exit the program
                    System.out.println("Goodbye...");
                    break;
                case 1:
                    // Display User ID
                    GraphSignIn.getUser();
                    break;
                case 2:
                    // Display Number of Users
                    GraphSignIn.getUsers();
                    break;
                case 3:
                    // Implement Your own
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        input.close();
    }
}
