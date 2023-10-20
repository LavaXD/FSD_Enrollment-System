
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class StudentController implements Serializable {

    public static final long serialVersionUID = 3L;
    static Scanner in = new Scanner(System.in);
    
    public static void courseMenu(Student student) throws ClassNotFoundException, IOException {
        String option = "";
        while (!option.equals("x")) {
            System.out.print("\n\u001B[36m   Student Course Menu: [c/e/r/s/x]: \u001B[0 m");
            option = in.nextLine();
            switch (option) {
                case "c":
                    change(student);
                    break;
                case "e":
                    if (student.subjects.size() == 4) {
                        System.out.println("\u001B[31mStudents are allowed to enrol in 4 subjects only\u001B[0m");
                        break;
                    }
                    enroll(student);
                    break;
                case "r":
                    removeSubject(student);
                    break;
                case "s":
                    showSubject(student);
                    break;
                case "x":
                    return;
            }
        }
    }

    public static void change(Student student) throws ClassNotFoundException, IOException {
        System.out.println("\u001B[33mUpdating Password\u001B[0m");
        System.out.print("New Password: ");
        String pass = in.nextLine();

        String confirmPass = "";
        while (!confirmPass.equals("x")) {
            System.out.print("Confirm Password: ");
            confirmPass = in.nextLine();
            if (!pass.equals(confirmPass)) {
                System.out.println("\u001B[31mPassword does not match - try again\u001B[0m");
                continue;
            }
            student.setPassword(confirmPass);
            Database.update(student);
            return;
        }
    }
    
    public static void calculateMarks(Student s) throws ClassNotFoundException, IOException {
    
        double marks = 0.0;
        
        List<Subject> subjects = s.getSubjects();
        for (Subject subject : subjects) {
            marks += (double) subject.getMark();
        }
        marks /= subjects.size();
        s.setMarks(marks);
        Database.update(s);
        
    }
    
    public static String calculateGrade(double mark, Student s) throws ClassNotFoundException, IOException {
        String Grade = "";
        if (mark < 50) {
            Grade = "F";
        } else if (mark >= 50 && mark < 70) {
            Grade = "P";
        } else if (mark >= 70 && mark < 85) {
            Grade = "D";
        } else if (mark >= 85) {
            Grade = "HD";
        }
        s.setGrade(Grade);
        Database.update(s);
        return Grade;
    }
    public static void enroll(Student student) throws ClassNotFoundException, IOException {
        Subject s = new Subject();
        student.subjects.add(s);
        System.out.println("\u001B[33m"+"Enrolling in Subject-"+s.getSubjectID()+"\u001B[0m");

        System.out.println("\u001B[33m"+"You are now enrolled in "+student.subjects.size()+" out of 4 subjects"+"\u001B[0m");
        Database.update(student);
        calculateMarks(student);
        calculateGrade(student.getMarks(),student);
    }

    public static void showSubject(Student student) {
        System.out.println("\u001B[33mShowing "+student.subjects.size()+" subjects\u001B[0m");
        for(Subject s:student.subjects){
            System.out.println("[ Subject::"+s.getSubjectID()+" -- mark = "+s.getMark()+" -- grade = "+s.getGrade()+" ]");
        }
    }
    
    public static void removeSubject(Student student) throws ClassNotFoundException, IOException {
        System.out.print("\u001B[37mRemove Subject by ID: \u001B[0m");
        String id = in.nextLine();
        
        for(Subject subject:student.subjects){
            if(subject.getSubjectID().equals(id)){
                student.subjects.remove(subject);
                System.out.println("\u001B[33mDroping Subject-" + id + "\u001B[0m");
                System.out.println("\u001B[33m" + "You are now enrolled in " + student.subjects.size() + " out of 4 subjects"
                        + "\u001B[0m");
                return;
            }
        }
        System.out.println("\u001B[37mSubject does not exit\u001B[0m");
        Database.update(student);
    }

    

    
}
