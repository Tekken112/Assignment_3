public class TestBST {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();

        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(7, "Seven");
        tree.put(2, "Two");
        tree.put(4, "Four");

        System.out.println("Size: " + tree.size());

        System.out.println("Value by key 3: " + tree.get(3));

        System.out.println("In-order traversal:");

        for (BST.Entry<Integer, String> elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        tree.delete(3);

        System.out.println("After deleting key 3:");

        for (BST.Entry<Integer, String> elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        System.out.println("Size: " + tree.size());
    }
}
