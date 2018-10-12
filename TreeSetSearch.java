import java.util.*;

public class TreeSetSearch<T extends Comparable<T>> implements Iterable<T> {

    private ArrayList duplicateNumbers = new ArrayList();
    private Node<T> root;

    public static class Node<V> {
        private V data;
        private Node<V> left, right;

        Node(V value) {
            data = value;
        }
    }


    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (root == null) {
            root = newNode;
            return;
        }
        Node<T> current = root;


        while (current != null) {
            int compare = value.compareTo(current.data);
            if (compare < 0) {
                if (current.left == null) {
                    current.left = newNode;
                    return;
                } else {
                    current = current.left;
                }
            } else if (compare == 0) {
                duplicateNumbers.add(current.data); //Результат +1
                return;
            }

            if (compare > 0) {
                if (current.right == null) {
                    current.right = newNode;
                    return;
                } else {
                    current = current.right;
                }
            }

        }
    }

    public void printTree() {

        Queue<Node> currentLevel = new LinkedList<>();
        Queue<Node> nextLevel = new LinkedList<>();

        currentLevel.add(root);

        while (!currentLevel.isEmpty()) {
            Iterator<Node> iter = currentLevel.iterator();
            while (iter.hasNext()) {
                Node currentNode = iter.next();
                if (currentNode.left != null) {
                    nextLevel.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    nextLevel.add(currentNode.right);
                }
                System.out.print(currentNode.data + " ");
            }
            System.out.println();
            currentLevel = nextLevel;
            nextLevel = new LinkedList<>();

        }


    }

    public int count(int v) {
        int count = Collections.frequency(duplicateNumbers, v);

        return count;

    }

    public T search(Node<T> root, T value) {


        Node<T> current = root;

        while (current != null) {
            int compare = value.compareTo(current.data);
            if (compare < 0) {
                current = current.left;
            } else if (compare == 0) {
                return current.data;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    boolean contain(int v) {
        TreeSetSearch value = new TreeSetSearch();


        if (value.search(root, v) != null) {
            return true;
        }
        return false;
    }


    private class PreorderIterator implements Iterator<T> {
        Stack<Node<T>> stack = new Stack<>();

        PreorderIterator() {
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (stack.isEmpty()) {
                throw new NoSuchElementException();
            }
            Node<T> cur = stack.pop();
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
            return cur.data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public Iterator<T> preorderIterator() {
        return new PreorderIterator();
    }

    @Override
    public Iterator<T> iterator() {
        return preorderIterator();
    }


    public static void main(String[] args) {
        TreeSetSearch<Integer> tree = new TreeSetSearch<>();
        int n = 1000; //Кол-во элементов дерева
        int v = 9;    //Заданное число

        for (int i = 0; i < n; i++) {
            tree.add((int) (Math.random() * 100));
        }

        tree.printTree();
        System.out.println("Заданное число : " + v);
        System.out.println("Есть ли заданное число в дереве? - " + tree.contain(v));
        if (tree.contain(v) == true) {
            System.out.println("Совпадений числа " + v + ": " + tree.count(v));
        }
    }
}



