package spa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static spa.Spa.employeerarraylist;

public class Spa {

    public static String cusssn;
    static ArrayList<Manager> managerarraylist = new ArrayList<Manager>();
    static ArrayList<Account> accountarraylist = new ArrayList<Account>();
    static ArrayList<Employee> employeerarraylist = new ArrayList<Employee>();
    static ArrayList<Customer> customerarraylist = new ArrayList<Customer>();
    static ArrayList<Services> servicearraylist = new ArrayList<Services>();
    static ArrayList<Equepment> equipmentarraylist = new ArrayList<Equepment>();
    static ArrayList<Schedual> schedualarraylist = new ArrayList<Schedual>();
    static PreparedStatement prestatment;
    static ResultSetMetaData resultSetMD;
    static final String user = "spa";
    static final String URL = "jdbc:derby://localhost:1527/spaSystem1 ";
    JTable table;
    static String qq;
    public static PreparedStatement add_employee_query;
    public static PreparedStatement add_equip_query;
    public static PreparedStatement update_employee_query;
    public static PreparedStatement remove_employee_query;
    public static PreparedStatement add_customer_query;
    public static PreparedStatement update_Customer_query;
    public static PreparedStatement delete_Service_query;
     public static Random rand = new Random();
     public static int Service_counter = rand.nextInt(31) + 5;
     public static Scanner input;

    public static void main(String[] args) throws SQLException {
//        EmployeeConnection();

        homePage x = new homePage();
        x.setVisible(true);
           

    }

