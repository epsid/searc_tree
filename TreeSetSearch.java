import java.util.*;

public class TreeSetSearch<T extends Comparable<T>> implements Iterable<T> {

    private Node<T> root;
    private int count = 0;

    private int duplicateCount() {
        return count++;
    }

    public static class Node<V> {
        private V data;
        private Node<V> left, right;

        Node(V value) {
            data = value;
        }
    }


    private void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (root == null) {
            root = newNode;
            return;
        }
        Node<T> current = root;

        while (current != null) {
            int compare = value.compareTo(current.data);
            if (compare <= 0) {
                if (current.left == null) {
                    current.left = newNode;
                    return;
                } else {
                    current = current.left;
                }
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


    private int countDuplicate(Node<T> root, T value) {


        Node<T> current = root;

        while (current != null) {
            int compare = value.compareTo(current.data);
            if (compare < 0) {
                current = current.left;
            } else if (compare == 0) {
                duplicateCount();
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return count;

    }

    private T search(Node<T> root, T value) {


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

    public boolean contain(int v) {
        TreeSetSearch value = new TreeSetSearch();


        if (value.search(root, v) != null) {
            return true;
        }
        return false;
    }

    public int count(int v) {

        TreeSetSearch value = new TreeSetSearch();

        return value.countDuplicate(root, v);


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
            Node<T> current = stack.pop();
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
            return current.data;
        }
    }


    private Iterator<T> preorderIterator() {
        return new PreorderIterator();
    }

    @Override
    public Iterator<T> iterator() {
        return preorderIterator();
    }

    private void printTree() {

        Queue<Node> currentLevel = new LinkedList<>();
        Queue<Node> nextLevel = new LinkedList<>();

        currentLevel.add(root);

        while (!currentLevel.isEmpty()) {
            Iterator<Node> iterator = currentLevel.iterator();
            while (iterator.hasNext()) {
                Node currentNode = iterator.next();
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


    public static void main(String[] args) {
        TreeSetSearch<Integer> tree = new TreeSetSearch<>();

        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите количество элементов дерева: ");
            int n = in.nextInt();
            System.out.print("Введите желаемый числовой разброс (от до):");
            int min = in.nextInt();
            int max = in.nextInt();
            System.out.println();
            Random rand = new Random();
            for (int i = 0; i < n; i++) {
                tree.add(rand.nextInt((max - min) + 1) + min);
            }

            tree.printTree();
            System.out.println("Количество элементов дерева : " + n);
            System.out.println("Введите искомое число: ");
            int v = in.nextInt();
            System.out.println();

            if (tree.contain(v)) {
                System.out.println("Искомое число найдено!");
                System.out.println("Совпадений заданного числа " + "(" + v + ")" + " : " + tree.count(v));
            } else {
                System.out.println("Искомое число не найдено!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Введённое число должно быть целым! Попробуйте ещё раз.");
        } catch (NullPointerException e) {
            System.out.println("Введённое число элементов дерева должно быть больше нуля! Попробуйте ещё раз.");
        }
    }
}



