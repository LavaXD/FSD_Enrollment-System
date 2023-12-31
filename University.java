import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;


public class University implements Serializable {

    private static final long serialVersionUID = 6L;
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner in = new Scanner(System.in);

        String option = "";
        while (true) {
            System.out.print("\n\u001B[36mUniversity System: [A]dmin,[S]tudent,[X]:  \u001B[0m");
            option = in.nextLine();
            switch (option) {
                case "a":
                    Admin.AdminSys();
                    break;
                case "s":
                    Student.StudentSys();
                    break;
                case "x":
                    System.out.println("\u001B[33mThank You\u001B[0m");
                    return;
            }
            
        }
    }
}
