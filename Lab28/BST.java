import java.util.List;

public class BST<E extends Comparable<E>> {
    private BNode<E> root;

    public BST() {
        root = null;
    }

    // Add data to this BST
    public void add(E data) {
        if (root == null) {
            root = new BNode<>(data);
        } else {
            addHelper(root, new BNode<>(data));
        }
    }

    // Recursive helper method for add
    private void addHelper(BNode<E> node, BNode<E> addMe) {
        if (addMe.data.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = addMe;
            } else {
                addHelper(node.left, addMe);
            }
        } else {
            if (node.right == null) {
                node.right = addMe;
            } else {
                addHelper(node.right, addMe);
            }
        }
    }

    public void addAll(List<E> data) {
        for (E d : data) {
            add(d);
        }
    }

    // Return the size of this tree (how many nodes are in it?)
    public int size() {
        return sizeHelper(root);
    }

    // Recursive helper method for size
    private int sizeHelper(BNode<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }

    // Return a string with the data of this BST in pre order
    public String preorder() {
        return "[" + preorderHelper(root) + "]";
    }

    // Recursive helper method for preorder
    private String preorderHelper(BNode<E> node) {
        if (node == null) {
            return "";
        }
        String ret = node.data.toString();
        if (node.left != null) {
            ret += ", " + preorderHelper(node.left);
        }
        if (node.right != null) {
            ret += ", " + preorderHelper(node.right);
        }
        return ret;
    }

    // Return a string with the data of this BST in order
    public String inorder() {
        return "[" + inorderHelper(root) + "]";
    }

    // Recursive helper method for inorder
    private String inorderHelper(BNode<E> node) {
        if (node == null) {
            return "";
        }
        String ret = inorderHelper(node.left);
        if (!ret.isEmpty()) {
            ret += ", ";
        }
        ret += node.data.toString();
        String rightStr = inorderHelper(node.right);
        if (!rightStr.isEmpty()) {
            ret += ", " + rightStr;
        }
        return ret;
    }

    // Return a string with the data of this BST in post order
    public String postorder() {
        return "[" + postorderHelper(root) + "]";
    }

    // Recursive helper method for postorder
    private String postorderHelper(BNode<E> node) {
        if (node == null) {
            return "";
        }
        String ret = postorderHelper(node.left);
        if (!ret.isEmpty()) {
            ret += ", ";
        }
        ret += postorderHelper(node.right);
        if (!ret.isEmpty()) {
            ret += ", ";
        }
        ret += node.data.toString();
        return ret;
    }

    // Check if this BST contains the specified data
    public boolean contains(E data) {
        return containsHelper(root, data);
    }

    // Recursive helper method for contains
    private boolean containsHelper(BNode<E> node, E data) {
        if (node == null) {
            return false;
        }
        if (node.data.equals(data)) {
            return true;
        } else if (data.compareTo(node.data) < 0) {
            return containsHelper(node.left, data);
        } else {
            return containsHelper(node.right, data);
        }
    }

    // Remove the first occurrence of data from this BST tree.
    // If data is successfully removed return true, otherwise return false.
    public boolean remove(E data) {
        BNode<E> parent = null;
        BNode<E> current = root;
        while (current != null && !current.data.equals(data)) {
            parent = current;
            if (data.compareTo(current.data) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current == null) {
            return false; // Node not found
        }
        if (current.left == null && current.right == null) {
            if (parent == null) {
                root = null;
            } else {
                if (data.compareTo(parent.data) < 0) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        } else if (current.left == null || current.right == null) {
            BNode<E> child = (current.left != null) ? current.left : current.right;
            if (parent == null) {
                root = child;
            } else {
                if (data.compareTo(parent.data) < 0) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
        } else {
            BNode<E> successorParent = current;
            BNode<E> successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            current.data = successor.data;
            if (successorParent.right == successor) {
                successorParent.right = successor.right;
            } else {
                successorParent.left = successor.right;
            }
        }
        return true;
    }

    // Helper class for BST node
    class BNode<E> {
        private E data;
        private BNode<E> left, right;

        public BNode(E data) {
            this.data = data;
        }
    }
}
