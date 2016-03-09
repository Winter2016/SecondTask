import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ксения on 3/6/2016.
 */
public class JSONReadWriter {
    private String fileName;
    public JSONReadWriter (String fName)
    {
        fileName = fName;
    }
    public JSONArray readJSON()
    {
        JSONParser parser = new JSONParser();
        JSONArray ulist = new JSONArray();
        try {
            JSONObject jsonObj = (JSONObject) parser.parse(new FileReader(fileName));
            if (!jsonObj.isEmpty())
                ulist = (JSONArray) jsonObj.get("u_list");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Can't parse the file");
        } catch (IOException e) {
            e.printStackTrace();

            System.out.println("Can't read the file");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ulist;
    }
    public List<User> readUser()
    {
        List<User> userlist = new ArrayList<>();
        String login;
        String password;
        JSONArray ulist = readJSON();
        List<JSONObject> ulistObj = new ArrayList<>();
        for(int i = 0; i< ulist.size(); i++) {
            ulistObj.add(i, (JSONObject) ulist.get(i));
            login = ulistObj.get(i).get("u_login").toString();
            password = ulistObj.get(i).get("u_password").toString();
            userlist.add(i, new User(login, password));
        }
        return userlist;
    }

    public void writeUser(User us) {
        JSONArray ulist = readJSON();
        JSONObject ob = new JSONObject();
        ob.put("u_login", us.getLogin());
        ob.put("u_password", us.getPassword());
        ulist.add(ob);
        JSONObject jo = new JSONObject();
        jo.put("u_list", ulist);
        try (FileWriter writer = new FileWriter(fileName)){
            writer.write(jo.toJSONString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't write to the file");
        }
    }

    public void rewriteUser(List<User> uslist) {
        for (User us:uslist) {
            writeUser(us);
        }
    }
}
