
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student implements Serializable {

    private static final long serialVersionUID = 5L;
    private String studentID;
    private String name;
    private String email;
    private String password;
    private double marks;
    private String grade;
    public List<Subject> subjects = new ArrayList<Subject>();

    static Scanner in = new Scanner(System.in);
    static String fileName = "students.data";

    @Override
    public String toString() {
        return "Student [studentID=" + studentID + ", name=" + name + ", email=" + email + "]";
    }

    public static void StudentSys() throws IOException, ClassNotFoundException {

        String option = "";
        while (true) {
            System.out.print("\n\u001B[36mStudent System: [l/r/x]: \u001B[0 m");
            option = in.nextLine();
            switch (option) {
                case "l":
                    login();
                    break;
                case "r":                    
                    register();
                    break;
                case "x":
                    System.out.println("\u001B[33mThank You\u001B[0m");
                    return;

            }
        }

    }

    public static Student matchEmail(String email) throws IOException, ClassNotFoundException {

        List<Student> list = Database.read();

        for (Student s : list) {
            if (email.equals(s.getEmail())) {
                return s;
            }
        }
        return null;
    }
    
    public static boolean matchPassword(String pass) throws IOException, ClassNotFoundException {
        List<Student> list = Database.read();

        for (Student s : list) {
            if (pass.equals(s.getPassword())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean matchFormat(String email, String password) {

        Pattern emailPattern = Pattern.compile(Util.getEmailRegex());
        Matcher emailMatcher = emailPattern.matcher(email);
        Pattern passPattern = Pattern.compile(Util.getPasswordRegex());
        Matcher passMatcher = passPattern.matcher(password);

        if (emailMatcher.matches()&& passMatcher.matches() ) {
            System.out.println("\u001B[33memail and password formats acceptable\u001B[0m");
            return true;
        } else {
            System.out.println("\u001B[31mincorrect email or password format\u001B[0m");
            return false;
        }
    }
    
    public static void register() throws IOException, ClassNotFoundException {

        System.out.println("\u001B[32mStudent Sign Up\u001B[0m");
        System.out.print("Email:");
        String E = in.nextLine();
        System.out.print("Password:");
        String P = in.nextLine();

        if (!matchFormat(E, P)) {
            return;
        }

        if (matchEmail(E) == null) {
            System.out.print("Name: ");
            String n = in.nextLine();

            Student s = new Student();
            s.setName(n);
            s.setEmail(E);
            s.setPassword(P);
            Database.write(s);
            System.out.println("\u001B[33mEnrolling student " + n + "\u001B[0m");
        } else {
            System.out.println("\u001B[31mStudent already exists \u001B[0m");
        }
    }

    public static void login() throws IOException, ClassNotFoundException {

        System.out.println("\u001B[32mStudent Sign In\u001B[0m");
        System.out.print("Email:");
        String e = in.nextLine();
        System.out.print("Password:");
        String p = in.nextLine();
       
        if (!matchFormat(e, p)) {
            return;
        }
        
        if (matchEmail(e) != null) {
            if (matchPassword(p)) {
                
                StudentController.courseMenu(matchEmail(e));
                return;
            }
            System.out.println("\u001B[31mIncorrect email or password\u001B[0m");
            return;
        } else {
            System.out.println("\u001B[31mStudent does not exist\u001B[0m");
        }

    }

    public Student() {
        /*
         * ID randomly generated 1 <= ID <= 999999, unique and formatted as 6-digits
         * IDs less than 6-digits width should be completed with zeroes from the left.
         */
        Random random = new Random();
        Integer randomNum = random.nextInt(0, 1000000);
        String s = String.format("%06d", randomNum);
        this.studentID = s;
    }
    
    
    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }
}
