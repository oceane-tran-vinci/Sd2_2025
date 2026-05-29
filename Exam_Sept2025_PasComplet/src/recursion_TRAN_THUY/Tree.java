package recursion_TRAN_THUY;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Tree implements Iterable<Tree> {

  private int value;

  private Tree parent;

  private Tree[] children;

  // *******************************************************
  // CONSTRUCTEURS
  // *******************************************************
  public Tree(int v, Tree[] chd) {
    value = v;
    children = chd;

    for (Tree child : chd) {
      child.parent = this;
    }
  }

  public Tree(int v) {
    this(v, new Tree[0]);
  }

  // *******************************************************
  // GETTERS
  // *******************************************************
  public int getValue() {
    return value;
  }

  public Tree getParent() {
    return parent;
  }

  public Tree[] getChildren() {
    return children;
  }

  // *******************************************************
  // ITERATEUR
  // *******************************************************
  public Iterator<Tree> iterator() {
    return Arrays.asList(children).iterator();
  }

  public int nbrChildren() {
    return children.length;
  }

  public boolean isLeaf() {
    return children.length == 0;
  }

  /* Implémentez une méthode qui retourne true si, pour chaque nœud de l’arbre,
  les valeurs de ses enfants sont triées de gauche à droite dans l’ordre décroissant
  (chaque enfant est supérieur ou égal à son frère de droite).
  Sinon, la méthode retourne false.*/
  //TODO
  //Frères triés (non-croissant de gauche à droite)
  public boolean freresTries() {
    // Vérifie l’ordre au niveau courant
    for (int i = 0; i + 1 < children.length; i++) {
      if (children[i].value < children[i + 1].value) {
        return false;
      }
    }
    // Vérifie récursivement tous les sous-arbres
    for (Tree enfant : children) {
      if (!enfant.freresTries()) {
        return false;
      }
    }
    return true;
  }


  /*Implémentez une méthode qui retourne une liste contenant les valeurs de tous les nœuds se trouvant au niveau k de l’arbre.
    - Le niveau de la racine est 0.
    - Si k est plus grand que la hauteur de l’arbre, la liste retournée est vide.
  */
  //TODO
  //Valeurs par niveau (k = 0 -> racine)
  public ArrayList<Integer> valeursAuNiveau(int k) {
    ArrayList<Integer> res = new ArrayList<>();
    if (k < 0) return res;
    collectAuNiveau(this, k, res);
    return res;
  }

  private void collectAuNiveau(Tree node, int k, ArrayList<Integer> acc) {
    if (node == null) return;
    if (k == 0) {
      acc.add(node.value);
      return;
    }
    for (Tree enfant : node.children) {
      collectAuNiveau(enfant, k - 1, acc);
    }
  }

  public static void main(String[] args) {
    Tree l6 = new Tree(1);
    Tree l1 = new Tree(1);
    Tree t9 = new Tree(-9, new Tree[]{l6, l1});
    Tree l3 = new Tree(3);
    Tree l7 = new Tree(7);
    Tree t8 = new Tree(8, new Tree[]{l3, l7});
    Tree l4 = new Tree(4);
    Tree t1 = new Tree(-1, new Tree[]{t8, t9, l4});
    System.out.println(t1.freresTries());
    System.out.println(t1.valeursAuNiveau(2));
  }
}
