import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class BinomialHeap {
    private HashMap<Integer, BinomialTreeNode> hashMap;

    BinomialTree[] treeList;
    private int size;

    public BinomialHeap() {
        hashMap = new HashMap<>();
        this.treeList = new BinomialTree[64];
        this.size = 0;
    }

    public BinomialHeap(BinomialTree bt) {
        hashMap = new HashMap<>();
        this.treeList = new BinomialTree[64];
        this.treeList[bt.height] = bt;
        this.size = 1<<bt.height;
    }

    private BinomialHeap(ArrayList<BinomialTreeNode> childrenList) {
        hashMap = new HashMap<>();
        this.treeList = new BinomialTree[64];
        for(int i = 0; i < childrenList.size(); i++) {
            childrenList.get(i).parent = null;
            this.treeList[i] = new BinomialTree(childrenList.get(i), i);
        }
    }

    private BinomialHeap union(BinomialHeap arg) {
        int max_i = (int)Math.max(Math.log(this.size)/Math.log(2), Math.log(arg.size)/Math.log(2));
        BinomialTree carry = null;
        BinomialTree[] anstreeList = new BinomialTree[64];
        int i;
        for(i = 0; i <= max_i; i++) {
            if(carry == null && this.treeList[i] == null && arg.treeList[i] == null) {
                anstreeList[i] = null;
                carry = null;
            } else if (carry != null && this.treeList[i] != null && arg.treeList[i] != null) {
                anstreeList[i] = this.treeList[i];
                carry = carry.plus(arg.treeList[i]);
            } else if ((carry != null && this.treeList[i] == null && arg.treeList[i] == null)) {
                anstreeList[i] = carry;
                carry = null;
            } else if ((carry == null && this.treeList[i] != null && arg.treeList[i] == null)) {
                anstreeList[i] = this.treeList[i];
                carry = null;
            } else if ((carry == null && this.treeList[i] == null && arg.treeList[i] != null)) {
                anstreeList[i] = arg.treeList[i];
                carry = null;
            } else if ((carry != null && this.treeList[i] != null && arg.treeList[i] == null)) {
                anstreeList[i] = null;
                carry = carry.plus(this.treeList[i]);
            } else if ((carry == null && this.treeList[i] != null && arg.treeList[i] != null)) {
                anstreeList[i] = null;
//                if(this.treeList[i].root.value == 7 && arg.treeList[i].root.value == 12) System.out.println("Hi");
                carry = this.treeList[i].plus(arg.treeList[i]);
            } else if ((carry != null && this.treeList[i] == null && arg.treeList[i] != null)) {
                anstreeList[i] = null;
                carry = carry.plus(arg.treeList[i]);
            }
        }
        anstreeList[i] = carry;
        this.treeList = anstreeList;
        this.size = this.size + arg.size;

//        if(arg.treeList[0].root.value == 12) {
//            for(int j = 0; j < 64; j++) {
//                if(anstreeList[j] == null) {
//                    System.out.print("* ");
//                    continue;
//                }
//                System.out.print(anstreeList[j].root.value+" ");
//            }
//            System.out.println();
//        }

        return this;
    }

    public void insert(int value) {
        BinomialTreeNode node = new BinomialTreeNode(value);
        hashMap.put(value, node);
        BinomialHeap bh = new BinomialHeap(new BinomialTree(node));
        this.union(bh);
    }

    private int findMaxTreeIndex() {
        int max = Integer.MIN_VALUE;
        //BinomialTree ret = null;
        int index = -1;
        //System.out.println(">>"+(int)(Math.log(this.size)/Math.log(2)));
        for (int i = 0; i <= (int)(Math.log(this.size)/Math.log(2)); i++) {
            if (this.treeList[i] != null && treeList[i].root.value > max) {
                max = treeList[i].root.value;
                //ret = this.treeList[i];
                index = i;
            }
        }

        return index;
    }

    public int findMax() {
        int btIndex = findMaxTreeIndex();
        return this.treeList[btIndex].root.value;
    }

    public int extractMax() {
        if (size == 0) {
            System.err.println("[ERROR] EMPTY HEAP. EXITING ...");
            System.exit(-1);
        }
        int btIndex = findMaxTreeIndex();
        BinomialHeap bh = new BinomialHeap(this.treeList[btIndex].root.children);
        int ret = this.treeList[btIndex].root.value;
        hashMap.remove(ret);
        this.treeList[btIndex] = null;
        this.size--;
        this.union(bh);
        return ret;
    }

    void debug() {
        for(int i = 0; i < 64; i++) {
            if(treeList[i] == null) {
                System.out.print("* ");
                continue;
            }
            System.out.print(treeList[i].root.value+" ");
        }
        System.out.println();
    }

    void increaseKey(int prev_val, int new_val) {
        BinomialTreeNode node = hashMap.get(prev_val);
        hashMap.remove(prev_val);

        node.value = new_val;
        hashMap.put(new_val, node);
        while (node.parent != null && node.value > node.parent.value) {
            int temp = node.value;
            node.value = node.parent.value;
            hashMap.put(node.value, node);
            node.parent.value = temp;
            hashMap.put(node.parent.value, node.parent);

            node = node.parent;
        }
    }

    void printHeap() {
        System.out.println("Printing Binomial Heap ...");
        System.out.println("--------------------------------");

        for (int i = 0; i <= (int)(Math.log(this.size)/Math.log(2)); i++) {
            if (this.treeList[i] == null) continue;
            this.treeList[i].printTree();
        }
        System.out.println("--------------------------------");
    }
}
