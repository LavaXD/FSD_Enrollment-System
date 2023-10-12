
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {

    public String name;
    public String adminID;
    public String password;

    static Scanner in = new Scanner(System.in);

    public static void AdminSys() throws IOException{
        
        String option = "";
        while (true) {
            System.out.print("\n\u001B[36mAdmin System: [c/g/p/r/s/x]: \u001B[0m");

            option = in.nextLine();
            switch (option) {
                case "c":
                    Database.clear();
                    break;
                case "g":
                    viewAllStudent();
                    break;
                case "p":
                    viewAllStudent();
                    break;
                case "r":
                    viewAllStudent();
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
        List<Student> list = new ArrayList<>();
        Student Alan = new Student("01", "Alan", "8492@qq", "123");
        list.add(Alan);
        for (Student s : list) {
            System.out.println(s);
        }

    }

    public List<Student> organizeByGrade() {
        return null;
    }

    public void removeStudent(String studentID) {

    }

    public void clearStudentsData(String studentID) {

    }

    public Admin(String adminID, String password) {
        this.adminID = adminID;
        this.password = password;
    }

}
