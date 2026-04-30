import java.util.Random;

public class TestHashTable {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(1009);

        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(100000);
            String name = "Name" + random.nextInt(100000);

            MyTestingClass key = new MyTestingClass(id, name);
            Student value = new Student("Student" + i, random.nextInt(10) + 18);

            table.put(key, value);
        }

        System.out.println("Total elements: " + table.size());
        table.printBucketSizes();
    }
}
