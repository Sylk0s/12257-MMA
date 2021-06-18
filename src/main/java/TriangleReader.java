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
            HashMap<Integer, List<Triangle>> perimeterresults  = new HashMap<>();
            HashMap<Integer, List<Triangle>> arearesults = new HashMap<>();

            for(Triangle triangle : triangles) {
                int perimeter = triangle.P;
                if(!perimeters.containsKey(perimeter)) {
                    perimeters.put(perimeter, triangle);
                } else {
                    if(!perimeterresults.containsKey(perimeter)) {
                        List<Triangle> list = new ArrayList<>();
                        list.add(triangle);
                        perimeterresults.put(perimeter, list);
                    }  else {
                        perimeterresults.get(perimeter).add(triangle);
                    }
                }
            }

            System.out.println(perimeterresults.size());

            perimeterresults.forEach((k,v) -> {
                int peri = k;
                int sp = peri / 2;
                v.forEach(r1 -> {
                    int area = r1.D;
                    v.forEach(r -> {
                        if((r.D == area) && !r.equals(r1)) {
                            arearesults.put(peri, v);
                        }
                    });
                });
            });

            System.out.println(arearesults.size());

            arearesults.forEach((p, a) -> {
                System.out.println("With area: " + a.get(0).D);
                System.out.println("With perimeter: " + a.get(0).P);
                a.forEach(t -> {
                  System.out.println(t.a1 + ", " + t.a2 + ", " + t.a3);
                }); });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
