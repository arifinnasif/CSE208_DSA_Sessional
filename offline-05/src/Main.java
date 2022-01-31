import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        RandomWordGenerator.generate();
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        // HashTableSepChain1 ht = new HashTableSepChain1(N);
        // HashTableSepChain2 ht = new HashTableSepChain2(N);

        HashTableDoubleHash1 ht = new HashTableDoubleHash1(N);
        // HashTableDoubleHash2 ht = new HashTableDoubleHash2(N);

        // HashTableCusProbe1 ht = new HashTableCusProbe1(N);
        // HashTableCusProbe2 ht = new HashTableCusProbe2(N);

        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("10kwords.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                ht.insert(line, ++i);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("1kwords.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                ht.search(line);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(ht.getCollision());
        System.out.println(ht.getAvgProbes());
    }
}
