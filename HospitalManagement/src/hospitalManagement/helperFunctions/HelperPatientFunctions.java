package hospitalManagement.helperFunctions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import hospitalManagement.doctor.Doctor;
import hospitalManagement.patient.PatientBO;

public class HelperPatientFunctions {

    Scanner sc = new Scanner(System.in);

    public Boolean isDocInList(List<Doctor> doc, Integer id) {
        for (Doctor d : doc) {
            if (d.getDoctor_id() == id)
                return true;
        }
        return false;
    }

    public Map.Entry<LocalDateTime, List<Doctor>> getAppointmentDetails(PatientBO currentUser) {
        System.out.println("\nFixing Appointment...\n");

        if (!currentUser.printAllSpecializations()) {
            return null;
        }

        System.out.print("Enter specialization needed : ");
        String specialization = sc.next();

        if (!currentUser.printAllLocation(specialization)) {
            return null;
        }

        System.out.print("Enter the location : ");
        String location = sc.next();

        System.out.print("Enter the date ( DD-MM-YYYY ) : ");
        String date = sc.next().trim();

        System.out.print("Enter the time ( HH:MM ) : ");
        String time = sc.next().trim();

        LocalDateTime date_time = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            date_time = LocalDateTime.parse(date + " " + time, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("\nWrong format of Date or Time!\n");
            return null;
        }
        return new AbstractMap.SimpleImmutableEntry<>(date_time,
                currentUser.getDoctors(specialization, date_time, location));
    }

}
