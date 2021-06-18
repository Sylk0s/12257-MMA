import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TriangleReader {

    public static void main(String[] args) throws IOException {

        List<Triangle> triangles = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("triangles.txt"));
            String line;
            int i = 0;
            while((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+");

                Triangle triangle = new Triangle();
                triangle.n = Integer.parseInt(data[1]);
                triangle.a1 = Integer.parseInt(data[2]);
                triangle.a2 = Integer.parseInt(data[3]);
                triangle.a3 = Integer.parseInt(data[4]);
                triangle.P = Integer.parseInt(data[6]);
                triangle.D = Integer.parseInt(data[7]);
                triangle.x1 = Integer.parseInt(data[9]);
                triangle.y1 = Integer.parseInt(data[10]);
                triangle.x2 = Integer.parseInt(data[11]);
                triangle.y2 = Integer.parseInt(data[12]);
                triangle.x3 = Integer.parseInt(data[13]);
                triangle.y3 = Integer.parseInt(data[14]);
                triangle.n1 = Integer.parseInt(data[16]);
                triangle.m1 = Integer.parseInt(data[17]);
                triangle.n2 = Integer.parseInt(data[18]);
                triangle.m2 = Integer.parseInt(data[19]);
                triangle.n3 = Integer.parseInt(data[20]);
                triangle.m3 = Integer.parseInt(data[21]);
                triangle.at = Integer.parseInt(data[23]);

                triangles.add(triangle);
                i++;
            }

            HashMap<Integer, Triangle> perimeters = new HashMap<>();

            for(Triangle triangle : triangles) {
                int perimeter = triangle.a1 + triangle.a2 + triangle.a3;
                if(!perimeters.containsKey(perimeter)) {
                    perimeters.put(perimeter, triangle);
                } else {
                    System.out.println(perimeters.get(perimeter).n + " and " + triangle.n);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
