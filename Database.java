import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Database implements Serializable {

    private static final long serialVersionUID = 2L;
    static String fileName = "students.data";
    
    // check if the file "students.data" exists before using it
    public static boolean ifExsits() {

        if (new File(fileName).exists()) {
            return true;
        }
        return false;
    }

    // create the file "students.data" if it doesn’t exists
    public static void createFile() throws IOException {

        if (!ifExsits()) {
            File file = new File(fileName);
            file.createNewFile();
        } else {
            System.out.println("File already existed!");
        }
    }

    // write objects to the file "students.data"
    public static void write(Student s) throws IOException, ClassNotFoundException {

        List<Student> list = read();
        List<Student> students = new ArrayList<>();
        for (Student student : list) {
            students.add(student);
        }
        FileOutputStream fStream = new FileOutputStream(fileName);
        ObjectOutputStream stream = new ObjectOutputStream(fStream);
        students.add(s);
        stream.writeObject(students);

        stream.close();
    }

    // delete objects to the file "students.data"
    public static void delete(String id) throws IOException, ClassNotFoundException {

        List<Student> list = read();
        List<Student> students = new CopyOnWriteArrayList<>();
        for (Student student : list) {
            students.add(student);
        }
        for (Student stu : students) {
            if (stu.getStudentID().equals(id)) {
                students.remove(stu);
            }
        }
        FileOutputStream fStream = new FileOutputStream(fileName);
        ObjectOutputStream stream = new ObjectOutputStream(fStream);

        stream.writeObject(students);

        stream.close();
    }
    
    // update objects to the file "students.data"
    public static void update(Student s) throws IOException, ClassNotFoundException {

        List<Student> list = read();
        List<Student> students = new CopyOnWriteArrayList<>();
        //copy
        for (Student student : list) {
            students.add(student);
        }
        //remove old student 
        for (Student stu : students) {
            if (stu.getName().equals(s.getName())) {
                students.remove(stu);
            }
        }
        
        FileOutputStream fStream = new FileOutputStream(fileName);
        ObjectOutputStream stream = new ObjectOutputStream(fStream);
        //add new student
        students.add(s);
        stream.writeObject(students);

        stream.close();
    }

    // read objects from the file "students.data"
    public static List<Student> read() {

        List<Student> list = new ArrayList<>();
        FileInputStream inputStream;
        ObjectInputStream stream;
        try {
            inputStream = new FileInputStream(fileName);
            stream = new ObjectInputStream(inputStream);
            list = (List<Student>) stream.readObject();
            stream.close(); 
        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return list;
    }

    // clear the objects from the file “students.data”
    public static void clear() throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write("");
        // writer.flush();
        writer.close();
    }


}