    public static void file() {
FileReader xx;
BufferedReader yy;
        try {
            xx = new FileReader("info.txt");
            yy=new BufferedReader(xx);
            String text="";
            String line=yy.readLine();
            while(line!=null){
                text+=line;
                line=yy.readLine();
            }
            JOptionPane.showMessageDialog(null, text);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, " error openning file .. termnating .. ");
            System.exit(1);
        }
    }

    static boolean CheckExistingEmp(String var) throws SQLException {

        try (JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
            rowSet.setUrl(URL);
            rowSet.setUsername(user);
            rowSet.setPassword(user);
            rowSet.setCommand("select * from ACCOUNT");
            rowSet.execute();
            resultSetMD = rowSet.getMetaData();
            int noc = resultSetMD.getColumnCount();
            while (rowSet.next()) {
                for (int i = 1; i <= noc; i++) {
                    if (rowSet.getObject(i).equals(var)) {
                        return true;
                    }

                }

            }
            return false;
        }
    }
    

    public static void removeEmployee(String ssn) {

        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/spaSystem1 ", "spa", "spa")) {
            remove_employee_query = con.prepareStatement("DELETE FROM EMPLOYEE where SSN = ? ");

            remove_employee_query.setString(1, ssn);
            remove_employee_query.executeUpdate();
            JOptionPane.showMessageDialog(null, " successfully deleted!  ");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sorry, not deleted!");
            ex.printStackTrace();

        }
    }

    public static void removeService(String S) {

        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/spaSystem1", "spa", "spa")) {
            delete_Service_query = con.prepareStatement("DELETE FROM SERVICES where snumber = ? ");

            delete_Service_query.setString(1, S);
            delete_Service_query.executeUpdate();
            JOptionPane.showMessageDialog(null, " successfully deleted!  ");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();

        }
    }

    static boolean CheckExisting(String var) throws SQLException {

        try (JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
            rowSet.setUrl(URL);
            rowSet.setUsername(user);
            rowSet.setPassword(user);
            rowSet.setCommand("select * from ACCOUNT");
            rowSet.execute();
            resultSetMD = rowSet.getMetaData();
            int noc = resultSetMD.getColumnCount();
            while (rowSet.next()) {
                for (int i = 1; i <= noc; i++) {
                    if (rowSet.getObject(i).equals(var)) {
                        return true;
                    }

                }

            }
            return false;
        }
    }

    static boolean CheckExisting2(String var) throws SQLException {
        try (JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
            rowSet.setUrl(URL);
            rowSet.setUsername(user);
            rowSet.setPassword(user);
            rowSet.setCommand("select * from ACCOUNT");
            rowSet.execute();
            resultSetMD = rowSet.getMetaData();
            int noc = resultSetMD.getColumnCount();
            while (rowSet.next()) {
                for (int i = 1; i <= noc; i++) {
                    if (rowSet.getObject(i).equals(var)) {
                        return true;
                    }
                }

            }
            return false;
        }
    }

    public static void add_SERVS(Services x) {
//        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/spaSystem1", "spa", "spa")) {
//            add_equip_query = con.prepareStatement("insert into Services (snumber, "
//                    + "sdate, froms, tos, s_type, price , tools , cssn , s_activity )VALUES (?, ?,?,?,?,?,?,?,?) ");
////
//            add_equip_query.setInt(1, x.getService_Number());
//            add_equip_query.setString(2, x.getSchedual_Of_Service().getSchedual_date());
//            add_equip_query.setString(3, x.getSchedual_Of_Service().getSchedual_from());
//            add_equip_query.setString(4, x.getSchedual_Of_Service().getSchedual_to());
//            add_equip_query.setString(5, x.getService_type());
//            add_equip_query.setDouble(6, x.getService_Price());
//            add_equip_query.setString(7, x.getService_equepmentAndTools().getEquipmentName());
//            add_equip_query.setString(8, cusssn);
//            add_equip_query.setString(9, x.getService_Activity());
//
//            add_equip_query.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Welcome dear, your information has been added!  ");
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Sorry, not added!");
//            ex.printStackTrace();
//
//        }
//
//    }

 try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/spaSystem1", "spa", "spa")) {
            add_equip_query = con.prepareStatement("insert into Services (snumber, "
                    + "sdate, froms, tos, s_type, price , tools , cssn , s_activity )VALUES (?, ?,?,?,?,?,?,?,?) ");
            add_equip_query.setInt(1, x.getService_Number());
            add_equip_query.setString(2, x.getSchedual_Of_Service().getSchedual_date());
            add_equip_query.setString(3, x.getSchedual_Of_Service().getSchedual_from());
            add_equip_query.setString(4, x.getSchedual_Of_Service().getSchedual_to());
            add_equip_query.setString(5, x.getService_type());
            add_equip_query.setDouble(6, x.getService_Price());
            add_equip_query.setString(7, x.getService_equepmentAndTools().getEquipmentName());
            add_equip_query.setString(8, x.getSsn());
            add_equip_query.setString(9, x.getService_Activity());

            add_equip_query.executeUpdate();
            JOptionPane.showMessageDialog(null, "Welcome dear, your information has been added!  ");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();

        }

 
 
    }

    static void EmployeeConnection(String query) throws SQLException {
        try (JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
            rowSet.setUrl(URL);
            rowSet.setUsername(user);
            rowSet.setPassword(user);
            rowSet.setCommand(query);
            rowSet.execute();
            resultSetMD = rowSet.getMetaData();
            int noc = resultSetMD.getColumnCount();
            for (int i = 1; i <= noc; i++) {

                System.out.printf("%s", resultSetMD.getColumnName(i) + "\t");
            }
            System.out.println("");
            while (rowSet.next()) {
                for (int i = 1; i <= noc; i++) {
                    System.out.printf("%s\t", rowSet.getObject(i));
                }
                System.out.println();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sorry, Not match!");

        }

    }

    public static void addCustomer(Customer cus) {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/spaSystem1", "spa", "spa")) {
            add_customer_query = con.prepareStatement("insert into CUSTOMER (FULLNAME, "
                    + "SSN, BD ,EMAIL, GENDER, PHONENUMBER)VALUES (?, ?, ?, ?, ?, ?) ");

            add_customer_query.setString(1, cus.getCustomer_FName());
            add_customer_query.setString(2, cus.getCustomer_SSN());
            add_customer_query.setString(3, cus.getCustomer_BDate());
            add_customer_query.setString(4, cus.getCustomer_email());
            add_customer_query.setString(5, cus.getCustomer_Gender());
            add_customer_query.setString(6, cus.getCustomer_phone());

            add_customer_query.executeUpdate();
            JOptionPane.showMessageDialog(null, " added!  ");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Sorry, not added!");
            ex.printStackTrace();
        }

    }

    public static void updateCustomer(Customer cus) {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/spaSystem1 ", "spa", "spa")) {
            update_Customer_query = con.prepareStatement("update Customer set FULLNAME= ? "
                    + ", BD = ?, EMAIL = ?, GENDER = ?, PHONENUMBER = ? where SSN = ? ");

            update_Customer_query.setString(1, cus.getCustomer_FName());
            update_Customer_query.setString(2, cus.getCustomer_BDate());
            update_Customer_query.setString(3, cus.getCustomer_email());
            update_Customer_query.setString(4, cus.getCustomer_Gender());
            update_Customer_query.setString(5, cus.getCustomer_phone());
            update_Customer_query.setString(6, cus.getCustomer_SSN());
            update_Customer_query.executeUpdate();
            JOptionPane.showMessageDialog(null, " successfully updated!  ");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
           

        }
    }

    public static void addEmployee(Employee emp) {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/spaSystem1", "spa", "spa")) {
            add_employee_query = con.prepareStatement("insert into EMPLOYEE (FULL_NAME, "
                    + "SSN, SALARY, BDATE, GENDER, SUPER_SSN)VALUES (?, ?, ?, ?, ?, ?) ");

            add_employee_query.setString(1, emp.getEmployee_FullName());
            add_employee_query.setString(2, emp.getEmployee_SSN());
            add_employee_query.setDouble(3, emp.getEmployee_Salary());
            add_employee_query.setString(4, emp.getEmployee_BDate());
            add_employee_query.setString(5, emp.getEmployee_Gender());
            add_employee_query.setString(6, emp.getManager_SSN());

            add_employee_query.executeUpdate();
            JOptionPane.showMessageDialog(null, "Welcome dear, your information has been added!  ");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sorry, not added!");
            ex.printStackTrace();

        }
    }

    public static void addEquipment(Equepment eq) {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/spaSystem1 ", "spa", "spa")) {
            add_equip_query = con.prepareStatement("insert into EQUIPMENT_AND_TOOLS (TOOL_ID, TOOL_NAME) VALUES (?,?) ");

            add_equip_query.setString(1, eq.getEquipmentID());
            add_equip_query.setString(2, eq.getEquipmentName());

            add_equip_query.executeUpdate();
            JOptionPane.showMessageDialog(null, " added!  ");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sorry, not added!");
            ex.printStackTrace();

        }
    }

    public static void updateEmployee(Employee emp) {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/spaSystem1 ", "spa", "spa")) {
            update_employee_query = con.prepareStatement("update EMPLOYEE set FULL_NAME= ? "
                    + ",SALARY = ?, BDATE = ?, GENDER = ?, SUPER_SSN = ? where SSN = ? ");

            update_employee_query.setString(1, emp.getEmployee_FullName());
            update_employee_query.setDouble(2, emp.getEmployee_Salary());
            update_employee_query.setString(3, emp.getEmployee_BDate());
            update_employee_query.setString(4, emp.getEmployee_Gender());
            update_employee_query.setString(5, emp.getManager_SSN());
            update_employee_query.setString(6, emp.getEmployee_SSN());
            update_employee_query.executeUpdate();
            JOptionPane.showMessageDialog(null, " successfully updated!  ");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sorry, not updated!");
            ex.printStackTrace();

        }
    }
}

