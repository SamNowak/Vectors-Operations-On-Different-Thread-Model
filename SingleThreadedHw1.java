import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SingleThreadedHw1 {

    //constant values
    private static final int CONSTANT = (int) Math.floor(Math.random() *(9 + 1) + 0);
    private static final int NUMBER_OF_CORES_2 = 24 * 2;


    //vectorX
    //vectorZ

    //single thread problem. Base Case
    public static void singleThread(int[] vectorX, int[] vectorY, int constant, int[] vectorZ) {
        //start time
        Stopwatch time = new Stopwatch();
        for (int i = 0; i < vectorX.length; i++) {
            vectorZ[i] = vectorX[i] * constant;
            vectorZ[i] = vectorZ[i] + vectorY[i];
        }
        //end time
        //formation of num_of_thread,time for R
        System.out.println(time.elaspedTime());
    }

    public static void main(String[] args) {
        //1000000000

        //vectors
        int[] x = new int[268435456];
        Arrays.fill(x, (int) Math.floor(Math.random() *(9 + 1) + 0));
        int[] y = new int[268435456];
        Arrays.fill(y, (int) Math.floor(Math.random() *(9 + 1) + 0));
        int[] z = new int[268435456];

        //which thread configuation we want to run.
        //change it to user input which option 1, 2, or 3(any other number) for each question
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which Thread model, 1)single 2) adjacent 3+) blocks: ");
        int options = scanner.nextInt();

        //just one thread
        if( options == 1) {
            for(int threadNum = 1; threadNum <= NUMBER_OF_CORES_2; threadNum++) {
                singleThread(x, y, CONSTANT, z);
            }
        }
        else if (options == 2) {
            //for Multi-threaded, where "adjacent" threads scale and add adjacent elements of X and Y.
            // That, is Thread t  will compute the indices t, t + n, t + 2n, ... were n is the number of threads.

            //for loop to get 1 to 2c where c is the number of cores in lamda. Seeing how it changes with more cores
            for(int threadNum = 1; threadNum <= NUMBER_OF_CORES_2; threadNum ++) {

                final List<AdjacentThreads> adThreads = new LinkedList<>();

                final Stopwatch time = new Stopwatch(); //starting clock

                for (int i = 0; i < threadNum; i++) { //creating threads, and running then
                    AdjacentThreads threadAd = new AdjacentThreads(x,y,z,CONSTANT,threadNum,i);
                    adThreads.add(threadAd);
                    threadAd.start(); //start thread
                }
                // Wait for the threads to be done
                for (AdjacentThreads t : adThreads)
                {
                    try
                    {
                        t.join();
                    } catch (InterruptedException ex)
                    {
                        System.err.println("Execution was Interrupted!");
                    }
                }

//                System.out.println(Arrays.toString(x));
//                System.out.println(Arrays.toString(y));
//                System.out.println(Arrays.toString(z));

                //formation of num_of_thread,time for R
                System.out.println(time.elaspedTime()); //end clock
                //re-made the vectors
                Arrays.fill(x, (int) Math.floor(Math.random() *(9 + 1) + 0));
                Arrays.fill(y, (int) Math.floor(Math.random() *(9 + 1) + 0));

            }

        }
        else {
            //Multi-threaded, where threads break the vector into contiguous block of roughly the same
            // size, and each thread works on those blocks.

            //for loop to get 1 to 2c where c is the number of cores in lamda. Seeing how it changes with more cores
            for(int threadNum = 1; threadNum <= NUMBER_OF_CORES_2; threadNum ++) {

                int index_per_thread = x.length / threadNum; // splitting the amount that of indexes blocks that each thread need to work on

                final List<GroupThreads> groupThreads = new LinkedList<>();

                final Stopwatch time2 = new Stopwatch(); //starting clock

                for (int i = 0; i < threadNum; i++) { //creating threads and running them
                    GroupThreads threadBlock = new GroupThreads(x, y, z, CONSTANT, index_per_thread, i * index_per_thread);
                    groupThreads.add(threadBlock);
                    threadBlock.start();
                }

                //waiting for the threads to be done
                for (GroupThreads t : groupThreads)
                {
                    try
                    {
                        t.join();
                    } catch (InterruptedException ex)
                    {
                        System.err.println("Execution was Interrupted!");
                    }
                }

                //formation of num_of_thread,time for R
                System.out.println(time2.elaspedTime()); //end clock

//                System.out.println(Arrays.toString(x));
//                System.out.println(Arrays.toString(y));
//                System.out.println(Arrays.toString(z));
//                resetting arrays
                Arrays.fill(x, (int) Math.floor(Math.random() *(9 + 1) + 0));
                Arrays.fill(y, (int) Math.floor(Math.random() *(9 + 1) + 0));

            }
        }


    }
}
