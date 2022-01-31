import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;


public class RandomWordGenerator {
    static HashTableSepChain1 ht = new HashTableSepChain1(1000);
    static ArrayList<String> arrayList = new ArrayList<>();
    static void generate() throws IOException {
        while (arrayList.size() < 10000) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 7; j ++) {
                int randomNum = ThreadLocalRandom.current().nextInt(0, 26);
                sb.append((char)('a'+randomNum));
            }
            if(ht.search(sb.toString()) == -1) {
                ht.insert(sb.toString(), 1);
                arrayList.add(sb.toString());
            }
        }

        FileWriter f0 = new FileWriter("10kwords.txt");
        String newLine = System.getProperty("line.separator");
        for(int i=0;i<10000;i++)
        {
            f0.write(arrayList.get(i)+ newLine);
        }
        f0.close();


        FileWriter f1 = new FileWriter("1kwords.txt");
        Collections.shuffle(arrayList);
        for(int i=0;i<1000;i++)
        {
            f1.write(arrayList.get(i)+ newLine);
        }
        f1.close();

    }
}