class Customer {

    String Customer_FName;
    String Customer_LName;
    String Customer_SSN;
    String Customer_BDate;
    String Customer_Gender;
    String Customer_phone;
    String Customer_email;
    boolean haveDiscount;
    int Discount_Value;

    public Customer(String Customer_FName, String Customer_SSN, String Customer_BDate, String Customer_Gender, String Customer_phone, String Customer_email, boolean haveDiscount, int Discount_Value) {
        this.Customer_FName = Customer_FName;
        this.Customer_SSN = Customer_SSN;
        this.Customer_BDate = Customer_BDate;
        this.Customer_Gender = Customer_Gender;
        this.Customer_phone = Customer_phone;
        this.Customer_email = Customer_email;
        this.haveDiscount = haveDiscount;
        this.Discount_Value = Discount_Value;
    }

    static boolean loginCustomerN(String var) throws SQLException {
        try (JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
            rowSet.setUrl("jdbc:derby://localhost:1527/spa");
            rowSet.setUsername("");
            rowSet.setPassword("");
            rowSet.setCommand("select FNAME from CUSTOMER");
            rowSet.execute();
            ResultSetMetaData resultSetMD = rowSet.getMetaData();
            int noc = resultSetMD.getColumnCount();
            while (rowSet.next()) {
                for (int i = 1; i <= noc; i++) {
                    if (rowSet.getObject(i).equals(var)) {
                        return true;
                    }
                }

            }
            return false;
        }
    }

