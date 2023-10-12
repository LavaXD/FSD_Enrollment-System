public class Util {
    
    //letter&digits @ university.com
    private static String emailRegex = "^[a-zA-Z0-9_.-]+@university+\\.com$";

    //Start with Capital letter, at least 6 letters, following with at least 3 digits
    private static String passwordRegex = "^[A-Z][A-Za-z]{5,}[0-9]{3,}$";

    public static String getEmailRegex() {
        return emailRegex;
    }

    public static String getPasswordRegex() {
        return passwordRegex;
    }

    
}
