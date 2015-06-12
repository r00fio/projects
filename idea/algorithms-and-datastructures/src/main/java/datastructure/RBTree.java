package datastructure;

/**
 * Created by r00fio on 6/6/15.
 */
public class RBTree {

    private Node root;
    private final boolean black = false;
    private final boolean red = true;

    public void delete(Comparable key) {
        Node n = delete(key, root);

    }
// Not full implementation
    private Node delete(Comparable key, Node node) {
        if (node != null) {
            if (node.less(key)) {
                node.right = delete(key, node.right);
            }else if (node.more(key)) {
                node.left = delete(key, node.left);
            } else {
                Node root = rotateLeft(node);
                root.left = root.left.left;
            }

        }
        return node;
    }

    public Object get(Comparable key) {
        Node n = get(key, root);
        if (n == null) return null;
        else return n.value;
    }

    private Node get(Comparable key, Node node) {
        if (node != null) {
            if (node.less(key)) {
                node = get(key, node.right);
            } else if (node.more(key)) {
                node = get(key, node.left);
            }
        }
        return node;
    }

    public void put(Comparable key, Object value) {
        root = put(root, key, value);
        root.color = black;
    }

    private Node put(Node node, Comparable key, Object value) {
        if (node == null) {
            return new Node(red, null, null, key, value);
        }

        if (node.less(key)) {
            node.right = put(node.right, key, value); // reset sub root
        } else if (node.more(key)) {
            node.left = put(node.left, key, value); // reset sub root
        } else {
            node.value = value;
        }
        return getNewRoot(node);
    }

    private Node getNewRoot(Node node) {
        if (red(node.right) && !red(node.left)) node = rotateLeft(node);
        if (red(node.left) && red(node.left.left)) node = rotateRight(node);
        if (red(node.left) && red(node.right)) flipColors(node);
        return node;
    }

    private void flipColors(Node node) {
        node.color = red;
        node.left.color = black;
        node.right.color = black;
    }

    private Node rotateRight(Node node) {
        Node root = node.left;
        node.left = node.left.right;
        root.right = node;
        root.color = black;
        root.right.color = red;
        return root;
    }

    private Node rotateLeft(Node node) {
        Node root = node.right;
        node.right = node.right.left;
        root.left = node;
        root.left.color = red;
        root.color = black;
        return root;
    }

    private boolean red(Node node) {
        return node != null && node.color == red;
    }

    class Node {
        //syntetic method access$400
        Node(boolean color, Node left, Node right, Comparable key, Object value) {
            this.color = color;
            this.left = left;
            this.right = right;
            this.key = key;
            this.value = value;
        }

        private boolean less(Comparable that) {
            return key.compareTo(that) == -1;
        }

        private boolean more(Comparable that) {
            return key.compareTo(that) == 1;
        }

        private boolean color;
        private Node left;
        private Node right;
        private Comparable key;
        private Object value;
    }

}