    static boolean loginCustomerSSN(String var) throws SQLException {
        try (JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
            rowSet.setUrl("jdbc:derby://localhost:1527/spa");
            rowSet.setUsername("");
            rowSet.setPassword("");
            rowSet.setCommand("select SSN from CUSTOMER");
            rowSet.execute();
            ResultSetMetaData resultSetMD = rowSet.getMetaData();
            int noc = resultSetMD.getColumnCount();
            while (rowSet.next()) {
                for (int i = 1; i <= noc; i++) {
                    if (rowSet.getObject(i).equals(var)) {
                        return true;
                    }
                }

            }
            return false;
        }
    }

    public String getCustomer_FName() {
        return Customer_FName;
    }

    public String getCustomer_LName() {
        return Customer_LName;
    }

    public String getCustomer_SSN() {
        return Customer_SSN;
    }

    public String getCustomer_BDate() {
        return Customer_BDate;
    }

    public String getCustomer_Gender() {
        return Customer_Gender;
    }

    public String getCustomer_phone() {
        return Customer_phone;
    }

    public String getCustomer_email() {
        return Customer_email;
    }

    public boolean isHaveDiscount() {
        return haveDiscount;
    }

    public int getDiscount_Value() {
        return Discount_Value;
    }

    public void setCustomer_FName(String Customer_FName) {
        this.Customer_FName = Customer_FName;
    }

    public void setCustomer_LName(String Customer_LName) {
        this.Customer_LName = Customer_LName;
    }

    public void setCustomer_SSN(String Customer_SSN) {
        this.Customer_SSN = Customer_SSN;
    }

    public void setCustomer_BDate(String Customer_BDate) {
        this.Customer_BDate = Customer_BDate;
    }

    public void setCustomer_Gender(String Customer_Gender) {
        this.Customer_Gender = Customer_Gender;
    }

    public void setCustomer_phone(String Customer_phone) {
        this.Customer_phone = Customer_phone;
    }

    public void setCustomer_email(String Customer_email) {
        this.Customer_email = Customer_email;
    }

    public void setHaveDiscount(boolean haveDiscount) {
        this.haveDiscount = haveDiscount;
    }

    public void setDiscount_Value(int Discount_Value) {
        this.Discount_Value = Discount_Value;
    }

}

class EquepRe {

    public String EquipmentName;
    boolean state;

    public EquepRe(String EquipmentName, boolean state) {
        this.EquipmentName = EquipmentName;
        this.state = state;
    }

    public String getEquipmentName() {
        return EquipmentName;
    }

    public void setEquipmentName(String EquipmentName) {
        this.EquipmentName = EquipmentName;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

}

class Equepment {

