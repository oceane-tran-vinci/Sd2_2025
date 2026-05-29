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
  // ITERATEUR
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
  // Implémentez une méthode qui retourne
  // la valeur maximale présente dans l’arbre.
  // -------------------------------------------------------

  public int valeurMax() {
    return 0;
  }

  // -------------------------------------------------------
  // 2) sommeDesValeurs()
  // Implémentez une méthode qui retourne
  // la somme des valeurs des nœuds de l’arbre.
  // -------------------------------------------------------

  public int sommeDesValeurs() {
    return 0;
  }

  // -------------------------------------------------------
  // 3) nbFeuilles()
  // Implémentez une méthode qui retourne
  // le nombre de feuilles dans l’arbre.
  // -------------------------------------------------------

  public int nbFeuilles() {
    return 0;
  }

  // -------------------------------------------------------
  // 4) nbNoeudEgalA(int x)
  // Compter les occurrences d’une valeur donnée.
  // -------------------------------------------------------

  public int nbNoeudEgalA(int x) {
    return 0;
  }

  // -------------------------------------------------------
  // 5) hauteur()
  // Implémentez une méthode qui retourne
  // la hauteur de l’arbre.
  // -------------------------------------------------------

  public int hauteur() {
    return 0;
  }

  // -------------------------------------------------------
  // 6) existeValeur(int x)
  // Implémentez une méthode qui retourne
  // true si x est présent dans l’arbre.
  // -------------------------------------------------------

  public boolean existeValeur(int x) {
    return false;
  }

  // -------------------------------------------------------
  // 7) tousInferieursOuEgaux()
  // Implémentez une méthode qui retourne true
  // si tous les enfants sont <= à leur parent.
  // -------------------------------------------------------

  public boolean tousInferieursOuEgaux() {
    return false;
  }

  // =======================================================
  // 🟡 EXERCICES MOYENS
  // =======================================================

  // -------------------------------------------------------
  // 8) valeursFeuilles()
  // Retourne un ensemble contenant
  // les valeurs de toutes les feuilles.
  // -------------------------------------------------------

  public HashSet<Integer> valeursFeuilles() {
    return null;
  }

  // -------------------------------------------------------
  // 9) nbApparitionsParValeur()
  // Retourne une map :
  // valeur -> nombre d’apparitions.
  // -------------------------------------------------------

  public HashMap<Integer, Integer> nbApparitionsParValeur() {
    return null;
  }

  // -------------------------------------------------------
  // 10) valeursInternes()
  // Retourne les valeurs des noeuds internes.
  // -------------------------------------------------------

  public HashSet<Integer> valeursInternes() {
    return null;
  }

  // -------------------------------------------------------
  // 11) nbNiveaux()
  // Retourne le nombre total de niveaux.
  // -------------------------------------------------------

  public int nbNiveaux() {
    return 0;
  }

  // -------------------------------------------------------
  // 12) valeursTriees()
  // Retourne toutes les valeurs triées
  // dans un TreeSet.
  // -------------------------------------------------------

  public TreeSet<Integer> valeursTriees() {
    return null;
  }

  // -------------------------------------------------------
  // 13) tousLesNoeudsOntLaMemeValeur()
  // Vérifie si tous les noeuds ont
  // la même valeur que la racine.
  // -------------------------------------------------------

  public boolean tousLesNoeudsOntLaMemeValeur() {
    return false;
  }

  // =======================================================
  // 🟠 EXERCICES MODERES
  // =======================================================

  // -------------------------------------------------------
  // 14) profondeurValeur(int x)
  // Retourne la profondeur de la première
  // occurrence de x.
  // Racine = profondeur 0.
  // Retourne -1 si absent.
  // -------------------------------------------------------

  public int profondeurValeur(int x) {
    return 0;
  }

  // -------------------------------------------------------
  // 15) valeursAvecProfondeur()
  // Retourne une map :
  // valeur -> profondeur.
  // Si une valeur apparaît plusieurs fois,
  // garder uniquement la première profondeur.
  // -------------------------------------------------------

  public HashMap<Integer, Integer> valeursAvecProfondeur() {
    return null;
  }

  // -------------------------------------------------------
  // 16) nbValeursDifferentes()
  // Retourne le nombre de valeurs différentes.
  // -------------------------------------------------------

  public int nbValeursDifferentes() {
    return 0;
  }

  // -------------------------------------------------------
  // 17) freresTries()
  // Retourne true si, pour chaque noeud,
  // les enfants sont triés dans l’ordre
  // décroissant de gauche à droite.
  // -------------------------------------------------------

  public boolean freresTries() {
    return false;
  }

  // -------------------------------------------------------
  // 18) valeursAuNiveau(int k)
  // Retourne les valeurs des noeuds
  // se trouvant au niveau k.
  // La racine est au niveau 0.
  // -------------------------------------------------------

  public ArrayList<Integer> valeursAuNiveau(int k) {
    return null;
  }

  // =======================================================
  // 🔴 EXERCICES DIFFICILES
  // =======================================================

  // -------------------------------------------------------
  // 19) cheminVers(int x)
  // Retourne le chemin menant
  // à la première occurrence de x.
  // -------------------------------------------------------

  public List<Integer> cheminVers(int x) {
    return null;
  }

  // -------------------------------------------------------
  // 20) cheminMaximal()
  // Retourne le plus long chemin
  // de la racine vers une feuille.
  // -------------------------------------------------------

  public List<Integer> cheminMaximal() {
    return null;
  }

  // -------------------------------------------------------
  // BONUS : cheminValeurMaximale()
  // Retourne le chemin menant
  // à la valeur maximale.
  // -------------------------------------------------------

  public List<Integer> cheminValeurMaximale() {
    return null;
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