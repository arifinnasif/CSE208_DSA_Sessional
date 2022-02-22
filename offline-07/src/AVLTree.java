public class AVLTree<T extends Comparable<T>> {
    private class Node {
        T key;
        Node parentNode;
        Node leftNode;
        Node rightNode;
        int height;
        private Node(T key, Node parentNode) {
            this.key = key;
            this.parentNode = parentNode;
            this.leftNode = null;
            this.rightNode = null;
            this.height = 0;
        }
    }

    private Node root;
    private final boolean VERBOSE = false;

    public AVLTree() {
        this.root = null;
    }


    private void rotateRight(Node center) {
        /*
            center
             /  \
            /    \
          x      tree_b
        /   \
       /     \
   tree_a    x_right
         */

        /*
            x
          /   \
         /     \
   tree_a     center
              /   \
             /     \
        x_right     tree_b
         */


        boolean rootChanged = false;
        if (center == this.root) rootChanged = true;

        Node x = center.leftNode;
        Node x_right = x.rightNode;

        x.rightNode = center;
        x.parentNode = center.parentNode;
        if(!rootChanged && center.parentNode.leftNode == center) center.parentNode.leftNode = x;
        else if(!rootChanged && center.parentNode.rightNode == center) center.parentNode.rightNode = x;
        center.parentNode = x;

        if(x_right != null) x_right.parentNode = center;
        center.leftNode = x_right;

        fixAncestorHeight(center);

        if (rootChanged) {
            this.root = x;
            this.root.parentNode = null;
        }
    }


    private void rotateLeft(Node center) {
        /*
          center
          /   \
         /     \
   tree_a       x
              /   \
             /     \
        x_left      tree_b
         */


        /*
               x
             /   \
            /     \
      center       tree_b
       /   \
      /     \
   tree_a    x_left
         */
        boolean rootChanged = false;
        if (center == this.root) rootChanged = true;

        Node x = center.rightNode;
        Node x_left = x.leftNode;

        x.leftNode = center;
        x.parentNode = center.parentNode;
        if(!rootChanged && center.parentNode.leftNode == center) center.parentNode.leftNode = x;
        else if(!rootChanged && center.parentNode.rightNode == center) center.parentNode.rightNode = x;
        center.parentNode = x;

        if(x_left != null) x_left.parentNode = center;
        center.rightNode = x_left;

        fixAncestorHeight(center);

        if (rootChanged) {
            this.root = x;
            this.root.parentNode = null;
        }
    }


    private int getChildDiff(Node root) {
        if (root == null) return 0;
        int leftHeight = -1;
        int rightHeight = -1;

        if(root.leftNode != null) leftHeight = root.leftNode.height;
        if(root.rightNode != null) rightHeight = root.rightNode.height;

        return leftHeight - rightHeight;
    }


    private void fixAVLproperty(Node start) {
        boolean avlproperty = true;
        while(start != null) {
            int diff = getChildDiff(start);
//            System.out.println(diff);
            if(diff > 1) {
                avlproperty = false;
                int diff2 = getChildDiff(start.leftNode);
//                System.out.println(diff2);
                if(diff2 >= 0) {
                    rotateRight(start);
                }
                else if (diff2 < 0){
                    rotateLeft(start.leftNode);
                    rotateRight(start);
                }
            } else if(diff < -1) {
                avlproperty = false;
                int diff2 = getChildDiff(start.rightNode);
//                System.out.println(diff2);
                if(diff2 <= 0) {
                    rotateLeft(start);
                }
                else if (diff2 > 0) {
                    rotateRight(start.rightNode);
                    rotateLeft(start);
                }
            }
            start = start.parentNode;
        }
        if(!avlproperty) System.out.println("Height invariant violated. After rebalancing:");
    }



    /**
     * adds a node with a given key into the subBST rooted at the given node
     * note: this method doesn't handle empty subtree
     * @param root root of the subtree. must always be non-null
     * @param key value to be inserted
     */
    private void insertItem(Node root, T key) {
        // runs loop until a match is found. if a match is found this method does nothing, as every element in the tree
        // is unique
        Node fixHeightFrom = null;
        while (root.key.compareTo(key) != 0) {
            if (root.key.compareTo(key) > 0) { // root.key > key
                if (root.leftNode == null) {
                    // adds a node to the left of the tree if it is empty. as root.key > key
                    root.leftNode = new Node(key, root);
                    fixHeightFrom = root;
                } else {
                    // moves root to the left. as root.key > key and there are more elements in the subtree that are
                    // less than the given key
                    root = root.leftNode;
                }
            } else if (root.key.compareTo(key) < 0) {
                if (root.rightNode == null) {
                    // adds a node to the right of the tree if it is empty. as root.key < key
                    root.rightNode = new Node(key, root);
                    fixHeightFrom = root;
                } else {
                    // moves root to the root. as root.key < key and there are more elements in the subtree that are
                    // greater than the given key
                    root = root.rightNode;
                }
            }
        }

        fixAncestorHeight(fixHeightFrom);
        fixAVLproperty(fixHeightFrom);


    }


    /**
     * inserts a node with the given key into the BST
     * @param key value to be inserted
     */
    public void insertItem(T key) {
        // handles empty BST
        if (this.root == null) {
            this.root = new Node(key, null);
        }
        insertItem(this.root, key);
    }

    /**
     * searches for a node with the key equal to given key in the subBST rooted at root and returns the node; returns
     * null if not found
     * @param root root of the sub tree
     * @param key value to searched for
     * @return node with the matching key or null if key not found
     */
    private Node searchItem(Node root, T key) {
        // root becoming null means that there are no nodes. runs loop until the tree is exhausted i.e. no match is
        // found.
        while (root != null) {
            if (root.key.compareTo(key) == 0) {
                // match found
                return root;
            } else if (root.key.compareTo(key) > 0) { // root.key > key
                // key is less than the key in the root. moving to the left subtree
                root = root.leftNode;
            } else { // root.key < key
                // key is greater than the key in the root. moving to the right subtree
                root = root.rightNode;
            }
        }
        // this portion of the code only executes when there's no match
        return null;
    }

    /**
     * searches for the given key in the entire BST
     * @param key value to be searched for
     * @return true if and only if key is found or false otherwise
     */
    public boolean searchItem(T key) {
        // as per the assignment requirements, searchItem should print whether the key has been found. but this feature
        // can be "turned off" for the purpose of reusability
        if (VERBOSE) {
            if (searchItem(this.root, key) != null) {
                System.out.println(key.toString() + " has been found");
                return true;
            } else {
                System.out.println(key.toString() + " has not been found");
                return false;
            }
        }
        return searchItem(this.root, key) != null;
    }

    /**
     * searches for the maximum element in the subBST rooted at the given root
     * @param root root of the subtree
     * @return the node with maximum key value or null if the subtree is empty
     */
    private Node getMaxItem(Node root) {
        // moves to right of the non-empty tree until there's no right node
        while (root != null && root.rightNode != null) {
            root = root.rightNode;
        }
        return root;
    }

    /**
     * transfers sub tree to a node. the destination node gets replaced. if the source is null the subtree rooted at the
     * destination node gets deleted
     * @param dest destination node. note dest cannot be null
     * @param source root of the subtree to be moved
     */
    private void transferSubtree(Node dest, Node source) {
        if (dest.parentNode == null) {
            // destination is the root of the tree
            this.root = source;
        } else if (dest.parentNode.leftNode == dest) {
            // destination is the left child of the parent node
            dest.parentNode.leftNode = source;
        } else {
            // destination is the right child of the parent node
            dest.parentNode.rightNode = source;
        }

        if (source != null) {
            // setting the parent of the new node
            source.parentNode = dest.parentNode;
        }
    }

    /**
     * deletes an element if it exists in the tree
     * @param key element to be deleted
     */
    public void deleteItem(T key) {
        Node nodeToDelete = searchItem(this.root, key);

        if (nodeToDelete == null) {
            // the element doesn't exist
            return;
        }

        Node fixHeightFrom;

        if (nodeToDelete.rightNode == null) {
            // handles both no child and strictly left childed node

            fixHeightFrom = nodeToDelete.parentNode;

            // whatever is in the left node (be it null) move it where nodeToDelete is
            transferSubtree(nodeToDelete, nodeToDelete.leftNode);
        } else if (nodeToDelete.leftNode == null) {
            // handles only strictly right childed node

            fixHeightFrom = nodeToDelete.parentNode;

            // whatever is in the right node move it where nodeToDelete is
            transferSubtree(nodeToDelete, nodeToDelete.rightNode);
        } else {
            // handles two childed node

            // gets the maximum element node in the left subtree of the node we want to delete
            Node replacement = getMaxItem(nodeToDelete.leftNode);

            fixHeightFrom = replacement.parentNode;

            if (replacement.parentNode != nodeToDelete) {
                // replacement is not a child of nodeToDelete

                // this block of code mainly takes care of the place from where we remove the replacement node

                fixHeightFrom = replacement;

                Node temp = replacement.parentNode;


                // as we move the replacement to the desired location. anything left of replacement takes its position
                transferSubtree(replacement, replacement.leftNode);
                fixAncestorHeight(temp);

                // and thus left edge of the replacement is "fixed"
                replacement.leftNode = nodeToDelete.leftNode;
                replacement.leftNode.parentNode = replacement;

                // if replacement was the child of nodeToDelete, there's no need for taking care of who is going to sit
                // on replacement's "seat" and the above code only creates a self-referencing node which is not desired.
                // also it deletes replacement node
            }
            // if replacement is a child of nodeToDelete, the deletion occurs in following statements

            // replacement takes nodeToDelete's place
            transferSubtree(nodeToDelete, replacement);
            // and right edge of the replacement is "fixed"
            replacement.rightNode = nodeToDelete.rightNode;
            replacement.rightNode.parentNode = replacement;
        }

        fixAncestorHeight(fixHeightFrom);
        fixAVLproperty(fixHeightFrom);
    }

    private void fixAncestorHeight(Node root) {
        while(root != null) {
            int leftHeight = -1;
            int rightHeight = -1;

            if (root.leftNode != null) leftHeight = root.leftNode.height;
            if (root.rightNode != null) rightHeight = root.rightNode.height;

            root.height = Math.max(leftHeight, rightHeight) + 1;
            root = root.parentNode;
        }
    }


    private void printTree(Node root, StringBuilder str) {
        if (root == null)
            return;

        str.append(root.key);

        if (root.rightNode == null && root.leftNode == null)
            return;

        str.append('(');
        printTree(root.leftNode, str);
        str.append(')');

        str.append('(');
        printTree(root.rightNode, str);
        str.append(')');
    }

    public void printTree() {
        StringBuilder str = new StringBuilder();
        printTree(this.root, str);
        System.out.println(str);
    }
}
