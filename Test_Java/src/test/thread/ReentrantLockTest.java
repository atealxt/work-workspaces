package test.thread;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * array initialize class
 */
class ThreadInit implements Runnable {

    /** The lock protecting all mutators */
    transient final ReentrantLock lock = new ReentrantLock();

    public void run() {
        final ReentrantLock localLock = this.lock;
        localLock.lock();
        System.out.println("ThreadInit locked");
        try {
            ReentrantLockTest.iArray = new int[10];
            Random random = new Random();
            for (int i = 0; i < ReentrantLockTest.iArray.length; i++) {
                ReentrantLockTest.iArray[i] = random.nextInt();
            }
        } finally {
            localLock.unlock();
            System.out.println("ThreadInit unlocked");
        }

    }
}

/**
 * sort array class
 */
class ThreadSort implements Runnable {

    public void run() {
        while (ReentrantLockTest.iArray == null) {
            System.out.println("waiting init");
        }
        for (int i : ReentrantLockTest.iArray) {
            System.out.print(i + " ");
        }
        System.out.println("\nsort start");
        ReentrantLockTest.iArray = sort(ReentrantLockTest.iArray);
        System.out.println("sort end");
        for (int i : ReentrantLockTest.iArray) {
            System.out.print(i + " ");
        }
    }

    /**
     * sort method
     */
    private int[] sort(final int[] iArr) {
        int iTem = 0;
        boolean bTem = false;
        for (int i = 0; i < iArr.length; i++) {
            bTem = false;
            for (int j = i + 1; j < iArr.length; j++) {
                if (iArr[j] < iArr[i]) {
                    iTem = iArr[j];
                    iArr[j] = iArr[i];
                    iArr[i] = iTem;
                    bTem = true;
                }
            }
            if (!bTem) {
                break;
            }
        }
        return iArr;
    }
}

/**
 * thread test class
 */
public class ReentrantLockTest {

    public static int[] iArray;

    public static void testing() {
        ThreadInit threadInit = new ThreadInit();
        ThreadSort threadSort = new ThreadSort();
        new Thread(threadSort).start();
        new Thread(threadInit).start();
    }
}
