package hospitalManagement.helperFunctions;

import java.util.Scanner;

public class HelperLoopFunctions {

    Scanner sc = new Scanner(System.in);

    public Integer mainLoop() {

        Integer choice = 0;
        while (choice > 4 || choice < 1) {
            System.out.println("Enter choice :\n1) Patient\n2) Admin\n3) Doctor\n4) Exit");
            choice = sc.nextInt();
            if (choice < 5 && choice > 0)
                break;
            System.out.println("Invalid Input");
        }
        return choice;

    }

    public Integer patientLoop() {

        Integer choice = 0;
        while (choice > 3 || choice < 1) {
            System.out.println("Enter choice :\n1) Create new user\n2) Login\n3) Back");
            choice = sc.nextInt();
            if (choice < 4 && choice > 0)
                break;
            System.out.println("Invalid Input");
        }
        return choice;

    }

    public Integer patientLoggedInLoop() {

        Integer choice = 0;
        while (choice > 5 || choice < 1) {
            System.out.println(
                    "Enter choice :\n1) Show my details\n2) Show Doctor Schedule\n3) Fix Appointment\n4) View My Appointments\n5) Log out");
            choice = sc.nextInt();
            if (choice < 6 && choice > 0)
                break;
            System.out.println("Invalid Input");
        }
        return choice;

    }

    public Integer adminLoggedInLoop() {

        Integer choice = 0;
        while (choice > 5 || choice < 1) {
            System.out.println(
                    "Enter choice :\n1) Create New Doctor\n2) Remove Doctor\n3) Create New Schedule\n4) Remove Schedule\n5) Log out");
            choice = sc.nextInt();
            if (choice < 6 && choice > 0)
                break;
            System.out.println("Invalid Input");
        }
        return choice;

    }

    public Integer doctorLoggedInLoop() {

        Integer choice = 0;
        while (choice > 2 || choice < 1) {
            System.out.println(
                    "Enter choice :\n1) Get All My Appointment Schedules\n2) Log out");
            choice = sc.nextInt();
            if (choice < 3 && choice > 0)
                break;
            System.out.println("Invalid Input");
        }
        return choice;

    }

}
