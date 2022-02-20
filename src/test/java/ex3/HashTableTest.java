package ex3;

import ex2.HashTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashTableTest {
    ex3.HashTable hashTable = new ex3.HashTable();

    @Test
    void put() {
        //No colisiona en la tabla vacia
        hashTable.put("1", "rata");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]", hashTable.toString());
        Assertions.assertEquals(1 , hashTable.count());
        Assertions.assertEquals(16, hashTable.size());

        //No colisiona en la tabla no vacia
        hashTable.put("2", "raton");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]\n" +
                " bucket[2] = [2, raton]", hashTable.toString());
        Assertions.assertEquals(2 , hashTable.count());
        Assertions.assertEquals(16, hashTable.size());

        //Colisiona dentro de una tabla no vacia y se colocará en 2 posición
        hashTable.put("13", "ratona");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]\n" +
                " bucket[2] = [2, raton] -> [13, ratona]", hashTable.toString());
        Assertions.assertEquals( 3, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());

        //Colisiona dentro de una tabla no vacia y se colocará en 3 posición
        hashTable.put("24", "ratoncito");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]\n" +
                " bucket[2] = [2, raton] -> [13, ratona] -> [24, ratoncito]", hashTable.toString());
        Assertions.assertEquals( 4, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());

        //UPDATES
        //update de un elemento que no colisiona dentro de una tabla vacia
        hashTable.put("1", "ratita");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, ratita]\n" +
                " bucket[2] = [2, raton] -> [13, ratona] -> [24, ratoncito]", hashTable.toString());
        Assertions.assertEquals( 4, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());

        //update de un elemento que si colisiona  en 2 posicion dentro de una tabla no vacia
        hashTable.put("13", "ratatosqr");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, ratita]\n" +
                " bucket[2] = [2, raton] -> [13, ratatosqr] -> [24, ratoncito]", hashTable.toString());
        Assertions.assertEquals( 4, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());

        //update de un elemento que si colisiona en 3 posicion dentro de una tabla no vacia
        hashTable.put("24", "ratonero");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, ratita]\n" +
                " bucket[2] = [2, raton] -> [13, ratatosqr] -> [24, ratonero]", hashTable.toString());
        Assertions.assertEquals( 4, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());

    }


    @Test
    void get() {
        hashTable = new ex3.HashTable();

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

        //Obtenir un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
        hashTable.put("0", "ratillas");
        Assertions.assertEquals(null, hashTable.get("11"));

        //Obtenir un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.
        Assertions.assertEquals(null, hashTable.get("35"));
        /*Assertions.assertNull(hashTable.get("1"));*/
    }

    @Test
    void drop() {
        hashTable = new ex3.HashTable();

        hashTable.put("1", "rata");
        hashTable.put("2", "raton");
        hashTable.put("13", "ratatosqr");
        hashTable.put("24", "ratonero");

        //Esborrar un element que no col·lisiona dins una taula.
        hashTable.drop("1");
        Assertions.assertEquals("\n" +
                " bucket[2] = [2, raton] -> [13, ratatosqr] -> [24, ratonero]", hashTable.toString());
        Assertions.assertEquals( 3, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());

        //Esborrar un element que si col·lisiona dins una taula (1a posició dins el mateix bucket).
        hashTable.put("1", "rata");
        hashTable.drop("2");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]\n" +
                " bucket[2] = [13, ratatosqr] -> [24, ratonero]", hashTable.toString());
        Assertions.assertEquals( 3, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());

        //Esborrar un element que si col·lisiona dins una taula (2a posició dins el mateix bucket).
        hashTable.put("2", "raton");
        hashTable.drop("24");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]\n" +
                " bucket[2] = [13, ratatosqr] -> [2, raton]", hashTable.toString());
        Assertions.assertEquals( 3, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());

        //Esborrar un element que si col·lisiona dins una taula (3a posició dins el mateix bucket).
        hashTable.put("24", "ratonero");
        hashTable.drop("24");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]\n" +
                " bucket[2] = [13, ratatosqr] -> [2, raton]", hashTable.toString());
        Assertions.assertEquals( 3, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());

        //Eliminar un elements que no existeix perquè la seva posició està buida.
        hashTable.put("24", "ratonero");
        hashTable.drop("5");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]\n" +
                " bucket[2] = [13, ratatosqr] -> [2, raton] -> [24, ratonero]", hashTable.toString());
        Assertions.assertEquals( 4, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());

        //Eliminar un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
        hashTable.drop("12");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]\n" +
                " bucket[2] = [13, ratatosqr] -> [2, raton] -> [24, ratonero]" ,hashTable.toString());
        Assertions.assertEquals( 4, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());

        //Eliminar un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.
        hashTable.drop("35");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, rata]\n" +
                " bucket[2] = [13, ratatosqr] -> [2, raton] -> [24, ratonero]" ,hashTable.toString());
        Assertions.assertEquals( 4, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());
    }

    @Test
    void count() {

    }

    @Test
    void size() {}

    @Test
    void testToString() {
    }
}