interface IIntegerHash {
 static final int SIZE = 8;

 void remove(Integer c);

 Integer busca(Integer nome);

 void insere(Integer c);
}

abstract class IntegerHashTable implements IIntegerHash {

 private static final Integer ELIMINADO = Integer.MAX_VALUE;

 private Integer[] dic;

 public IntegerHashTable() {
  dic = new Integer[SIZE];
 }

 abstract protected int hash(Integer key, int i);

 protected int hash(Integer key) {
  return (key % SIZE);
 }

 public void insere(Integer key) {
  int i = 0;
  int j = hash(key, i);
  while (i != SIZE) {
   if (dic[j] == null || dic[j] == ELIMINADO) {
    dic[j] = key;
    break;
   }
   i = i + 1;
   j = hash(key, i);
  }
 }

 public void remove(Integer key) {
  int i = 0;
  int j = hash(key, i);
  while ((i != SIZE) && (dic[j] != null)) {
   if (dic[j].equals(key)) {
    dic[j] = ELIMINADO;
    break;
   }
   i = i + 1;
   j = hash(key, i);
  }
 }

 public Integer busca(Integer key) {
  int i = 0;
  int j = hash(key, i);
  while ((i != SIZE) && (dic[j] != null)) {
   if (dic[j].equals(key)) {
    return dic[j];
   }
   i = i + 1;
   j = hash(key, i);
  }
  return null; // Não foi encontrada a chave k
 }

 public void imprime() {
  for (int i = 0; i < dic.length; i++) {
   System.out.println("(" + i + ") => " + dic[i]);
  }
 }
}

class LinearHashTable extends IntegerHashTable {

 protected int hash(Integer key, int i) {
  return (hash(key) + i) % SIZE;
 }
}

class QuadraticaHashTable extends IntegerHashTable {

 private final int c1 = 1;
 private final int c2 = 3;

 protected int hash(Integer key, int i) {
  return (hash(key) + i * c1 + c2 * i * i) % SIZE;
 }
}

class Main2 {
 public static void main(String[] args) {

  int[] v = new int[] { 10, 22, 31, 4, 15, 28, 6, 88 };

  LinearHashTable lht = new LinearHashTable();

  for (int i = 0; i < v.length; i++) {
   lht.insere(v[i]);
  }

  lht.imprime();
  
  QuadraticaHashTable qht = new QuadraticaHashTable();

  for (int i = 0; i < v.length; i++) {
   qht.insere(v[i]);
  }

  qht.imprime();
  
 }
}