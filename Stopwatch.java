public class Stopwatch {

    private final long start;

    public Stopwatch() {
        start = System.nanoTime();
    }

    public double elaspedTime() {
        return (System.nanoTime() - start) / 1000000000.0;
    }
}
