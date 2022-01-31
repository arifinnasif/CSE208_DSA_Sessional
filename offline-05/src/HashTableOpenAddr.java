import java.util.ArrayList;

public abstract class HashTableOpenAddr {
    protected int tableLength;
    private int collision;
    private int number_of_probes;
    private int number_of_search;

    private Pair[] table;
    public HashTableOpenAddr(int m) {
        collision = 0;
        number_of_probes = 0;
        number_of_search = 0;
        tableLength = m;
        table = new Pair[tableLength];
    }


    abstract int hashFunction(String str);

    int auxHashFunction(String str) {
        int hash = 0;

        for(int i = 0; i < str.length(); i++)
            hash = (hash + (str.charAt(i)-'a')) % tableLength;

        return hash;
    }

    abstract int iHash(String str, int itr);

    public void insert(String key, int value) {
        int i=0;
        do {
            int hashValue = iHash(key, i);
            if (table[hashValue] == null || table[hashValue].value == -1) {
                table[hashValue] = new Pair(key, value);
                return;
            } else {
                collision++;
                i++;
            }
        } while (i != tableLength);
        // System.err.println("Table Full. Cannot insert");
    }

    public void delete(String key) {
        int i = 0;
        do {
            int hashValue = iHash(key, i);
            if (table[hashValue].key.equals(key) && table[hashValue].value != -1) {
                table[hashValue].value=-1;
                return;
            } else {
                i++;
            }
        } while(i != tableLength);
    }

    public int search(String key) {
        int i = 0;
        int hashValue;
        number_of_search++;
        do {
            number_of_probes++;
            hashValue = iHash(key, i);
            if (table[hashValue].key.equals(key) && table[hashValue].value != -1) {
                return table[hashValue].value;
            } else {
                i++;
            }
        } while(i != tableLength && table[hashValue] != null);
        return -1;
    }

    public int getCollision() {
        return collision;
    }

    public double getAvgProbes() {
        return ((double) number_of_probes)/((double) number_of_search);
    }

}
