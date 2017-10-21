import javax.swing.JOptionPane;

public class Demarrer {
	String palabra = "";
	String[] jeus = {"Avec l'ordinateur", "Avec un ami"};
	String[] mots = {"mango", "Ananas", "Avocat", "cacao", "", "Goyave", "Melon", "Tamarin", "Kiwi", "Grenadelle", "Grenade", "Banane", "coco"};
	String leJeu = (String) JOptionPane.showInputDialog(null, "Comment voulez-vous jouer?", "Le jeu du pendu", JOptionPane.QUESTION_MESSAGE, null, jeus, jeus[0]);
	
	public String regreso() {
        if (leJeu.equals("Avec un ami")) {
        	palabra = JOptionPane.showInputDialog("Le mot cach\u00E9");
        	if (palabra.isEmpty() || palabra.matches("[0-9]") || palabra.matches("[-!$%^&*()_+|~=`{}\\[\\]:\";'<>?,.\\/]")) {
				JOptionPane.showMessageDialog(null, "Vous devez écrire un mot");
				palabra = JOptionPane.showInputDialog("Le mot cach\u00E9");
			}
		}else if (palabra == null || leJeu == null) {
			System.exit(0);
		} else {
			palabra = mots[(int)(Math.random() * mots.length)].toUpperCase();
			JOptionPane.showMessageDialog(null, "Vous devez deviner un fruit tropical");
		}
        return palabra;
	}
}
