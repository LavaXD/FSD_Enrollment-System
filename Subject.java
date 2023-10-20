import java.io.Serializable;
import java.util.Random;

class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    private String subjectID;
    private String subjectName;
    private int mark;
    private String Grade;


    public String calculateGrade(int mark) {
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
        return Grade;
    }

    public Subject() {
        /*
         * ID randomly generated 1 <= ID <= 999, unique and formatted as 3-digits width
         * IDs less than 3-digits width should be completed with zeroes from the left.
         */
        Random random = new Random();
        Integer randomNum = random.nextInt(0, 1000);
        String s = String.format("%03d", randomNum);
        this.subjectID = s;

        // mark is randomly generated where 25<= mark <= 100
        int i = random.nextInt(24, 101);
        this.mark = i;

        // grade is determined based on the mark
        this.Grade = calculateGrade(mark);
    }

    @Override
    public String toString() {
        return "Subject [subjectID=" + subjectID + ", subjectName=" + subjectName + "]";
    }

    public String getSubjectID() {
        return subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getMark() {
        return mark;
    }

    public String getGrade() {
        return Grade;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

}