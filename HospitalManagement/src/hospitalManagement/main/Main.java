package hospitalManagement.main;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import hospitalManagement.doctor.Doctor;
import hospitalManagement.doctor.DoctorBO;
import hospitalManagement.helperFunctions.HelperLoopFunctions;
import hospitalManagement.helperFunctions.HelperPatientFunctions;
import hospitalManagement.hospitalAdmin.AdminBO;
import hospitalManagement.patient.PatientBO;
import hospitalManagement.user.UserBO;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        Integer choiceMain = 0;
        HelperLoopFunctions hlf = new HelperLoopFunctions();
        HelperPatientFunctions hpf = new HelperPatientFunctions();

        while (choiceMain != 4) {

            choiceMain = hlf.mainLoop();
            if (choiceMain == 1) {

                PatientBO currentUser = null;
                Integer patientChoice = 0;

                while (patientChoice != 3) {

                    patientChoice = hlf.patientLoop();
                    if (patientChoice == 1) { // Creating new User for Patient

                        System.out.println("Creating new user ...");
                        System.out.print("Enter name : ");
                        String name = (sc.nextLine()).trim();
                        System.out.print("Enter password : ");
                        String password = (sc.nextLine()).trim();
                        System.out.print("Enter address : ");
                        String address = (sc.nextLine()).trim();
                        System.out.print("Enter gender : ");
                        String gender = (sc.nextLine()).trim();
                        System.out.print("Enter age :");
                        Integer age = sc.nextInt();
                        Boolean userCreated = new UserBO().createPatient(name, password, address, age, gender);

                        if (userCreated == false) {
                            System.out.println(
                                    "\nAccount Not Created !\nUser with name " + name + " already exists...\n");
                        } else {
                            System.out.println("\nAccount Created !\nProceed to Login ...\n");
                        }

                    } else if (patientChoice == 2) { // Login for Patient

                        System.out.println("Logging in ...");
                        System.out.print("Enter name : ");
                        String name = (sc.nextLine()).trim();
                        System.out.print("Enter password : ");
                        String password = (sc.nextLine()).trim();
                        currentUser = new UserBO().loginPatient(name, password);

                        if (currentUser == null) {
                            System.out.println("User not found !");
                        } else {

                            System.out.println("\nLogin Successful ...\n\nDoctor Schedule : ");
                            currentUser.getDoctorSchedule();

                            Integer loggedInChoice = 0;
                            while (loggedInChoice != 5) {

                                loggedInChoice = hlf.patientLoggedInLoop();

                                if (loggedInChoice == 1) { // Displaying Current Paitent User Details

                                    System.out.println("\nMy Details :");
                                    System.out.println(currentUser.getMyDetails());

                                } else if (loggedInChoice == 2) { // Displaying Doctor Schedule

                                    System.out.println("\nDoctor Schedule : ");
                                    currentUser.getDoctorSchedule();

                                } else if (loggedInChoice == 3) { // Fixing Appointment

                                    Map.Entry<LocalDateTime, List<Doctor>> p = hpf.getAppointmentDetails(currentUser);
                                    if (p == null)
                                        continue;

                                    List<Doctor> doc = p.getValue();

                                    System.out.println();
                                    if (!doc.isEmpty()) {

                                        System.out.println("Available Doctors :\n"); /*
                                                                                      * Displaying Specified Available
                                                                                      * Doctors
                                                                                      */
                                        for (Doctor d : doc) {
                                            System.out.println(d.listDoc());
                                        }
                                        System.out.println();

                                        System.out.print("Enter suitable Doctor's ID : ");
                                        Integer requestedDoctorId = sc.nextInt();

                                        if (hpf.isDocInList(doc, requestedDoctorId)) {

                                            Integer appointedID = currentUser.setAppointment(currentUser.getId(),
                                                    requestedDoctorId, p.getKey());
                                            System.out.println("\nYour Appointment Has Confirmed !\nAppointment ID is "
                                                    + appointedID + "\n");

                                        } else {
                                            System.out.println("\nWrong ID Selected !\n");
                                        }

                                    } else {
                                        System.out.println("No Doctors Available ...\n");
                                    }

                                } else if (loggedInChoice == 4) { // Viewing User Appointments
                                    currentUser.printMyAppointments();
                                }
                            }

                        }

                    }

                }

            } else if (choiceMain == 2) {

                AdminBO currentAdmin = null;
                System.out.println("Logging in ...");
                System.out.print("Enter Username : ");
                String name = (sc.nextLine()).trim();
                System.out.print("Enter password : ");
                String password = (sc.nextLine()).trim();
                currentAdmin = new UserBO().loginAdmin(name, password);

                if (currentAdmin == null) {
                    System.out.println("\nUser not found !\n");
                } else {

                    System.out.println("\nLogin Successful ...\n");
                    Integer loggedInChoice = 0;
                    while (loggedInChoice != 5) {

                        loggedInChoice = hlf.adminLoggedInLoop();
                        if (loggedInChoice == 1) { // create new doctor account

                            System.out.println("\nCreating new Doctor...\n");
                            System.out.print("Enter name : ");
                            String docname = (sc.nextLine()).trim();
                            System.out.print("Enter Doctor's specialization : ");
                            String specialization = (sc.nextLine()).trim();
                            System.out.println("\nAviailable Schedules :\n");
                            currentAdmin.printAllSchedule();
                            System.out.print("Enter Schedule ID : ");
                            Integer schedule_id = sc.nextInt();
                            currentAdmin.createNewDoctor(specialization, docname, docname, schedule_id);

                        } else if (loggedInChoice == 2) {// remove doctor account

                            System.out.println("Available Doctors :");
                            currentAdmin.printAllDoctors();
                            System.out.print("Enter the Doctor's ID to be removed : ");
                            Integer id = sc.nextInt();

                            System.out.println("\nRemoving Doctor...\n");

                            if (currentAdmin.removeDoctor(id)) {
                                System.out.println("\nDoctor with ID : " + id + " removed !\n");
                            } else {
                                System.out.println("\nEntered ID is wrong !\n");
                            }

                        } else if (loggedInChoice == 3) {// create new schedule for doc

                            System.out.println("\nCreating New Schedule !\n");
                            System.out.print("Enter day : ");
                            String day = (sc.nextLine()).trim();
                            System.out.print("Enter location :");
                            String location = (sc.nextLine()).trim();
                            System.out.print("Enter start time (hh:mm) : ");
                            String startTime = (sc.nextLine()).trim();
                            System.out.print("Enter End time (hh:mm) : ");
                            String endtime = (sc.nextLine()).trim();

                            if (currentAdmin.createNewSchedule(day, location, startTime, endtime)) {
                                System.out.println("\nNew Schedule Created !\n");
                            } else {
                                System.out.println("\nAn Error Occured ! New Schedule Not Created !\n");
                            }

                        } else if (loggedInChoice == 4) {// remove schedule for doc

                            System.out.println("\nAviailable Schedules :\n");
                            currentAdmin.printAllSchedule();

                            System.out.println("Enter Schedule ID: ");
                            Integer id = sc.nextInt();

                            if (currentAdmin.removeSchedule(id)) {
                                System.out.println("\nSchedule Removed !\n");
                            } else {
                                System.out.println("\nAn Error Occured ! New Schedule Not Created !\n");
                            }

                        }

                    }

                }

            } else if (choiceMain == 3) {

                DoctorBO currentDoctor = null;
                System.out.println("Logging in ...");
                System.out.print("Enter Username : ");
                String name = (sc.nextLine()).trim();
                System.out.print("Enter password : ");
                String password = (sc.nextLine()).trim();
                currentDoctor = new UserBO().loginDoctor(name, password);

                if (currentDoctor == null) {
                    System.out.println("\nUser not found !\n");
                } else {

                    System.out.println("\nLogin Successful ...\n");
                    Integer loggedInChoice = 0;
                    while (loggedInChoice != 2) {

                        loggedInChoice = hlf.doctorLoggedInLoop();
                        if (loggedInChoice == 1) { // printing current doctor's appoints

                            currentDoctor.printMyAppointments();

                        }

                    }

                }

            }
        }

        sc.close();
    }
}
