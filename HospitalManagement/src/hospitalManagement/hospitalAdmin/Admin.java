/*
 * HospitalAdmin POJO -> Plain Old Java Object
 */
package hospitalManagement.hospitalAdmin;

public class Admin {
    Integer admin_id;

    String admin_name;
    String password;

    public Admin() {
    }

    public Admin(Integer admin_id, String admin_name, String password) {
        setAdmin_id(admin_id);
        setAdmin_name(admin_name);
        setPassword(password);
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