    public String EquipmentName;
    public String EquipmentID;
    boolean state;

    public Equepment(String EquipmentName, String EquipmentID, boolean state) {
        this.EquipmentName = EquipmentName;
        this.EquipmentID = EquipmentID;
        this.state = state;
    }

    public String getEquipmentName() {
        return EquipmentName;
    }

    public void setEquipmentName(String EquipmentName) {
        this.EquipmentName = EquipmentName;
    }

    public String getEquipmentID() {
        return EquipmentID;
    }

    public void setEquipmentID(String EquipmentID) {
        this.EquipmentID = EquipmentID;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }


}

class Schedual {

    public String Schedual_date;
    public String Schedual_from;
    public String Schedual_to;

    public Schedual(String Schedual_date, String Schedual_from, String Schedual_to) {
        this.Schedual_date = Schedual_date;
        this.Schedual_from = Schedual_from;
        this.Schedual_to = Schedual_to;
    }

    public String getSchedual_date() {
        return Schedual_date;
    }

    public void setSchedual_date(String Schedual_date) {
        this.Schedual_date = Schedual_date;
    }

    public String getSchedual_from() {
        return Schedual_from;
    }

    public void setSchedual_from(String Schedual_from) {
        this.Schedual_from = Schedual_from;
    }

    public String getSchedual_to() {
        return Schedual_to;
    }

    public void setSchedual_to(String Schedual_to) {
        this.Schedual_to = Schedual_to;
    }

}

class Services {

    public int Service_Number;
    public String Service_Activity;
    public String Service_type;
    public double Service_Price;
    public Equepment Service_equepmentAndTools;
    public Schedual Schedual_Of_Service;
    String ssn;

    public Services(int Service_Number, String Service_Activity, String Service_type, double Service_Price, Equepment Service_equepmentAndTools, Schedual Schedual_Of_Service,String ssn) {
        this.Service_Number = Service_Number;
        this.Service_Activity = Service_Activity;
        this.Service_type = Service_type;
        this.Service_Price = Service_Price;
        this.Service_equepmentAndTools = Service_equepmentAndTools;
        this.Schedual_Of_Service = Schedual_Of_Service;
        this.ssn=ssn;
    }

    public String getService_type() {
        return Service_type;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setService_type(String Service_type) {
        this.Service_type = Service_type;
    }

    public int getService_Number() {
        return Service_Number;
    }

    public void setService_Number(int Service_Number) {
        this.Service_Number = Service_Number;
    }

    public String getService_Activity() {
        return Service_Activity;
    }

    public void setService_Activity(String Service_Activity) {
        this.Service_Activity = Service_Activity;
    }

    public double getService_Price() {
        return Service_Price;
    }

    public void setService_Price(double Service_Price) {
        this.Service_Price = Service_Price;
    }

    public Equepment getService_equepmentAndTools() {
        return Service_equepmentAndTools;
    }

    public void setService_equepmentAndTools(Equepment Service_equepmentAndTools) {
        this.Service_equepmentAndTools = Service_equepmentAndTools;
    }

    public Schedual getSchedual_Of_Service() {
        return Schedual_Of_Service;
    }

    public void setSchedual_Of_Service(Schedual Schedual_Of_Service) {
        this.Schedual_Of_Service = Schedual_Of_Service;
    }

}

class DataEntryException extends Exception {

    public DataEntryException() {
        super(" sorry, You entered invalid information. ");
    }
}

class Employee {

    public String Employee_FullName;
    public String Employee_SSN;
    public String Manager_SSN;
    public String Employee_BDate;
    public double Employee_Salary;
    public String Employee_Gender;

    public Employee(String Employee_FullName, String Employee_SSN,
            String Manager_SSN, String Employee_BDate, double Employee_Salary, String Employee_Gender) {
        this.Employee_FullName = Employee_FullName;

        this.Employee_SSN = Employee_SSN;
        this.Manager_SSN = Manager_SSN;
        this.Employee_BDate = Employee_BDate;
        this.Employee_Salary = Employee_Salary;
        this.Employee_Gender = Employee_Gender;
    }

