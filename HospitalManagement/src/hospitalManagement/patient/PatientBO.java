package hospitalManagement.patient;

import java.time.LocalDateTime;
import java.util.List;

import hospitalManagement.doctor.Doctor;
import hospitalManagement.schedule.ScheduleDAO;

public class PatientBO {

    private Patient currentUser;

    PatientBO(Patient user) {
        currentUser = user;
    }

    public Integer getId() {
        return currentUser.getPatient_id();
    }

    public String getMyDetails() {
        return currentUser.toString();
    }

    public void getDoctorSchedule() {
        ScheduleDAO.printDoctorSchedule();
    }

    public List<Doctor> getDoctors(String specialization, LocalDateTime date_time, String location) {
        return new PatientDAO().getDoctors(specialization, date_time, location);
    }

    public Integer setAppointment(Integer patient_id, Integer doctor_id, LocalDateTime time) {
        return new PatientDAO().setAppointment(patient_id, doctor_id, time);
    }

    public Boolean printAllSpecializations() {
        return new PatientDAO().printAllSpecializations();
    }

    public Boolean printAllLocation(String specialization) {
        return new PatientDAO().printAllLocation(specialization);
    }

    public void printMyAppointments() {
        new PatientDAO().printMyAppointments(currentUser.getPatient_id());
    }
}
