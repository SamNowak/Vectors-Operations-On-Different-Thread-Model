public class AdjacentThreads extends Thread {

    private final int[] vectorX;
    private final int[] vectorZ;
    private final int[] vectorY;
    private int constant;
    private int numberOfThreads;
    private int startIndex;
    



    public AdjacentThreads(int[] vectorX, int[] vectorY, int[] vectorZ, int constant, int numberOfThreads, int startIndex) {
        this.vectorX = vectorX;
        this.vectorY = vectorY;
        this.vectorZ = vectorZ;
        this.constant = constant;
        this.numberOfThreads = numberOfThreads;
        this.startIndex = startIndex;

    }

    @Override
    public void run() {
        //each thread has it own indexes to hit, so we skip the other numberOfThreads indexes
        for (int i = startIndex; i < vectorX.length; i = i + numberOfThreads){
            vectorZ[i] = vectorX[i] * constant;
            vectorZ[i] = vectorZ[i] + vectorY[i];
        }
    }
}
