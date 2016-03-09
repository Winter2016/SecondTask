import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ксения on 3/6/2016.
 */
public class ReadWriter {
    private String fileName;
    public ReadWriter (String fName)
    {
        fileName = fName;
    }
    public List<User> readUser()
    {
        List<User> us = new ArrayList<User>();
        String line;
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) {

            while ((line = reader.readLine()) != null) {
                String chunks[] = line.split(" ");
                us.add(new User(chunks[0].toString(), chunks[1].toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't read the file");
        }
        return us;
    }

    public void writeUser(User us) {
        try(FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.append(us.toString());
            writer.append(System.getProperty("line.separator"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't write to the file");
        }
    }

    public void rewriteUser(List<User> uslist)  {
        try(FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (int i = 0; i < uslist.size(); i++) {
                writer.write(uslist.get(i).toString());
                writer.write(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't overwrite the file");
        }
    }
}
