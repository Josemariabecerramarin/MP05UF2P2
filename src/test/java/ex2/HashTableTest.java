package ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    HashTable hashTable = new HashTable();

    @Test
    void put() {
        hashTable.put("1", "rata");
        hashTable.put("2", "raton");
        hashTable.put("2", "ratita");
        hashTable.put("3", "ratoncito");
        hashTable.put("3", "ratoncita");

        Assertions.assertEquals("\n bucket[1] = [1, rata]", hashTable.toString());
    }
    @Test
    void put2(){

    }

    @Test
    void get() {
        Assertions.assertNull(hashTable.get("1"));
    }

    @Test
    void drop() {
        hashTable.put("1", "rata");
        hashTable.drop("1");
        Assertions.assertNull(hashTable.get("1"));
    }

    @Test
    void count() {
        Assertions.assertEquals(0, hashTable.count());
    }

    @Test
    void size() {
        Assertions.assertEquals(16, hashTable.size());
    }

    @Test
    void testToString() {
    }
}