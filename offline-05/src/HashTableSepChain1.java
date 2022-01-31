public class HashTableSepChain1 extends HashTableSepChain{
    HashTableSepChain1(int m) {
        super(m);
    }
    @Override
    int hashFunction(String str) {
        int hash = 5381;

        for(int i = 0; i < str.length(); i++)
            hash = (((hash << 5) + hash) + (str.charAt(i)-'a'+1)) % tableLength; /* hash * 33 + c */

        return hash;
    }
}
