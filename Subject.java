class Subject{

    public String subjectID;
    public String subjectName;
    public int mark;
    public String Grade;

    public void enroll(){
        
    }

    public void calculateGrade(int mark) {
        
    }

    public Subject() {
    }

    public Subject(String subjectID, String subjectName) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "Subject [subjectID=" + subjectID + ", subjectName=" + subjectName + "]";
    }
    
    
}