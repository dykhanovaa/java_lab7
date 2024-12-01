class MyThread extends Thread {
    private int[] array;
    private int startIndex;
    private int endIndex;
    private int sum;

    public MyThread(int[] array, int startIndex, int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.sum = 0;
    }

    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            sum += array[i];
        }
    }

    public int getSum() {
        return sum;
    }
}

public class ArraySum {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};
        int middle = array.length / 2;

        MyThread thread1 = new MyThread(array, 0, middle);
        MyThread thread2 = new MyThread(array, middle, array.length);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int totalSum = thread1.getSum() + thread2.getSum();
        System.out.println("Сумма элементов массива: " + totalSum);
    }
}
