public abstract class HashTableDoubleHash extends HashTableOpenAddr{
    public HashTableDoubleHash(int m) {
        super(m);
    }

    @Override
    int iHash(String str, int itr) {
        return (hashFunction(str) + itr * auxHashFunction(str))%tableLength;
    }
}
