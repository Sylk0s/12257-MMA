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
            List<List<Triangle>> finalresults = new ArrayList<>();

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

            perimeterresults.forEach((k,v) -> {
                int peri = k;
                int sp = peri / 2;
                List<Triangle> samearea = new ArrayList<>();
                v.forEach(r1 -> {
                    int area = r1.D;
                    v.forEach(r -> {
                        if((r.D == area) && !r.equals(r1)) {
                            samearea.add(r);
                        }
                    });
                });
                if(samearea.size() != 0)
                    arearesults.put(peri, samearea);
            });

            arearesults.forEach((a,t) -> {
                Triangle ref = t.get(0);
                    for(Triangle triangle : getPermutations(ref)) {
                        float t1Cos = (float) ((Math.pow(triangle.a3, 2) - Math.pow(triangle.a1, 2) - Math.pow(triangle.a2, 2)) / (-2 * triangle.a1 * triangle.a2));
                        for(int index = 1; index < t.size(); index++) {
                            Triangle triangle2 = t.get(index);
                            float t2Cos = (float) ((Math.pow(triangle2.a3, 2) - Math.pow(triangle2.a1, 2) - Math.pow(triangle2.a2, 2)) / (-2 * triangle2.a1 * triangle2.a2));
                            if(roughlyEqualsNegative(t1Cos, t2Cos)) {
                                List<Triangle> result = new ArrayList<>();
                                result.add(triangle);
                                result.add(triangle2);
                                finalresults.add(result);
                            }
                        }
                    }
            });

            File file = new File("output.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file.getPath());

            finalresults.forEach((result) -> {
                try {
                    writer.write("Pair: \n");
                    result.forEach(t -> {
                        try {
                            writer.write(t.a1 + ", " + t.a2 + ", " + t.a3 + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    writer.write(" \n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();

            /*
            arearesults.forEach((p, a) -> {
                try {
                    writer.write("With area: " + a.get(0).D + "\n");
                    writer.write("With perimeter: " + a.get(0).P + "\n");
                    a.forEach(t -> {
                        try {
                            writer.write(t.a1 + ", " + t.a2 + ", " + t.a3 + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    writer.write(" \n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.close();
            */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Triangle[] getPermutations(Triangle triangle) {
        Triangle t1 = new Triangle();
        t1.a1 = triangle.a1;
        t1.a2 = triangle.a2;
        t1.a3 = triangle.a3;
        Triangle t2 = new Triangle();
        t2.a1 = triangle.a1;
        t2.a2 = triangle.a3;
        t2.a3 = triangle.a2;
        Triangle t3 = new Triangle();
        t3.a1 = triangle.a2;
        t3.a2 = triangle.a1;
        t3.a3 = triangle.a3;
        Triangle t4 = new Triangle();
        t4.a1 = triangle.a2;
        t4.a2 = triangle.a3;
        t4.a3 = triangle.a1;
        Triangle t5 = new Triangle();
        t5.a1 = triangle.a3;
        t5.a2 = triangle.a1;
        t5.a3 = triangle.a2;
        Triangle t6 = new Triangle();
        t6.a1 = triangle.a3;
        t6.a2 = triangle.a2;
        t6.a3 = triangle.a1;

        return new Triangle[]{t1, t2, t3, t4, t5, t6};
    }

    public static final float ERROR_AMOUNT = 0.1f;

    public static boolean roughlyEqualsNegative(float num, float num2) {
        if(Math.abs(num - num2) < ERROR_AMOUNT) return true;
        else return false;
    }
}
