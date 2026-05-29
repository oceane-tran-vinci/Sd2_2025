package huffman;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Huffman {

	//Ajouter : implements Comparable<Node> (lier à buildTree)
	private static class Node implements Comparable<Node> {
		private char ch;
		private int freq;
		private final Node left, right;

		public Node(char ch, int freq, Node left, Node right) {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		//lier à buildTree
		@Override
		public int compareTo(Node that) {
			return this.freq - that.freq;
		}
	}

	/***********************************************************
	 M�thode computeFreq
	 ***********************************************************/
	// renvoie une map qui a comme cl� les lettres de la chaine de
	// caract�re donn�e en param�tre et comme valeur la fr�quence de
	// cette lettre dans cette chaine de caract�re
	public static Map<Character, Integer> computeFreq(String s) {
		//TODO
		char[] input = s.toCharArray(); // On convertit la chaîne de caractères en un tableau de caractères

		// On crée une Map pour stocker la fréquence de chaque caractère
		// clé : caractère ; valeur : nombre d'occurrences
		Map<Character, Integer> freq = new HashMap<Character, Integer>();

		// On parcourt chaque caractère du tableau
		for (int i = 0; i < input.length; i++) {
			int oldFreq = 0; // on initialise l'ancienne fréquence à 0

			// Si le caractère a déjà été rencontré, on récupère sa fréquence actuelle
			if (freq.get(input[i]) != null) {
				oldFreq = freq.get(input[i]);
			}
			freq.put(input[i], oldFreq + 1); // On met à jour la fréquence du caractère en l'incrémentant de 1
		}
		return freq; // On retourne la map des fréquences complétée
	}

	/*Version plus simple*/
	// renvoie une map qui a comme cl� les lettres de la chaine de
	// caract�re donn�e en param�tre et comme valeur la fr�quence de
	// cette lettre dans cette chaine de caract�re
	public static Map<Character, Integer> computeFreqV2(String s) {
		//TODO
		//On crée une map vide pour stocker les caractères et leur fréquence
		Map<Character, Integer> freq = new HashMap<>();

		// On parcourt chaque caractère de la chaîne
		for (char c : s.toCharArray()) {
				// La méthode merge fait :
				// - si 'c' n'est pas encore dans la map, elle l'ajoute avec la valeur 1
				// - si 'c' est déjà dans la map, elle met à jour la valeur en faisant : ancienneValeur + 1
				freq.merge(c, 1, Integer::sum);
		}

		// On retourne la map contenant les fréquences
		return freq;
	}


	/***********************************************************
	 M�thode buildTree
	 !! ne pas oublier d'ajouter au constructeur:
	 - implements Comparable<Node>
	 - compareTo(Node that)
	 ***********************************************************/
	// renvoie l'arbre de Huffman obtenu gr�ce � la map des fr�quences des lettres
	public static Node buildTree(Map<Character, Integer> freq) {
		//TODO
		//Création d'une file de priorité (min-heap) pour trier les nœuds selon leur fréquence
		PriorityQueue<Node> p = new PriorityQueue<>();
		Set<Character> characters = freq.keySet();// Récupère tous les caractères de la map des fréquences
		// Pour chaque caractère, crée un nœud feuille et l'ajoute à la file
		for (Character c : characters) {
			// Pour chaque caractère, on crée une feuille (nœud sans enfant) et on l’ajoute dans la file
			p.add(new Node(c, freq.get(c), null, null));
		}

		// Tant qu’il y a au moins deux nœuds dans la file
		while (p.size() > 1) {
			// On retire les deux nœuds avec les plus petites fréquences
			Node x = p.poll();  // le plus petit
			Node y = p.poll();  // le deuxième plus petit

			// On crée un nouveau nœud parent (caractère vide '\0') avec les deux nœuds comme enfants
			// La fréquence du parent est la somme des fréquences des enfants
			Node parent = new Node('\0', x.freq + y.freq, x, y);
			p.add(parent); // On remet ce nœud dans la file pour qu’il soit à son tour utilisé plus tard
		}

		// À la fin, il ne reste qu’un seul nœud dans la file : la racine de l’arbre de Huffman
		return p.poll();
	}

	/***********************************************************
	 M�thode buildCode
	 ***********************************************************/
	// renvoie une map qui associe chaque lettre � son code.
	// Ce code est obtenu en parcourant l'arbre de Huffman donn� en param�tre
	public static Map<Character, String> buildCode(Node root) {
		//TODO
		//On prépare une Map vide pour stocker les associations caractère → code binaire
		Map<Character, String> codeMap = new HashMap<>();
		buildCode(codeMap, root, ""); // On lance la version récursive avec une chaîne vide au départ
		return codeMap; // On retourne la map complète
	}

	//A Ajouter (récursion)
	// Méthode récursive utilisée pour remplir la map des codes Huffman
	private static void buildCode(Map<Character, String> codeMap, Node node, String t) {
		// Si on est sur une feuille → c’est un caractère (pas un nœud intermédiaire)
		if (node.isLeaf()) {
			codeMap.put(node.ch, t); // On ajoute ce caractère dans la map avec le chemin binaire actuel
			return;
		} else { // Sinon, on continue à parcourir les deux branches :
			buildCode(codeMap, node.left, t + '0'); // gauche → on ajoute '0' au code
			buildCode(codeMap, node.right, t + '1'); // droite → on ajoute '1' au code
		}
	}

	/***********************************************************
	 M�thode compress
	 ***********************************************************/
	// encode la chaine de caract�re prise en param�tre en une chaine de
	// bit 0 et 1 en utilisant la map des codes codeMap
	public static String compress(String s, Map<Character, String> codeMap) {
		//TODO
		char[] input = s.toCharArray(); // On convertit la chaîne d'entrée en tableau de caractères
		StringBuffer toReturn = new StringBuffer(); // StringBuffer est utilisé pour construire efficacement la chaîne binaire résultante
		// encode
		// On parcourt chaque caractère de la chaîne d'entrée
		for (int i = 0; i < input.length; i++) {
			String code = codeMap.get(input[i]); // On récupère le code binaire associé à ce caractère dans la map
			toReturn.append(code); // On ajoute ce code à la fin de la chaîne binaire finale
		}
		return toReturn.toString(); // On retourne la chaîne binaire compressée sous forme de String
	}

	/*Version simplifié*/
	// encode la chaine de caract�re prise en param�tre en une chaine de
	// bit 0 et 1 en utilisant la map des codes codeMap
	public static String compressV2(String s, Map<Character, String> codeMap) {
		//TODO
		//StringBuilder permet de construire une chaîne efficacement (mieux que +=)
		StringBuilder toReturn = new StringBuilder();

		// On parcourt chaque caractère de la chaîne d’entrée
		for (char c : s.toCharArray()) {
			// On récupère le code binaire correspondant au caractère c dans la map,
			// et on l'ajoute à la fin de toReturn (.append() = .add() mais pour StringBuilder)
			toReturn.append(codeMap.get(c));
		}
		// Une fois tous les codes ajoutés, on transforme le StringBuilder en String,
		// et on retourne la chaîne compressée (suite de 0 et 1)
		return toReturn.toString();
	}


	/***********************************************************
	 M�thode expand
	 ***********************************************************/
	// Cette m�thode d�code une chaine de 0 et de 1 cod� � l'aide de l'algorithme de Huffman
	// En param�tre, en plus de la chaine � d�coder, est sp�cifi� la racine de l'arbre de
	// Huffman
	public static String expand(Node root, String t) {
		//TODO
		//On crée un StringBuffer pour construire la chaîne décodée caractère par caractère
		StringBuilder decoded = new StringBuilder();
		char[] bits = t.toCharArray(); // On convertit la chaîne binaire (t) en tableau de caractères
		int i=0; // i servira à parcourir les bits dans la chaîne
		int totalChars=root.freq; // totalChars correspond à la fréquence totale, donc au nbr de caractères à reconstruire

		// Pour chaque caractère attendu dans le résultat...
		for (int j=0; j<totalChars;j++) {
			Node current = root; // On commence toujours le parcours depuis la racine

			// Tant qu’on n’est pas arrivé à une feuille (caractère final)
			while (!current .isLeaf()) {
				// Si le bit courant est '1', on va à droite
				char bit = bits[i];
				if (bit == '1') {
					current = current.right;
				} else {
					current = current.left; // Sinon (bit = '0'), on va à gauche
				}
				i++; // On passe au bit suivant
			}
			decoded.append(current.ch); // Une fois arrivé sur une feuille, on récupère le caractère associé
		}
		return decoded.toString(); // On retourne la chaîne finale décodée sous forme de String
	}

	/*MAIN GLOBAL*/
	public static void main(String[] args) throws IOException {
		String s= HuffmanReadFile.loadFile(new File("Preparation_Exam_Juin2025/11-0.txt"));
		Map<Character, Integer> freq = computeFreq(s);
		Node root = buildTree(freq);
		Map<Character, String> code= buildCode(root);
		String compress = compress(s, code);
		HuffmanWriteFile.write("Preparation_Exam_Juin2025/fichier_compresse",compress);
		String texteOriginal =
				expand(root, HuffmanReadFile.read("Preparation_Exam_Juin2025/fichier_compresse"));
		System.out.println(texteOriginal);
	}
}
