package hospitalManagement.hospitalAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import hospitalManagement.connection.MyConnection;
import hospitalManagement.doctor.Doctor;

public class AdminDAO {

    public AdminBO login(String username, String password) {

        Connection con = MyConnection.getConnection();
        AdminBO user = null;
        try {

            PreparedStatement pst = con.prepareStatement("select * from admin where admin_name = ? and password = ?");
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            if (rs.next() != false) {
                Integer id = rs.getInt("admin_id");
                user = new AdminBO(new Admin(id, username, password));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void printAllDoctors() {

        Connection con = MyConnection.getConnection();
        try {
            Statement stm = con.createStatement();
            String query = "select * from doctor";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {

                String specialization = rs.getString("specialization"), doctor_name = rs.getString("doctor_name");
                Integer doctor_id = rs.getInt("doctor_id");
                Doctor doc = new Doctor(doctor_id, doctor_name, null, specialization, -1);
                System.out.println(doc.printDocDetails());

            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Boolean createNewDoctor(String specialization, String doctor_name, String password,
            Integer schedule_id) {

        Boolean newDoctorCreated = false;
        Connection con = MyConnection.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement(
                    "insert into doctor (doctor_name, password, specialization, schedule_id) values(?,?,?,?)");
            pst.setString(1, doctor_name);
            pst.setString(2, password);
            pst.setString(3, specialization);
            pst.setInt(4, schedule_id);
            Integer rowsAffected = pst.executeUpdate();

            if (rowsAffected != 0) {
                newDoctorCreated = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDoctorCreated;
    }

    public Boolean removeDoctor(Integer id) {

        Connection con = MyConnection.getConnection();
        Boolean docRemoved = false;
        try {

            Statement stm = con.createStatement();
            String query = "delete from doctor where doctor_id = " + id;
            Integer rowsAffected = stm.executeUpdate(query);

            if (rowsAffected != 0) {
                docRemoved = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return docRemoved;
    }

    public Boolean createNewSchedule(String day, String location, String start_time_String, String end_time_String) {

        Boolean newScheduleCreated = false;
        Connection con = MyConnection.getConnection();

        LocalTime start_time, end_time;
        try {
            start_time = LocalTime.parse(start_time_String);
            end_time = LocalTime.parse(end_time_String);
        } catch (DateTimeParseException e) {
            System.out.println("\nEntered Time is in Worng format !");
            return false;
        }

        try {
            PreparedStatement pst = con.prepareStatement(
                    "insert into schedule (day, location, start_time, end_time) values(?,?,?,?)");
            pst.setString(1, day);
            pst.setString(2, location);
            pst.setString(3, start_time.toString());
            pst.setString(4, end_time.toString());
            Integer rowsAffected = pst.executeUpdate();

            if (rowsAffected != 0) {
                newScheduleCreated = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newScheduleCreated;
    }

    public Boolean removeSchedule(Integer id) {

        Connection con = MyConnection.getConnection();
        Boolean scheduleRemoved = false;
        try {

            Statement stm = con.createStatement();
            String query = "delete from schedule where schedule_id = " + id;
            Integer rowsAffected = stm.executeUpdate(query);

            if (rowsAffected != 0) {
                scheduleRemoved = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return scheduleRemoved;
    }

}
