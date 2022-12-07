package hospitalManagement.schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;

import hospitalManagement.connection.MyConnection;

public class ScheduleDAO {

    public static void printDoctorSchedule() {

        Connection con = MyConnection.getConnection();
        try {
            Statement stm = con.createStatement();
            String query = "select * from schedule";
            ResultSet rs = stm.executeQuery(query);
            int id;
            String day, location;
            LocalTime end_time, start_time;
            while (rs.next()) {
                id = rs.getInt("schedule_id");
                day = rs.getString("day");
                start_time = (rs.getTime("start_time")).toLocalTime();
                end_time = (rs.getTime("end_time")).toLocalTime();
                location = rs.getString("location");
                Schedule s = new Schedule(id, day, start_time, end_time, location);
                System.out.println(s.toString());
            }
            System.out.println();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
