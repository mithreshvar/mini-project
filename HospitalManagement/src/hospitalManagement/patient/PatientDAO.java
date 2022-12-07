package hospitalManagement.patient;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import hospitalManagement.connection.MyConnection;
import hospitalManagement.doctor.Doctor;

public class PatientDAO {

    public PatientBO login(String name, String password) {

        Connection con = MyConnection.getConnection();
        PatientBO user = null;
        try {
            Statement stm = con.createStatement();
            String query = "select * from patient where patient_name = '" + name + "'";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                if (password.equals(rs.getString("password"))) {

                    Integer id = rs.getInt("patient_id"), age = rs.getInt("age");
                    String address = rs.getString("address"), gender = rs.getString("gender");

                    user = new PatientBO(new Patient(name, password, address, gender, age, id));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public Boolean createUser(String name, String password, String address, Integer age, String gender) {

        Connection con = MyConnection.getConnection();
        Boolean userCreated = false;

        try {
            Statement stm = con.createStatement();
            String query = "select * from patient where patient_name = '" + name + "'";
            ResultSet rs = stm.executeQuery(query);
            if (rs.next() == false) {

                PreparedStatement pst = con.prepareStatement(
                        "insert into patient (patient_name, password, address, age, gender) values(?,?,?,?,?)");
                pst.setString(1, name);
                pst.setString(2, password);
                pst.setString(3, address);
                pst.setInt(4, age);
                pst.setString(5, gender);
                Integer rowsAffected = pst.executeUpdate();

                if (rowsAffected != 0) {
                    userCreated = true;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userCreated;
    }

    public List<Doctor> getDoctors(String specialization, LocalDateTime date_time, String location) {

        Connection con = MyConnection.getConnection();
        List<Doctor> al = new ArrayList<Doctor>();
        try {
            String day = date_time.getDayOfWeek().toString();
            String time = date_time.getHour() + ":" + date_time.getMinute();
            PreparedStatement pst = con.prepareStatement(
                    "select doctor_id, doctor_name from doctor where specialization=? and schedule_id=(select schedule_id from schedule where location=? and day=? and start_time <= ? and end_time>=? )");
            pst.setString(1, specialization);
            pst.setString(2, location);
            pst.setString(3, day);
            pst.setString(4, time);
            pst.setString(5, time);

            Integer id;
            String name;
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                id = rs.getInt("doctor_id");
                name = rs.getString("doctor_name");
                Doctor d = new Doctor(name, id);
                al.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return al;

    }

    public Integer setAppointment(Integer patient_id, Integer doctor_id, LocalDateTime time) {

        Connection con = MyConnection.getConnection();
        Integer appointmentID = -1;

        try {

            PreparedStatement pst = con.prepareStatement(
                    "select * from appointment_record where patient_id = ? and doctor_id = ? and date = ? ");
            pst.setInt(1, patient_id);
            pst.setInt(2, doctor_id);
            pst.setString(3, time.toString());

            ResultSet rs = pst.executeQuery();
            if (rs.next() == false) {

                pst = con.prepareStatement(
                        "insert into appointment_record (patient_id, doctor_id, date) values(?,?,?)");
                pst.setInt(1, patient_id);
                pst.setInt(2, doctor_id);
                pst.setString(3, time.toString());
                Integer rowsAffected = pst.executeUpdate();

                if (rowsAffected != 0) {

                    pst = con.prepareStatement(
                            "select * from appointment_record where patient_id = ? and doctor_id = ? and date = ? ");
                    pst.setInt(1, patient_id);
                    pst.setInt(2, doctor_id);
                    pst.setString(3, time.toString());
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        appointmentID = rs.getInt("appointment_record_id");
                    }
                }

            } else {
                appointmentID = rs.getInt("appointment_record_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appointmentID;
    }

    public Boolean printAllSpecializations() {

        Connection con = MyConnection.getConnection();
        try {

            Statement stm = con.createStatement();
            String query = "select specialization from doctor group by specialization";
            ResultSet rs = stm.executeQuery(query);

            if (rs.next() == false) {
                System.out.println("\nNo Specialization Available Sorry !\n");
                return false;
            } else {

                System.out.println("Available Specialization Are :");
                do {
                    System.out.println(rs.getString("specialization"));
                } while (rs.next());
                System.out.println();
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public Boolean printAllLocation(String specialization) {

        Connection con = MyConnection.getConnection();
        try {

            Statement stm = con.createStatement();
            String query = "select location from schedule where schedule_id in (select schedule_id from doctor where specialization = '"
                    + specialization + "') group by location";
            ResultSet rs = stm.executeQuery(query);

            if (rs.next() == false) {
                System.out.println("\nSorry ! No Locations Available In This Specialization...\n");
                return false;
            } else {

                System.out.println("Available Locations Are :");
                do {
                    System.out.println(rs.getString("location"));
                } while (rs.next());
                System.out.println();
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void printMyAppointments(Integer patient_id) {

        Connection con = MyConnection.getConnection();
        try {

            Statement stm = con.createStatement();
            String query = "select a.appointment_record_id as id, a.date as date, d.doctor_name as doctor_name from appointment_record as a join doctor as d on a.doctor_id = d.doctor_id where patient_id = "
                    + patient_id;
            ResultSet rs = stm.executeQuery(query);

            if (rs.next() == false) {
                System.out.println("\nNo Appointment Records Available !\n");
            } else {

                System.out.println("Your Appointment Records :");
                do {
                    System.out.println("[ Id : " + rs.getString("id") + " , " + "Date : " + rs.getString("date") + " , "
                            + "Doctor_name : " + rs.getString("doctor_name") + " ]");
                } while (rs.next());
                System.out.println();
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
