import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil {

    private static void deleteFile(String filePath) {
        File myObj = new File(filePath);
        myObj.delete();
    }

    public static void saveObj(Object obj, String fileName) {
        String folderPath = "SerializedFiles"; 
        String filePath = folderPath + File.separator + fileName; 

        try {
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            deleteFile(filePath);

            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MainObj loadMainObj(String fileName) {
        String folderPath = "SerializedFiles"; 
        String filePath = folderPath + File.separator + fileName;
        File myObj = new File(filePath);
        if(!myObj.exists()){
            return new MainObj();
        }
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            MainObj mainObj = (MainObj) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            return mainObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
