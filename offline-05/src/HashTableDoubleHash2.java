public class HashTableDoubleHash2 extends HashTableDoubleHash{
    public HashTableDoubleHash2(int m) {
        super(m);
    }

    @Override
    int hashFunction(String str) {
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
}
