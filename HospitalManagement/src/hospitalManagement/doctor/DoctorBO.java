package hospitalManagement.doctor;

public class DoctorBO {

    private Doctor currentUser;

    DoctorBO(Doctor user) {
        currentUser = user;
    }

    public void printMyAppointments() {
        DoctorDAO.printMyAppointments(currentUser.getDoctor_id());
    }

}
