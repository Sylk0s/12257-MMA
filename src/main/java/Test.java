public class Test {

    public static void main(String[] args) {
        Triangle ref = new Triangle();
        ref.a1 = 101;
        ref.a2 = 25;
        ref.a3 = 84;
        for(Triangle triangle : TriangleReader.getPermutations(ref)) {
            float t1Cos = evaluateCosInt(triangle);
            Triangle ref2 = new Triangle();
            ref2.a1 = 89;
            ref2.a2 = 21;
            ref2.a3 = 100;
            boolean found = false;
            for(Triangle triangle2 : TriangleReader.getPermutations(ref2)) {
                float t2Cos = evaluateCosInt(triangle2);
                found = TriangleReader.roughlyEqualsNegative(t1Cos, t2Cos);
                if(found) {
                    System.out.println("Found Pair: \n");
                    System.out.println(triangle.a1 + " " + triangle.a2 + " " + triangle.a3 + "\n");
                    System.out.println(triangle2.a1 + " " + triangle2.a2 + " " + triangle2.a3 + "\n");
                    break;
                }
            }
            if(found)
                break;
        }
    }

    public static float evaluateCosInt(Triangle triangle) {
        return (float) ((Math.pow(triangle.a3, 2) - Math.pow(triangle.a1, 2) - Math.pow(triangle.a2, 2)) / (-2 * triangle.a1 * triangle.a2));
    }
}
