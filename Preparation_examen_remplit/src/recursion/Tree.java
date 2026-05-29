package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

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
  // ITERATOR
  // *******************************************************

  @Override
  public Iterator<Tree> iterator() {
    return Arrays.asList(children).iterator();
  }

  // *******************************************************
  // METHODES UTILITAIRES
  // *******************************************************

  public int nbrChildren() {
    return children.length;
  }

  public boolean isLeaf() {
    return children.length == 0;
  }

  // =======================================================
  // 🟢 EXERCICES FACILES
  // =======================================================

  // -------------------------------------------------------
  // 1) valeurMax()
  // Retourne la valeur maximale de l’arbre
  // -------------------------------------------------------

  public int valeurMax() {
    int max = this.value;

    for (Tree enfant : children) {
      max = Math.max(max, enfant.valeurMax());
    }

    return max;
  }

  // -------------------------------------------------------
  // 2) sommeDesValeurs()
  // Retourne la somme des valeurs de l’arbre
  // -------------------------------------------------------

  public int sommeDesValeurs() {
    int somme = this.value;

    for (Tree enfant : children) {
      somme += enfant.sommeDesValeurs();
    }

    return somme;
  }

  // -------------------------------------------------------
  // 3) nbFeuilles()
  // Retourne le nombre de feuilles
  // -------------------------------------------------------

  public int nbFeuilles() {

    if (this.isLeaf()) {
      return 1;
    }

    int total = 0;

    for (Tree enfant : children) {
      total += enfant.nbFeuilles();
    }

    return total;
  }

  // -------------------------------------------------------
  // 4) nbNoeudEgalA(int x)
  // Compte le nombre d’occurrences de x
  // -------------------------------------------------------

  public int nbNoeudEgalA(int x) {

    int count = (this.value == x) ? 1 : 0;

    for (Tree enfant : children) {
      count += enfant.nbNoeudEgalA(x);
    }

    return count;
  }

  // -------------------------------------------------------
  // 5) hauteur()
  // Retourne la hauteur de l’arbre
  // -------------------------------------------------------

  public int hauteur() {

    int max = 0;

    for (Tree enfant : children) {
      max = Math.max(max, enfant.hauteur());
    }

    return 1 + max;
  }

  // -------------------------------------------------------
  // 6) existeValeur(int x)
  // Vérifie si x existe dans l’arbre
  // -------------------------------------------------------

  public boolean existeValeur(int x) {

    if (this.value == x) {
      return true;
    }

    for (Tree enfant : children) {

      if (enfant.existeValeur(x)) {
        return true;
      }
    }

    return false;
  }

  // -------------------------------------------------------
  // 7) tousInferieursOuEgaux()
  // Vérifie que chaque enfant <= parent
  // -------------------------------------------------------

  public boolean tousInferieursOuEgaux() {

    for (Tree enfant : children) {

      if (enfant.value > this.value
          || !enfant.tousInferieursOuEgaux()) {

        return false;
      }
    }

    return true;
  }

  // =======================================================
  // 🟡 EXERCICES MOYENS
  // =======================================================

  // -------------------------------------------------------
  // 8) valeursFeuilles()
  // Retourne les valeurs des feuilles
  // -------------------------------------------------------

  public HashSet<Integer> valeursFeuilles() {

    HashSet<Integer> valeurs = new HashSet<>();

    if (this.isLeaf()) {
      valeurs.add(this.value);
    }

    for (Tree enfant : children) {
      valeurs.addAll(enfant.valeursFeuilles());
    }

    return valeurs;
  }

  // -------------------------------------------------------
  // 9) nbApparitionsParValeur()
  // Map : valeur -> nb apparitions
  // -------------------------------------------------------

  public HashMap<Integer, Integer> nbApparitionsParValeur() {

    HashMap<Integer, Integer> map = new HashMap<>();

    remplirMap(this, map);

    return map;
  }

  private void remplirMap(Tree node,
      HashMap<Integer, Integer> map) {

    map.put(node.value,
        map.getOrDefault(node.value, 0) + 1);

    for (Tree enfant : node.children) {
      remplirMap(enfant, map);
    }
  }

  // -------------------------------------------------------
  // 10) valeursInternes()
  // Retourne les valeurs des noeuds internes
  // -------------------------------------------------------

  public HashSet<Integer> valeursInternes() {

    HashSet<Integer> valeurs = new HashSet<>();

    if (!this.isLeaf()) {

      valeurs.add(this.value);

      for (Tree enfant : children) {
        valeurs.addAll(enfant.valeursInternes());
      }
    }

    return valeurs;
  }

  // -------------------------------------------------------
  // 11) nbNiveaux()
  // Retourne le nombre de niveaux
  // -------------------------------------------------------

  public int nbNiveaux() {

    if (this.isLeaf()) {
      return 1;
    }

    int max = 0;

    for (Tree enfant : children) {
      max = Math.max(max, enfant.nbNiveaux());
    }

    return 1 + max;
  }

  // -------------------------------------------------------
  // 12) valeursTriees()
  // Retourne toutes les valeurs triées
  // -------------------------------------------------------

  public TreeSet<Integer> valeursTriees() {

    TreeSet<Integer> set = new TreeSet<>();

    remplirSetTrie(set);

    return set;
  }

  private void remplirSetTrie(TreeSet<Integer> set) {

    set.add(this.value);

    for (Tree enfant : children) {
      enfant.remplirSetTrie(set);
    }
  }

  // -------------------------------------------------------
  // 13) tousLesNoeudsOntLaMemeValeur()
  // Vérifie si tout l’arbre a la même valeur
  // -------------------------------------------------------

  public boolean tousLesNoeudsOntLaMemeValeur() {
    return tousEgauxA(this.value);
  }

  private boolean tousEgauxA(int cible) {

    if (this.value != cible) {
      return false;
    }

    for (Tree enfant : children) {

      if (!enfant.tousEgauxA(cible)) {
        return false;
      }
    }

    return true;
  }

  // =======================================================
  // 🟠 EXERCICES MODERES
  // =======================================================

  // -------------------------------------------------------
  // 14) profondeurValeur(int x)
  // Retourne la profondeur de x
  // -------------------------------------------------------

  public int profondeurValeur(int x) {
    return chercherProfondeur(this, x, 0);
  }

  private int chercherProfondeur(Tree node,
      int x,
      int niveau) {

    if (node.value == x) {
      return niveau;
    }

    for (Tree enfant : node.children) {

      int res =
          chercherProfondeur(enfant,
              x,
              niveau + 1);

      if (res != -1) {
        return res;
      }
    }

    return -1;
  }

  // -------------------------------------------------------
  // 15) valeursAvecProfondeur()
  // Map : valeur -> profondeur
  // -------------------------------------------------------

  public HashMap<Integer, Integer> valeursAvecProfondeur() {

    HashMap<Integer, Integer> map = new HashMap<>();

    remplirValeursProfondeur(this, 0, map);

    return map;
  }

  private void remplirValeursProfondeur(Tree node,
      int profondeur,
      HashMap<Integer, Integer> map) {

    map.putIfAbsent(node.value, profondeur);

    for (Tree enfant : node.children) {

      remplirValeursProfondeur(enfant,
          profondeur + 1,
          map);
    }
  }

  // -------------------------------------------------------
  // 16) nbValeursDifferentes()
  // Compte les valeurs distinctes
  // -------------------------------------------------------

  public int nbValeursDifferentes() {

    HashSet<Integer> set = new HashSet<>();

    remplirSetValeurs(set);

    return set.size();
  }

  private void remplirSetValeurs(HashSet<Integer> set) {

    set.add(this.value);

    for (Tree enfant : children) {
      enfant.remplirSetValeurs(set);
    }
  }

  // -------------------------------------------------------
  // 17) freresTries()
  // Vérifie si les enfants sont triés décroissants
  // -------------------------------------------------------

  public boolean freresTries() {

    for (int i = 0; i + 1 < children.length; i++) {

      if (children[i].value
          < children[i + 1].value) {

        return false;
      }
    }

    for (Tree enfant : children) {

      if (!enfant.freresTries()) {
        return false;
      }
    }

    return true;
  }

  // -------------------------------------------------------
  // 18) valeursAuNiveau(int k)
  // Retourne les valeurs au niveau k
  // -------------------------------------------------------

  public ArrayList<Integer> valeursAuNiveau(int k) {

    ArrayList<Integer> res = new ArrayList<>();

    if (k < 0) {
      return res;
    }

    collectAuNiveau(this, k, res);

    return res;
  }

  private void collectAuNiveau(Tree node,
      int k,
      ArrayList<Integer> acc) {

    if (k == 0) {

      acc.add(node.value);
      return;
    }

    for (Tree enfant : node.children) {
      collectAuNiveau(enfant,
          k - 1,
          acc);
    }
  }

  // =======================================================
  // 🔴 EXERCICES DIFFICILES
  // =======================================================

  // -------------------------------------------------------
  // 19) cheminVers(int x)
  // Retourne le chemin vers x
  // -------------------------------------------------------

  public List<Integer> cheminVers(int x) {

    List<Integer> chemin = new ArrayList<>();

    if (trouverChemin(this, x, chemin)) {
      return chemin;
    }

    return new ArrayList<>();
  }

  private boolean trouverChemin(Tree node,
      int x,
      List<Integer> chemin) {

    chemin.add(node.value);

    if (node.value == x) {
      return true;
    }

    for (Tree enfant : node.children) {

      if (trouverChemin(enfant,
          x,
          chemin)) {

        return true;
      }
    }

    // BACKTRACK
    chemin.remove(chemin.size() - 1);

    return false;
  }

  // -------------------------------------------------------
  // 20) cheminMaximal()
  // Retourne le plus long chemin racine -> feuille
  // -------------------------------------------------------

  public List<Integer> cheminMaximal() {

    List<Integer> chemin = new ArrayList<>();

    chemin.add(this.value);

    List<Integer> meilleur = new ArrayList<>();

    for (Tree enfant : children) {

      List<Integer> tmp =
          enfant.cheminMaximal();

      if (tmp.size() > meilleur.size()) {
        meilleur = tmp;
      }
    }

    chemin.addAll(meilleur);

    return chemin;
  }

  // -------------------------------------------------------
  // BONUS : cheminValeurMaximale()
  // Retourne le chemin vers la plus grande valeur
  // -------------------------------------------------------

  public List<Integer> cheminValeurMaximale() {

    List<Integer> chemin = new ArrayList<>();

    chemin.add(this.value);

    List<Integer> meilleur = new ArrayList<>();

    for (Tree enfant : children) {

      List<Integer> tmp =
          enfant.cheminValeurMaximale();

      if (!tmp.isEmpty()
          && tmp.get(tmp.size() - 1)
          > chemin.get(chemin.size() - 1)) {

        meilleur = tmp;
      }
    }

    chemin.addAll(meilleur);

    return chemin;
  }

  // *******************************************************
  // MAIN TEST
  // *******************************************************

  public static void main(String[] args) {

    Tree l6 = new Tree(6);
    Tree l1 = new Tree(1);

    Tree t9 = new Tree(9,
        new Tree[]{l6, l1});

    Tree l3 = new Tree(3);
    Tree l7 = new Tree(7);

    Tree t8 = new Tree(8,
        new Tree[]{l3, l7});

    Tree l4 = new Tree(4);

    Tree t1 = new Tree(1,
        new Tree[]{t8, t9, l4});

    // ===================================================
    // 🟢 FACILES
    // ===================================================

    System.out.println("===== FACILES =====");

    System.out.println("valeurMax = "
        + t1.valeurMax());

    System.out.println("sommeDesValeurs = "
        + t1.sommeDesValeurs());

    System.out.println("nbFeuilles = "
        + t1.nbFeuilles());

    System.out.println("nbNoeudEgalA(1) = "
        + t1.nbNoeudEgalA(1));

    System.out.println("hauteur = "
        + t1.hauteur());

    System.out.println("existeValeur(7) = "
        + t1.existeValeur(7));

    System.out.println("tousInferieursOuEgaux = "
        + t1.tousInferieursOuEgaux());

    // ===================================================
    // 🟡 MOYENS
    // ===================================================

    System.out.println("\n===== MOYENS =====");

    System.out.println("valeursFeuilles = "
        + t1.valeursFeuilles());

    System.out.println("nbApparitionsParValeur = "
        + t1.nbApparitionsParValeur());

    System.out.println("valeursInternes = "
        + t1.valeursInternes());

    System.out.println("nbNiveaux = "
        + t1.nbNiveaux());

    System.out.println("valeursTriees = "
        + t1.valeursTriees());

    System.out.println("tousLesNoeudsOntLaMemeValeur = "
        + t1.tousLesNoeudsOntLaMemeValeur());

    // ===================================================
    // 🟠 MODERES
    // ===================================================

    System.out.println("\n===== MODERES =====");

    System.out.println("profondeurValeur(7) = "
        + t1.profondeurValeur(7));

    System.out.println("valeursAvecProfondeur = "
        + t1.valeursAvecProfondeur());

    System.out.println("nbValeursDifferentes = "
        + t1.nbValeursDifferentes());

    System.out.println("freresTries = "
        + t1.freresTries());

    System.out.println("valeursAuNiveau(2) = "
        + t1.valeursAuNiveau(2));

    // ===================================================
    // 🔴 DIFFICILES
    // ===================================================

    System.out.println("\n===== DIFFICILES =====");

    System.out.println("cheminVers(7) = "
        + t1.cheminVers(7));

    System.out.println("cheminMaximal = "
        + t1.cheminMaximal());

    System.out.println("cheminValeurMaximale = "
        + t1.cheminValeurMaximale());
  }
}