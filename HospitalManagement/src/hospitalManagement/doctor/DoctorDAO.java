package hospitalManagement.doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import hospitalManagement.connection.MyConnection;

public class DoctorDAO {

    public DoctorBO login(String name, String password) {
        Connection con = MyConnection.getConnection();
        DoctorBO user = null;
        try {

            PreparedStatement pst = con.prepareStatement("select * from doctor where doctor_name = ? and password = ?");
            pst.setString(1, name);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            if (rs.next() != false) {
                Integer id = rs.getInt("doctor_id");
                String specialization = rs.getString("specialization");
                Integer schedule_id = rs.getInt("schedule_id");
                user = new DoctorBO(new Doctor(id, name, password, specialization, schedule_id));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void printMyAppointments(Integer doctor_id) {

        Connection con = MyConnection.getConnection();
        try {

            String query = "select a.appointment_record_id, a.date, p.patient_id, p.patient_name, p.address, p.age, p.gender from appointment_record as a join patient as p on a.patient_id = p.patient_id where a.doctor_id = "
                    + doctor_id;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                Integer appointment_record_id = rs.getInt(1);
                String date = rs.getString(2);
                Integer patient_id = rs.getInt(3);
                String patient_name = rs.getString(4);
                String address = rs.getString(5);
                Integer age = rs.getInt(6);
                String gender = rs.getString(7);
                System.out.println("[ appointment_record_id = " + appointment_record_id + " , date = " + date
                        + " , patient_id = " + patient_id + " , patient_name = " + patient_name + " , address = "
                        + address + " , age = " + age + " , gender = " + gender + " ]");

            }
            System.out.println();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
