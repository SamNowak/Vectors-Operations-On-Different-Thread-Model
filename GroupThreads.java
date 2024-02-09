public class GroupThreads extends Thread {
    private final int[] vectorX;
    private final int[] vectorZ;
    private final int[] vectorY;
    private int constant;
    private int index_per_thread;
    private int startIndex;

    public GroupThreads(int[] vectorX, int[] vectorY, int[] vectorZ, int constant, int index_per_thread, int startIndex) {
        this.vectorX = vectorX;
        this.vectorY = vectorY;
        this.vectorZ = vectorZ;
        this.constant = constant;
        this.index_per_thread = index_per_thread;
        this.startIndex = startIndex;
    }

    @Override
    public void run() {
        int index_changed = 0;
        while(index_changed < index_per_thread) { //runs untill it hits is allocated index_changed per threads.
            vectorZ[startIndex+index_changed] = vectorX[startIndex+index_changed] * constant;
            vectorZ[startIndex+index_changed] = vectorZ[startIndex+index_changed] + vectorY[startIndex+index_changed];
            index_changed++;
        }
    }
}

