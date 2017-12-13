package parking_data.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import parking_data.domain.Node;
import parking_data.domain.Road;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for data import
 */
public class TestJson {
    private static final Type ROAD_TYPE =
            new TypeToken<List<Road>>() {
            }.getType();

    public static void main(String[] args) {
        ArrayList<Road> roads = getRoadList();
        ArrayList<Node> nodes = getNodes(roads);
        System.out.println("Check1");
    }

    private static ArrayList<Node> getNodes(ArrayList<Road> roads) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Road r: roads
                ) {
            for (ArrayList<Double> c: r.getGeometry().getCoordinates()
                    ) {
                nodes.add(new Node(r.getProperties().getId().longValue(),
                        r.getProperties().getName(), c.get(0), c.get(1)));
            }

        }
        return nodes;
    }

    private static ArrayList<Road> getRoadList() {
        File file = new File("C:\\git\\road2.json");
        ArrayList<Road> data = new ArrayList<>();
        if (file.canRead()) {
            Gson gson = new Gson();
            JsonReader reader = null;
            try {
                reader = new JsonReader(new FileReader(file));
                data = gson.fromJson(reader, ROAD_TYPE);
                System.out.println(data.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Can't read file");
        }
        return data;
    }
}
