import java.util.ArrayList;

public class BinomialTreeNode {
    int value;
    BinomialTreeNode parent;
    ArrayList<BinomialTreeNode> children;

    public BinomialTreeNode(int value) {
        this.value = value;
        parent = null;
        children = new ArrayList<>();
    }
}
