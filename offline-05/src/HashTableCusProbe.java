public abstract class HashTableCusProbe extends HashTableOpenAddr{
    public HashTableCusProbe(int m) {
        super(m);
    }

    @Override
    int iHash(String str, int itr) {
        int c1 = 2;
        int c2 = 3;
        return (hashFunction(str) + c1 * itr * auxHashFunction(str) + c2 * itr * itr) % tableLength;
    }
}
