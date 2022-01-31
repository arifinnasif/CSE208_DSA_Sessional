import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BinomialHeap bh = new BinomialHeap();
//        bh.insert(7);
//
//        bh.insert(12);
//
////        bh.debug();
////        for(var i : bh.treeList[1].root.children) {
////            System.out.print(i.value+" | ");
////        }
////        System.out.println("<>"+bh.treeList[1].root.children.size());
//
//
//        bh.insert(19);
//        bh.insert(5);
//        bh.insert(16);
//        bh.insert(6);
//        bh.printHeap();
//
//        System.out.println(bh.extractMax());
//
//        System.out.println(bh.extractMax());
//        bh.increaseKey(5,8);
//        System.out.println(bh.extractMax());
//
//        System.out.println(bh.extractMax());
//        System.out.println(bh.extractMax());
//        System.out.println(bh.extractMax());
//        System.out.println(bh.extractMax());

        File file = new File("sir-in.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] token = line.split(" ");
            if (token[0].equalsIgnoreCase("INS")) {
                int temp = Integer.parseInt(token[1]);
                bh.insert(temp);
                System.out.println("Inserted "+temp);
            } else if (token[0].equalsIgnoreCase("FIN")) {
                int temp = bh.findMax();
                System.out.println("FindMax returned "+temp);
            } else if (token[0].equalsIgnoreCase("EXT")) {
                int temp = bh.extractMax();
                System.out.println("ExtractMax returned "+temp);
            } else if (token[0].equalsIgnoreCase("INC")) {
                int prev = Integer.parseInt(token[1]);
                int next = Integer.parseInt(token[2]);
                bh.increaseKey(prev, next);
                System.out.println("Increased "+ prev +". The updated value is "+ next +".");
            } else if (token[0].equalsIgnoreCase("PRI")) {
                bh.printHeap();
            } else {
                System.err.println("[WARNING] UNKNOWN COMMAND!");
            }
        }
    }
}
