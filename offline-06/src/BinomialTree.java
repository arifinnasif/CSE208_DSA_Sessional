import java.util.Collections;
import java.util.LinkedList;

public class BinomialTree {
    BinomialTreeNode root;
    int height;

    public BinomialTree(BinomialTreeNode node) {
        this.root = node;
        this.height = 0;
    }

    public BinomialTree(BinomialTreeNode node, int height) {
        this.root = node;
        this.height = height;
    }

    BinomialTree plus(BinomialTree arg) {
        if (arg.height != this.height) {
            System.out.println("Unequal tree addition. Exiting");
            System.exit(-1);
        }
        if(this.root.value < arg.root.value) {
            this.root.parent = arg.root;
            arg.root.children.add(this.root);
            this.root = arg.root;
        } else {
            arg.root.parent = this.root;
            this.root.children.add(arg.root);
        }

        this.height++;

        return this;
    }

//    void print_tree() {
//        System.out.println(root.value);
//        System.out.println(root.);
//    }

    public void printTree() {
        if (this.root == null) return;

        System.out.println("Binomial Tree, B"+height);

        LinkedList<BinomialTreeNode> linkedList = new LinkedList<>();
        linkedList.add(this.root);
        int count;

        int level = 0;
        while(!linkedList.isEmpty()) {
            count = linkedList.size();

            System.out.print("Level "+ (level++) + " : ");

            while (count > 0) {
                BinomialTreeNode poppedNode = linkedList.pop();
                System.out.print(poppedNode.value+" ");
                LinkedList<BinomialTreeNode> temp = new LinkedList<>(poppedNode.children);
                Collections.reverse(temp);
//                System.out.println();
//                System.out.println("+++++++++++++++++++++++++++++++");
//                for (var h: temp) {
//                    System.out.print(h.value+" ");
//                }
//                System.out.println();
//                for (var h : poppedNode.children) {
//                    System.out.print(h.value+" ");
//                }
//                System.out.println();
//                System.out.println("+++++++++++++++++++++++++++++++");
                linkedList.addAll(temp);

                count--;
            }
            System.out.println();
        }
    }
}
