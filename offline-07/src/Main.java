import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();
        File file = new File("in.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] cmd = line.split(" ");
                if (cmd[0].equals("I")) {
                    //insert
                    avlTree.insertItem(Integer.parseInt(cmd[1]));
                    avlTree.printTree();

                } else if (cmd[0].equals("D")) {
                    //delete
                    avlTree.deleteItem(Integer.parseInt(cmd[1]));
                    avlTree.printTree();

                } else if (cmd[0].equals("F")) {
                    //find
                    if(avlTree.searchItem(Integer.parseInt(cmd[1]))) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
