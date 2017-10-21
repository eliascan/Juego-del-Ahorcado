
public class JeuDuPendu {
	/* LES ATTRIBUTS */
	private String chaineCachee, chaineVue, lettresDejaEssayees;
	private int nbErreurs;
	private final int MAX_ERREURS;

	/* LE CONSTRUCTEUR */
	public JeuDuPendu(String palabra) {
		this.nbErreurs = 0;
		this.MAX_ERREURS = 8;
		this.chaineCachee = palabra.toUpperCase();
		this.chaineVue = "";
		for (int i = 0; i < chaineCachee.length(); i++) {
			chaineVue += "*";
		}
	}
	
	/* LES SETTERS & GETTERS */

	public String setChaineCachee(String chaineCachee) {
		return this.chaineCachee = chaineCachee;
	}
	
	public void setLettresDejaEssayees(String lettresDejaEssayees) {
		this.lettresDejaEssayees = lettresDejaEssayees;
	}
	
	public String getLettresDejaEssayees() {
		return lettresDejaEssayees;
	}
	
	public String getChaineVue() {
		return chaineVue.toUpperCase();
	}
	
	public String getChaineCachee() {
		return chaineCachee.toUpperCase();
	}
	
	public int getNbErreurs() {
		return nbErreurs;
	}
	
	public int getMAX_ERREURS() {
		return MAX_ERREURS;
	}
	
	/* LES METHODES */

	/* LE METHODE POUR LES LETTRES DEJA ESSAYEE */
	public boolean dejaEssaye(char letter) {
        if (lettresDejaEssayees.indexOf(letter) != -1) {
        	return true;
        } else {
        	lettresDejaEssayees += letter;
        	return false;
        }
	}
	
	/* LE METHODE POUR TROUVER LES LETTRES QU'ILS SONT DANS LE MOT CACHER */
	public boolean trouve(char lettre) {
		if (chaineCachee.contains(lettre + "")) {
			for (int i = 0; i < chaineCachee.length(); i++) {
				if (chaineCachee.charAt(i) == lettre) {
					chaineVue = chaineVue.substring(0, i) + lettre + chaineVue.substring(i + 1);
				}
		}
			return true;
		} else {
			nbErreurs++;
			return false;
		}
	}
	
	/* RETURNE VRAI SI TOUS LES LETTRES SONT DIVINAI */
	public boolean gagner() {
		return (getChaineCachee().equals(getChaineVue()));
	}
	
	/* RETURNE VRAI SI LES ERREURS ARRIVENT A 8 */
	public boolean perdu() {
		 return (getNbErreurs() == getMAX_ERREURS());
	}
}
