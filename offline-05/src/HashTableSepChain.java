import java.util.ArrayList;

public abstract class HashTableSepChain {
    protected int tableLength;
    private int collision;
    private int number_of_probes;
    private int number_of_searches;

    private ArrayList<Pair>[] table;
    public HashTableSepChain(int m) {
        number_of_searches=0;
        number_of_probes=0;
        collision = 0;
        tableLength = m;
        table = new ArrayList[tableLength];
    }

    public boolean isEmpty() {
        int total_size = 0;
        for(int i = 0; i < tableLength; i++) {
            total_size += table.length;
        }

        return total_size == 0;
    }

    abstract int hashFunction(String str);



    public void insert(String key, int value) {
        int hashvalue = hashFunction(key);

        if(table[hashvalue] == null) table[hashvalue] = new ArrayList<>();

        if (table[hashvalue].size() != 0) collision++;

        for(int i = 0; i < table[hashvalue].size(); i++) {
            if(table[hashvalue].get(i).key.equals(key)) {
                table[hashvalue].get(i).value = value;
                return;
            }
        }
        table[hashvalue].add(new Pair(key, value));
    }

    public void delete(String key) {
        int hashvalue = hashFunction(key);

        for(int i = 0; i < table[hashvalue].size(); i++) {
            if(table[hashvalue].get(i).key.equals(key)) {
                table[hashvalue].remove(i);
                return;
            }
        }
    }

    public int search(String key) {
        number_of_searches++;
        int hashvalue = hashFunction(key);

        if(table[hashvalue] == null) return -1;

        for(int i = 0; i < table[hashvalue].size(); i++) {
            number_of_probes++;
            if(table[hashvalue].get(i).key.equals(key)) {
                return table[hashvalue].get(i).value;
            }
        }
        return -1;
    }

    public int getCollision() {
        return collision;
    }

    public double getAvgProbes() {
        return (double)number_of_probes/(double)number_of_searches;
    }
}
