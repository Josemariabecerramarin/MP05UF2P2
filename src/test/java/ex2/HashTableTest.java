package ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    HashTable hashTable = new HashTable();

    @Test
    void put() {
        hashTable.put("1", "rata");//No colisiona en la tabla vacia
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]", hashTable.toString());

        hashTable.put("2", "raton");//No colisiona en la tabla no vacia
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]\n" +
                " bucket[2] = [2, raton]", hashTable.toString());

        hashTable.put("13", "ratona");//Colisiona dentro de una tabla no vacia y se colocará en 2 posición
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]\n" +
                " bucket[2] = [2, raton] -> [13, ratona]", hashTable.toString());

        hashTable.put("24", "ratoncito");//Colisiona dentro de una tabla no vacia y se colocará en 3 posición
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]\n" +
                " bucket[2] = [2, raton] -> [13, ratona] -> [24, ratoncito]", hashTable.toString());

        //UPDATES
        //update de un elemento que no colisiona dentro de una tabla vacia
        hashTable.put("1", "ratita");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, ratita]\n" +
                " bucket[2] = [2, raton] -> [13, ratona] -> [24, ratoncito]", hashTable.toString());

        //update de un elemento que si colisiona  en 2 posicion dentro de una tabla no vacia
        hashTable.put("13", "ratatosqr");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, ratita]\n" +
                " bucket[2] = [2, raton] -> [13, ratatosqr] -> [24, ratoncito]", hashTable.toString());

        //update de un elemento que si colisiona en 3 posicion dentro de una tabla no vacia
        hashTable.put("24", "ratonero");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, ratita]\n" +
                " bucket[2] = [2, raton] -> [13, ratatosqr] -> [24, ratonero]", hashTable.toString());

    }


    @Test
    void get() {
        HashTable hashTable = new HashTable();

        //Obtenir un element que no col·lisiona dins una taula vuida.
        hashTable.put("1", "ratita");
        Assertions.assertEquals("ratita", hashTable.get("1"));

        //Obtenir un element que col·lisiona dins una taula (1a posició dins el mateix bucket).
        hashTable.put("2", "raton");
        Assertions.assertEquals("raton", hashTable.get("2"));

        //Obtenir un element que col·lisiona dins una taula (2a posició dins el mateix bucket).
        hashTable.put("13", "ratatosqr");
        Assertions.assertEquals("ratatosqr", hashTable.get("13"));

        //Obtenir un element que col·lisiona dins una taula (3a posició dins el mateix bucket).
        hashTable.put("24", "ratonero");
        Assertions.assertEquals("ratonero", hashTable.get("24"));

        //Obtenir un elements que no existeix perquè la seva posició està buida.
        Assertions.assertEquals(null, hashTable.get("0"));

        /*Assertions.assertEquals(null,hashTable.get("27"));*/

        //Obtenir un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
        hashTable.put("0", "ratillas");
        Assertions.assertEquals(null, hashTable.get("11"));

        //Obtenir un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.
        Assertions.assertEquals(null, hashTable.get("35"));
        /*Assertions.assertNull(hashTable.get("1"));*/
    }

    @Test
    void drop() {
        HashTable hashTable = new HashTable();

        hashTable.put("1", "rata");
        hashTable.put("2", "raton");
        hashTable.put("13", "ratatosqr");
        hashTable.put("24", "ratonero");

        hashTable.drop("1");
        Assertions.assertEquals("\n" +
                " bucket[2] = [2, raton] -> [13, ratatosqr] -> [24, ratonero]", hashTable.toString());
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