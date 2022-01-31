import java.util.ArrayList;

public class HashTable {
    private int tableLength;
    private int collision;
    /*
    public class Pair {
        public String key;
        public int value;
        Pair(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }*/

    private ArrayList<Pair>[] table;
    public HashTable(int m) {
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

    private int hashFunction(String str) {
        // polynomialRollingHash
        int p = 31;
        int power_of_p = 1;
        int hash_val = 0;

        // Loop to calculate the hash value
        // by iterating over the elements of String
        for(int i = 0; i < str.length(); i++)
        {
            hash_val = (hash_val + (str.charAt(i) - 'a' + 1) * power_of_p) % tableLength;
            power_of_p = (power_of_p * p) % tableLength;
        }
        return hash_val % tableLength;
    }



    public void insert(String key, int value) {
        int hashvalue = hashFunction(key);

        if(table[hashvalue] == null) table[hashvalue] = new ArrayList<>();

        if (table[hashvalue].size() != 0) collision++;

        for(int i = 0; i < table[hashvalue].size(); i++) {
            if(table[hashvalue].get(i).key == key) {
                table[hashvalue].get(i).value = value;
                return;
            }
        }
        table[hashvalue].add(new Pair(key, value));
    }

    public void delete(String key) {
        int hashvalue = hashFunction(key);

        for(int i = 0; i < table[hashvalue].size(); i++) {
            if(table[hashvalue].get(i).key == key) {
                table[hashvalue].remove(i);
                return;
            }
        }
    }

    public int search(String key) {
        int hashvalue = hashFunction(key);

        for(int i = 0; i < table[hashvalue].size(); i++) {
            if(table[hashvalue].get(i).key == key) {
                return table[hashvalue].get(i).value;
            }
        }
        return -1;
    }

    public int getCollision() {
        return collision;
    }

}
