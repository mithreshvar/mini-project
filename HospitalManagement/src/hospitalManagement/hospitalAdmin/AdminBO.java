package hospitalManagement.hospitalAdmin;

import hospitalManagement.schedule.ScheduleDAO;

public class AdminBO {

    Admin currentAdmin;

    AdminBO(Admin currentAdmin) {
        this.currentAdmin = currentAdmin;
    }

    public void printAllDoctors() {
        AdminDAO.printAllDoctors();
    }

    public Boolean removeDoctor(Integer id) {
        return new AdminDAO().removeDoctor(id);
    }

    public void printAllSchedule() {
        ScheduleDAO.printDoctorSchedule();
    }

    public Boolean createNewDoctor(String specialization, String doctor_name, String password, Integer schedule_id) {
        return new AdminDAO().createNewDoctor(specialization, doctor_name, password, schedule_id);
    }

    public Boolean createNewSchedule(String day, String location, String start_time, String end_time) {
        return new AdminDAO().createNewSchedule(day, location, start_time, end_time);
    }

    public Boolean removeSchedule(Integer id) {
        return new AdminDAO().removeSchedule(id);
    }

}
