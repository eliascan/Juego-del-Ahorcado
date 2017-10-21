import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Hangman {

	private JFrame frame;
	private final JLabel lblTitle = new JLabel("Le jeu du pandu");
	private final JLabel lblImagePandu = new JLabel("");
	private final JLabel lblMot = new JLabel("");
	private final JLabel lblConteur = new JLabel("Vous avez droit \u00E0 un total de 8 erruers.");
	private final JLabel lblLaEssa = new JLabel("Lettre \u00E0 essay\u00E9e :");
	private final JLabel lbllettresDeja = new JLabel("Lettres d\u00E9j\u00E0 essay\u00E9es : ");
	private final JTextField txtLettre = new JTextField();
	private final JTextField txtLettresDejaEs = new JTextField();
	private final JButton btnEssayer = new JButton("Essayer cette lettre");

	String palabraT = "";

	Demarrer palabraL = new Demarrer();
	String palabra = palabraL.regreso();
	
	JeuDuPendu maJeu = new JeuDuPendu(palabra.toUpperCase());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hangman window = new Hangman();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Hangman() {
		initialize();
		for (int i = 0; i < palabra.length(); i++) {
			palabraT += "*";
		}
		lblMot.setText(palabraT.toUpperCase());
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		txtLettresDejaEs.setEditable(false);
		txtLettresDejaEs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtLettresDejaEs.setBounds(150, 466, 192, 20);
		txtLettresDejaEs.setColumns(10);
		txtLettre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtLettre.setBounds(119, 435, 40, 20);
		txtLettre.setColumns(10);
		frame = new JFrame();
		frame.setBounds(100, 100, 368, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		lblTitle.setFont(new Font("Trajan Pro", Font.BOLD, 20));
		lblTitle.setBounds(77, 11, 197, 23);
		
		frame.getContentPane().add(lblTitle);
		lblImagePandu.setIcon(new ImageIcon(Hangman.class.getResource("/Images/Ima_0.png")));
		lblImagePandu.setBounds(51, 47, 250, 300);
		
		frame.getContentPane().add(lblImagePandu);
		lblMot.setHorizontalAlignment(SwingConstants.CENTER);
		lblMot.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMot.setBounds(10, 358, 332, 30);
		
		frame.getContentPane().add(lblMot);
		lblConteur.setHorizontalAlignment(SwingConstants.CENTER);
		lblConteur.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblConteur.setBounds(51, 399, 250, 20);
		
		frame.getContentPane().add(lblConteur);
		lblLaEssa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLaEssa.setBounds(10, 434, 113, 20);
		
		frame.getContentPane().add(lblLaEssa);
		lbllettresDeja.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbllettresDeja.setBounds(10, 465, 151, 20);
		
		frame.getContentPane().add(lbllettresDeja);
		
		frame.getContentPane().add(txtLettre);
		
		frame.getContentPane().add(txtLettresDejaEs);
		btnEssayer.addActionListener(new BtnEssayerActionListener());
		btnEssayer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEssayer.setBounds(169, 434, 173, 23);
		
		frame.getContentPane().add(btnEssayer);
	}
	
	private class BtnEssayerActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			/* VALIDATION SI LE CHAMP EST VIDE */
			if (txtLettre.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ ne doit pas être vide !!!");
				txtLettre.requestFocus();
				return;
			}
			
			/* VARIABLES INTERNES */
			String SaissiLettre = txtLettresDejaEs.getText().toUpperCase(); // Boite de text lettres
			char letter = txtLettre.getText().toUpperCase().charAt(0); // Le lettre
			
			/* VALIDATION SI C'EST UNE LETTRE */
			if (!Character.isLetter(letter)) {
				txtLettre.setText("");
				JOptionPane.showMessageDialog(null, "Vous devez placer une lettre !!!");
				txtLettre.requestFocus();
				return;
			}
			
				/* Verifiez si la lettre �tait d�j� utilise(COMMENCER) -->*/
				
			maJeu.setLettresDejaEssayees(SaissiLettre);
			
            if (maJeu.dejaEssaye(letter)) {
                    JOptionPane.showMessageDialog(null, "Cette lettre a déjà été utilisée");
                    txtLettre.requestFocus();
                    txtLettre.setText("");
                    return;
            } else {
                    txtLettresDejaEs.setText(maJeu.getLettresDejaEssayees().toUpperCase());
                    txtLettre.requestFocus();
                    txtLettre.setText("");
            }			
				/* V�rifiez si la lettre �tait d�j� utilis�e (FIN) <--*/
                                
				/* Trouver la position de la lettre dans le mot (COMMENCER) -->*/
 
            if (maJeu.trouve(letter)) {
				lblMot.setText(maJeu.getChaineVue().toUpperCase());
				if (maJeu.gagner()) {
					btnEssayer.setEnabled(false);
					txtLettre.setEditable(false);
					JOptionPane.showMessageDialog(null, "Bravo ! Vous avez deviné le mot !");
				}
				txtLettre.requestFocus();
			} else {
				lblConteur.setText("Vous avez droit à " + (maJeu.getMAX_ERREURS() - maJeu.getNbErreurs()) + " essais de plus.");
				lblImagePandu.setIcon(new ImageIcon(Hangman.class.getResource("/Images/Ima_" + maJeu.getNbErreurs() + ".png")));
				if (maJeu.perdu()) {
					lblConteur.setText("Après " + maJeu.getNbErreurs() + " erreurs. C'est la fin.");
					btnEssayer.setEnabled(false);
					txtLettre.setEditable(false);
					JOptionPane.showMessageDialog(null, "Désolé, tous vos essais sont épuisés ...\nLe chercheé était : " + maJeu.getChaineCachee());
				}
				txtLettre.requestFocus();
			}

				/* Trouver la position de la lettre dans le mot (FIN) <--*/	
		}
	}
}
