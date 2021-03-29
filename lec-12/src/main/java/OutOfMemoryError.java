public class OutOfMemoryError {

    public static void main(String[] args) {
        int multiplier = 100;
        for (int i = 0; i < 100; i++) {
            System.out.println("Round " + i + " Free memory " + Runtime.getRuntime().freeMemory());
            String s = "";
            for (int j = i; j > 1; j--) {
                s = s.concat("s");
            }
            multiplier = multiplier * 10;
        }
    }
}
