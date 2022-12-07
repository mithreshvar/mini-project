/*
 * Patient POJO -> Plain Old Java Object
 */
package hospitalManagement.patient;

public class Patient {

    private String patient_name, address, gender, password;
    private Integer age, patient_id;

    Patient() {
    }

    public Patient(String patient_name, String password, String address, String gender, Integer age,
            Integer patient_id) {
        setPatient_name(patient_name);
        setAddress(address);
        setGender(gender);
        setAge(age);
        setPatient_id(patient_id);
        setPassword(password);
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return (" Name : " + patient_name + " Address : " + address + " Gender : " + gender + " Age : " + age);
    }
}