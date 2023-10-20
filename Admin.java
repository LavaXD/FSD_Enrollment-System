
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Admin implements Serializable {

    private static final long serialVersionUID = 4L;
    private String name;
    private String adminID;
    private String password;

    static Scanner in = new Scanner(System.in);

    public static void AdminSys() throws IOException, ClassNotFoundException {

        String option = "";
        while (true) {
            System.out.print("\n\u001B[36mAdmin System: [c/g/p/r/s/x]: \u001B[0m");

            option = in.nextLine();
            switch (option) {
                case "c":
                    clearStudentsData();
                    break;
                case "g":
                    organizeByGrade();
                    break;
                case "p":
                    partition();
                    break;
                case "r":
                    removeStudent();
                    break;
                case "s":
                    viewAllStudent();
                    break;
                case "x":
                    System.out.println("\u001B[33mThank You\u001B[0m");
                    return;
            }
        }
    }

    public static void viewAllStudent() {
        System.out.println("\u001B[33mStudent List\u001B[0m");
        List<Student> students = Database.read();
        if(students.size()==0){
            System.out.println("       < Nothing to Display >");
        }
        for (Student s : students) {
            System.out.println(s.getName() + " :: " + s.getStudentID() + " --> Email: " + s.getEmail() + " -pass "
                    + s.getPassword());
        }

    }

      
    public static void partition() throws ClassNotFoundException, IOException {
        System.out.println("\u001B[33mPASS/FAIL Partition\u001B[0m");
        List<Student> students = Database.read();

        String result = "";
        System.out.print("FAIL --> [");
        for (Student student : students) {

            if (student.getGrade().equals("F")) {
                
                result += student.getName() + " :: " + student.getStudentID() + "  --> Grade: "
                        + student.getGrade() + " - MARK: " + student.getMarks() + ",";
            }
        }
        if (result.endsWith(",")) {
            result = result.substring(0, result.length() - 1);
        }
        System.out.print(result);
        System.out.println("]");

        String res = "";
        System.out.print("PASS --> [");
        for (Student student : students) {

            if (!student.getGrade().equals("F")) {
                res += student.getName() + " :: " + student.getStudentID() + "  --> Grade: "
                        + student.getGrade() + " - MARK: " + student.getMarks() + ",";
            }
        }
        if (res.endsWith(",")) {
            res = res.substring(0, res.length() - 1);
        }
        System.out.print(res);
        System.out.println("]");
    }

    //format problem
    public static void organizeByGrade() throws ClassNotFoundException, IOException {
        System.out.println("\u001B[33mGrade Grouping\u001B[0m");
        List<Student> students = Database.read();

        if (students.size() == 0) {
            System.out.println("       < Nothing to Display >");
            return;
        }
        
        for (Student student : students) {
            if (student.getGrade().equals("HD")) {
                System.out.println("HD --> [" + student.getName() + " :: " + student.getStudentID()
                        + "  --> Grade: HD - MARK: " + student.getMarks() + "]");
            }
        }
        
        System.out.println("\n");

        for (Student student : students) {
            if (student.getGrade().equalsIgnoreCase("D")) {
                System.out.println("D --> [" + student.getName() + " :: " + student.getStudentID()
                        + "  --> Grade: D - MARK: " + student.getMarks() + "]");
            }
        }

        System.out.println("\n");

        for (Student student : students) {
            if (student.getGrade().equals("P")) {
                System.out.println("P --> [" + student.getName() + " :: " + student.getStudentID()
                        + "  --> Grade: P - MARK: " + student.getMarks() + "]");
            }
        }

        System.out.println("\n");

        for (Student student : students) {
            if (student.getGrade().equals("F")) {
                System.out.println("F --> [" + student.getName() + " :: " + student.getStudentID()
                        + "  --> Grade: F - MARK: " + student.getMarks() + "]");
            }
        }
    }

    public static void removeStudent() throws ClassNotFoundException, IOException {
        List<Student> students = Database.read();
        if (students.size() == 0) {
            System.out.println("      No Students registered");
            return;
        }
        
        System.out.println("Remove by ID: ");
        String id = in.nextLine();
       
        for (Student student : students) {
            if (student.getStudentID().equals(id)) {
                Database.delete(id);
                System.out.println("\u001B[33mRemoving Student " + id + " Account\u001B[0m");
                return;
            }

        }
        System.out.println("\u001B[31mStudent " + id + " does not exist\u001B[0m");

    }

    public static void clearStudentsData() throws IOException {
        System.out.println("\u001B[33mClearing students database\u001B[0m");
        System.out.print("\u001B[31mAre you sure you want to clear the database: [Y]ES / [N]O: \u001B[0m");
        String option = in.nextLine();
        if (option.equals("y")) {
            Database.clear();
            System.out.println("\u001B[33mStudents data cleared\u001B[0m");
        } else {
            return;
        }
    }

    public Admin(String adminID, String password) {
        this.adminID = adminID;
        this.password = password;
    }

}
