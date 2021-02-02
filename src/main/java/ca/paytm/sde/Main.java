package ca.paytm.sde;

public class Main {

    public static void main(String[] args) {
        MACollection<Integer> collection = new MACollection<>();
        collection.addAll(new Integer[] {1, 2, 3, 4, 5, 6, 7});
        double movingAverageOf = collection.getMovingAverageOf(2);
        int size = collection.size();
        System.out.println("SIZE: " + size);
        System.out.println("6: " + collection.get(size - 1));
        System.out.println("5: " + collection.get(size - 2));
        System.out.println("4: " + collection.get(size - 3));
        System.out.println("3: " + collection.get(size - 4));
        System.out.println("2: " + collection.get(size - 5));
        System.out.println("1: " + collection.get(size - 6));
        System.out.println("0: " + collection.get(size - 7));
        System.out.println("MA: " + movingAverageOf);
    }
}
