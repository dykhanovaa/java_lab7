class MyThread2 extends Thread{
    private int[] array;
    private int maxEl;

    public MyThread2(int[] array){
        this.array = array;
        this.maxEl = Integer.MIN_VALUE;
    }

    public void run(){
        for(int i=0; i < array.length; i++){
            maxEl = Math.max(array[i], maxEl);
        }
    }

    int getMaxEl(){
        return maxEl;
    }

}

public class BiggestElement {
    public static void main(String[] args){
        int[][] matrix = new int[][] {{1,22,-15,24,8}, {1,2,3,4,5}, {-6,7,-8,9,-10}};

        int rowNum = matrix.length;
        MyThread2[] threads = new MyThread2[rowNum];

        for(int i=0; i<rowNum; i++){
            threads[i] = new MyThread2(matrix[i]);
            threads[i].start();
        }

        for(int i=0; i<rowNum; i++){
            try{
                threads[i].join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        int globalMax = Integer.MIN_VALUE;
        for (MyThread2 thread : threads) {
            globalMax = Math.max(globalMax, thread.getMaxEl());
        }

        System.out.println("Наибольший элемент в матрице: " + globalMax);
    }
}
