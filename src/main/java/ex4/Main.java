package ex4;

import ex3.HashTable;

public class Main {
    public static void main(String[] args) {
        ex3.HashTable hashTable = new ex3.HashTable();

        // Put some key values.
        for (int i = 0; i < 50; i++) {
            final String key = String.valueOf(i);
            hashTable.put(key, key);
        }

        // Print the HashTable structure
        ex3.HashTable.log("****   HashTable  ***");
        ex3.HashTable.log(hashTable.toString());
        HashTable.log("\nValue for key(20) : " + hashTable.get("20"));
    }
}