import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Database {

    static String filePath = "D:\\VSCoding\\ErollmentSys\\students.data";
    static String fileName = "students.data";
    
    // check if the file "students.data" exists before using it
    public static boolean ifExsits() {

        if (new File(filePath).exists()) {
            return true;
        }
        return false;
    }

    // create the file "students.data" if it doesn’t exists
    public static void createFile() throws IOException {

        if (!ifExsits()) {
            File file = new File("students.data");
            file.createNewFile();
        } else {
            System.out.println("File already existed!");
        }
    }

    // write objects to the file "students.data"
    public static void write(String content) throws IOException {
        Writer writer = new FileWriter(fileName, true);
        writer.write(content);
        writer.write("\n");
        writer.close();
    }

    // read objects from the file "students.data"
    public static void read() throws IOException {
        Reader reader = new FileReader(fileName);
        int c = reader.read();
        while (c != -1) {
            System.out.print((char) c);
            c = reader.read();
        }
        reader.close();
    }

    // clear the objects from the file “students.data”
    public static void clear() throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write("");
        // writer.flush();
        writer.close();
    }
}
