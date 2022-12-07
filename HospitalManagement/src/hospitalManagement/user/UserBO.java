package hospitalManagement.user;

import hospitalManagement.doctor.DoctorBO;
import hospitalManagement.doctor.DoctorDAO;
import hospitalManagement.hospitalAdmin.AdminBO;
import hospitalManagement.hospitalAdmin.AdminDAO;
import hospitalManagement.patient.PatientBO;
import hospitalManagement.patient.PatientDAO;

public class UserBO {

    public PatientBO loginPatient(String name, String password) {
        return new PatientDAO().login(name, password);
    }

    public Boolean createPatient(String name, String password, String address, Integer age, String gender) {
        return new PatientDAO().createUser(name, password, address, age, gender);
    }

    public AdminBO loginAdmin(String username, String password) {
        return new AdminDAO().login(username, password);
    }

    public DoctorBO loginDoctor(String name, String password) {
        return new DoctorDAO().login(name, password);
    }
}
