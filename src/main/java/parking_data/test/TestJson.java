package parking_data.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import parking_data.domain.Road;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Test class for data import
 */
public class TestJson {
    private static final Type ROAD_TYPE =
            new TypeToken<List<Road>>() {
            }.getType();

    public static void main(String[] args) {
        File file = new File("C:\\git\\road2.json");
        if (file.canRead()) {
            Gson gson = new Gson();
            JsonReader reader = null;
            try {
                reader = new JsonReader(new FileReader(file));
                Road data = gson.fromJson(reader, ROAD_TYPE);
                System.out.println(data.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Can't read file");
        }

    }
}