    public String getEmployee_FullName() {
        return Employee_FullName;
    }

    public void setEmployee_FullName(String Employee_FName) {
        this.Employee_FullName = Employee_FullName;
    }

    public String getEmployee_SSN() {
        return Employee_SSN;
    }

    public void setEmployee_SSN(String Employee_SSN) {
        this.Employee_SSN = Employee_SSN;
    }

    public String getManager_SSN() {
        return Manager_SSN;
    }

    public void setManager_SSN(String Manager_SSN) {
        this.Manager_SSN = Manager_SSN;
    }

    public String getEmployee_BDate() {
        return Employee_BDate;
    }

    public void setEmployee_BDate(String Employee_BDate) {
        this.Employee_BDate = Employee_BDate;
    }

    public double getEmployee_Salary() {
        return Employee_Salary;
    }

    public void setEmployee_Salary(double Employee_Salary) {
        this.Employee_Salary = Employee_Salary;
    }

    public String getEmployee_Gender() {
        return Employee_Gender;
    }

    public void setEmployee_Gender(String Employee_Gender) {
        this.Employee_Gender = Employee_Gender;
    }

}

class Manager {

    String Manager_FName;
    String Manager_LName;
    String Manager_SSN;
    String Manager_BDate;
    double Manager_Salary;
    String Manager_Gender;

    public Manager(String Manager_FName, String Manager_LName, String Manager_SSN, String Manager_BDate, double Manager_Salary, String Manager_Gender) {
        this.Manager_FName = Manager_FName;
        this.Manager_LName = Manager_LName;
        this.Manager_SSN = Manager_SSN;
        this.Manager_BDate = Manager_BDate;
        this.Manager_Salary = Manager_Salary;
        this.Manager_Gender = Manager_Gender;
    }

    public String getManager_FName() {
        return Manager_FName;
    }

    public String getManager_LName() {
        return Manager_LName;
    }

    public String getManager_SSN() {
        return Manager_SSN;
    }

    public String getManager_BDate() {
        return Manager_BDate;
    }

    public double getManager_Salary() {
        return Manager_Salary;
    }

    public String getManager_Gender() {
        return Manager_Gender;
    }

    public void setManager_FName(String Manager_FName) {
        this.Manager_FName = Manager_FName;
    }

    public void setManager_LName(String Manager_LName) {
        this.Manager_LName = Manager_LName;
    }

    public void setManager_SSN(String Manager_SSN) {
        this.Manager_SSN = Manager_SSN;
    }

    public void setManager_BDate(String Manager_BDate) {
        this.Manager_BDate = Manager_BDate;
    }

    public void setManager_Salary(double Manager_Salary) {
        this.Manager_Salary = Manager_Salary;
    }

    public void setManager_Gender(String Manager_Gender) {
        this.Manager_Gender = Manager_Gender;
    }

    public void addEmployee(Employee x) {
        employeerarraylist.add(x);
    }

    public void removeEmployee(Employee x) {
        for (int i = 0; i < employeerarraylist.size(); i++) {
            if (employeerarraylist.get(i).Employee_SSN == x.Employee_SSN) {
                employeerarraylist.remove(i);
            }
        }

    }

    public void updateEmployee() {

    }

}

class Account {

    String UserName;
    private String Password;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Account(String UserName, String Password) {
        this.UserName = UserName;
        this.Password = Password;
    }
}
class ER{
    String a,b;

    public ER(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
    
}
class TeamMember{
    
        String descirtion;
        int teamNumber;
        String teamMember;
        

    public TeamMember(String descirtion, int teamNumber, String teamMember) {
        this.descirtion = descirtion;
        this.teamNumber = teamNumber;
        this.teamMember = teamMember;
    }
        
    }