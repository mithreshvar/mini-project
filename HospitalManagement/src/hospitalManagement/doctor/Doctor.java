/*
 * Doctor POJO -> Plain Old Java Object
 */
package hospitalManagement.doctor;

public class Doctor {
    String specialization, doctor_name, password;
    Integer doctor_id, schedule_id;

    public Doctor() {
    }

    public Doctor(String doctor_name, Integer doctor_id) {
        setDoctor_name(doctor_name);
        setDoctor_id(doctor_id);
    }

    public Doctor(Integer doctor_id, String doctor_name, String password, String specialization, Integer schedule_id) {
        setSpecialization(specialization);
        setDoctor_name(doctor_name);
        setDoctor_id(doctor_id);
        setSchedule_id(schedule_id);
        setPassword(password);
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Integer schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String listDoc() {
        return "[ doctor_id=" + doctor_id + ", doctor_name=" + doctor_name + "]";
    }

    @Override
    public String toString() {
        return "[ doctor_id=" + doctor_id + ", doctor_name=" + doctor_name + ",specialization=" + specialization + "]";
    }

    public String printDocDetails() {
        return "[ doctor_id = " + doctor_id + ", doctor_name = " + doctor_name + ", Specialization = " + specialization
                + " ]";
    }
}
