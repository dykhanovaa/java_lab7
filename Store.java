import java.util.*;

class Product{
    private String name;
    private int weight;

    public Product(String name, int weight){
        this.name = name;
        this.weight = weight;
    }

    String getName(){
        return name;
    }

    int getWeight(){
        return weight;
    }
}

class Warehouse{
    private Queue<Product> products = new LinkedList<>();

    public void addProduct(Product product){
        products.offer(product);
    }

    public Product takeProduct(){
        return products.poll();
    }

    public boolean isEmpty(){
        return products.isEmpty();
    }

}


class Loader extends Thread{
    private Warehouse warehouse;
    private int carryWeight = 0;
    private final int MAX_WEIGHT = 150;

    public Loader(Warehouse warehouse){
        this.warehouse = warehouse;
    }

    public void run(){
        while(!warehouse.isEmpty()){
            Product product = warehouse.takeProduct();
            if(product == null) break;
            if(carryWeight + product.getWeight() <= MAX_WEIGHT){
                carryWeight+=product.getWeight();
                System.out.println(Thread.currentThread().getName() + " переносит: " + product.getName() + " (Вес: " + product.getWeight() + " кг, Общий вес: " + carryWeight + ")");
            }else{
                System.out.println(Thread.currentThread().getName() + " достиг лимит веса, унес товары");
                carryWeight = 0;
            }
        }
    }

}

public class Store {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        warehouse.addProduct(new Product("P1", 10));
        warehouse.addProduct(new Product("P2", 20));
        warehouse.addProduct(new Product("P3", 30));
        warehouse.addProduct(new Product("P4", 40));
        warehouse.addProduct(new Product("P5", 50));
        warehouse.addProduct(new Product("P6", 60));
        warehouse.addProduct(new Product("P7", 70));
        warehouse.addProduct(new Product("P8", 80));
        warehouse.addProduct(new Product("P9", 90));
        warehouse.addProduct(new Product("P10", 100));
        warehouse.addProduct(new Product("P11", 110));
        warehouse.addProduct(new Product("P12", 120));

        Loader loader1 = new Loader(warehouse);
        Loader loader2 = new Loader(warehouse);
        Loader loader3 = new Loader(warehouse);

        loader1.start();
        loader2.start();
        loader3.start();

        try{
            loader1.join();
            loader2.join();
            loader3.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Работа закончена");

    }
}
