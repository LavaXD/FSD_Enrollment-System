import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {

    public String studentID;
    public String name;
    public String email;
    public String password;
    public static List<Subject> subjects = new ArrayList<Subject>();

    static Scanner in = new Scanner(System.in);
    static String fileName = "students.data";

    @Override
    public String toString() {
        return "Student [studentID=" + studentID + ", name=" + name + ", email=" + email + "]";
    }

    public static void StudentSys() throws IOException {

        String option = "";
        while (true) {
            System.out.print("\n\u001B[36mStudent Course System: [l/r/x]: \u001B[0 m");
            option = in.nextLine();
            switch (option) {
                case "l":
                    System.out.println("\u001B[32mStudent Sign In\u001B[0m");
                    System.out.print("Email:");
                    String e = in.nextLine();
                    System.out.print("Password:");
                    String p = in.nextLine();
                    login(e, p);
                    break;
                case "r":
                    
                    System.out.println("\u001B[32mStudent Sign Up\u001B[0m");
                    System.out.print("Email:");
                    String E = in.nextLine();
                    System.out.print("Password:");
                    String P = in.nextLine();
                    register(E, P);
                    break;
                case "x":
                    System.out.println("\u001B[33mThank You\u001B[0m");
                    return;

            }
        }

    }
    
    public static boolean matchEmail(String email) throws IOException {
        Reader reader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        if ((line = bufferedReader.readLine()) == null) {
            return false;
        }

        while ((line = bufferedReader.readLine()) != null) {

            String ema = line.trim();

            if (ema.equals(email)) {
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
    
    public static void register(String email, String password) throws IOException {
        
        if (!matchFormat(email, password)) {
            return;
        }
        

        if (!matchEmail(email)) {
            System.out.print("Name: ");
            String name = in.nextLine();
            Database.write(email);
            Database.write(password);
            System.out.println("\u001B[33mEnrolling student "+name+"\u001B[0m");
        } else {
            System.out.println("\u001B[31mStudent already exists \u001B[0m");
        } 
    }

    public static void login(String email, String password) throws IOException {

        Reader reader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(reader);

        if(!matchFormat(email, password)){
            return;
        }
        if (matchEmail(email)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String pass = line.trim();

                if (pass.equals(password)) {
                    
                    courseMenu();
                    return;
                }
            }
            System.out.println("\u001B[31mIncorrect email or password format\u001B[0m");
            return;
        } else {
            System.out.println("\u001B[31mStudent does not exist\u001B[0m");
        }

    }

    public static void courseMenu() {
        String option = "";
        while(true){
            System.out.print("\n\u001B[36m   Student Course Menu: [c/e/r/s/x]: \u001B[0 m");
            option = in.nextLine();
            switch(option){
                case "x":
                    return;
            }
        }
    }
    
    public static void enroll(Subject subject) {

        subjects.add(subject);
        System.out.println(subject.subjectName + " Enrolled");
        
    }

    public void removeSubject() {

    }

    public void showEnrollmentList() {
        System.out.println("Enrollment list: ");
        for (Subject s : subjects) {
            System.out.print(s.subjectID + " ");
            System.out.println(s.subjectName);
        }

    }

    public Student() {
    }

    public Student(String studentID, String name, String email, String password) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
        this.password = password;

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

}
