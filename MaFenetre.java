import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.Vector;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class MaFenetre extends JFrame {

    private JLabel trace;
    private JButton quit;

    private JButton chava;
    private JButton charr;
    private JButton add;
    private JButton delete;
    private JTextField saisie;
    private String FaitSaisi;

    private JComboBox cb1;
    private JLabel labcb1;
    private DefaultListModel model;
    private JList lis;
    private JLabel FaitsDeduits;
    private JScrollPane pane;

    private JTable table;
    private JLabel TabTitre;
    private JScrollPane panneau;

    private BDF laBdf;
    private ArrayList<String> contenu_bdf;

    private BDR laBdr;
    private ArrayList<Regle> contenu_bdr;

    public MaFenetre(String s) {
        super(s);
        setSize(1200, 800);
        setLayout(null); // gestion absolue des coordonnées
        // des composants dans la JFrame

        laBdf = new BDF("maBdf.txt");
        contenu_bdf = laBdf.getContenu();

        laBdr = new BDR("ClasseurRegles.csv");
        contenu_bdr = laBdr.getContenu();

        Init_Accessoires();

        Init_Trts();

        Init_Faits();

        Init_Regles();

        setVisible(true);
    }
    Font f4  = new Font("Arial",Font.PLAIN ,12);
    public void Init_Accessoires(){
        trace = new JLabel();
        trace.setBounds(100, 80, 300, 20);
        trace.setFont(f4);
        getContentPane().add(trace);

        quit = new JButton("Quitter");
        quit.setFont(f4);
        quit.setFocusable(false);
        // positionnement et dimt manuel du bouton
        quit.setBounds(1000, 50, 80, 30);

        // traitement d'un clic sur le bouton
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // quand on a clique sur le bouton
                //"Quitter", on sort du programme
                System.exit(0);
            }
        });

        getContentPane().add(quit).setBackground(new java.awt.Color(70,130,180));
        String imgURL="logo_ecm.png";
        ImageIcon icone=new ImageIcon(imgURL);
        //Création d'un JLabel avec un alignement
        JLabel image=new JLabel(icone,JLabel.CENTER);
        image.setBounds(400,-20,250,200);
        //Ajoute le JLabel à la JFrame
        getContentPane().add(image);
        getContentPane().setBackground(new java.awt.Color(201, 214, 245));
    }

    public void Init_Trts () {
        // ajout des boutons chainage avant / arrière
        chava = new JButton("Chainage Avant");
        chava.setBounds(950, 200, 200, 30);
        charr = new JButton("Chainage Arriere");
        charr.setBounds(950, 250, 200, 30);
        MI M=new MI("maBdf.txt","ClasseurRegles.csv");
        chava.setFocusable(false);
        charr.setFocusable(false);
        chava.setFont(f4);
        charr.setFont(f4);
        getContentPane().add(chava).setBackground(new java.awt.Color(70,130,180));
        getContentPane().add(charr).setBackground(new java.awt.Color(70,130,180));

        // ajout des 2 Listeners associés
        chava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // lancer le chainage avant  ...
                trace.setText("Chainage Avant");
                M.chainageAvant(laBdf,laBdr);
                for(int i=0;i<laBdf.getContenu().size();i++) {
                    model.add(i,laBdf.getContenu().get(i));
                    // Faire défiler la vue vers le bas
                    JScrollBar vertical = pane.getVerticalScrollBar();
                    vertical.setValue(vertical.getMaximum());
                }
            }
        });
        charr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // lancer le chainage arrière  ...
                trace.setText("Chainage Arriere");
                trace.setText(String.valueOf(M.chainageArriere(FaitSaisi)));
            }
        });
        saisie = new JTextField(80);
        saisie.setBounds(1000, 300, 100, 30);
        getContentPane().add(saisie);

        // Create an ActionListener for the JTextField component
        saisie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the source of the component, which is our combo box
                JTextField tf = (JTextField) event.getSource();

                // display the selected item
                FaitSaisi = tf.getText();
                trace.setText("Saisie = " + FaitSaisi);

            }
        });

    }

    public void Init_Faits() {
        String[] bookTitles = new String[]{"Effective Java", "Head First Java",
                "Thinking in Java", "Java for Dummies"};

        //cb1 = new JComboBox(bookTitles);
        // mettre chaque fait dans la ComboBox
        cb1 = new JComboBox();
        for (int i = 0; i < contenu_bdf.size(); i++)
            cb1.addItem(contenu_bdf.get(i));

        cb1.setBounds(230, 200, 200, 20);
        cb1.setFont(f4);
        labcb1 = new JLabel("Base de Faits");
        labcb1.setBounds(290, 170, 100, 20);
        labcb1.setFont(f4);

        // Create an ActionListener for the JComboBox component
        cb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the source of the component, which is our combo box
                JComboBox comboBox = (JComboBox) event.getSource();

                // display the selected item
                Object selected = comboBox.getSelectedItem();
                trace.setText("Item choisi = " + selected);
            }
        });
        // add a button add to add a fact
        add = new JButton("Ajout d'un fait");
        add.setBounds(230, 280, 200, 30);
        add.setFont(f4);
        // Créer une étiquette pour la zone de texte flottante
        JLabel label = new JLabel("Rentrez un fait ");
        label.setVisible(false); // rendre l'étiquette initialement invisible
        label.setBounds(80, 280, 150, 20);
        label.setFont(f4);
        getContentPane().add(label);

        // Ajouter un écouteur d'événements pour le bouton add
            add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label.setVisible(true); // afficher l'étiquette lorsque la souris passe sur le bouton
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label.setVisible(false); // masquer l'étiquette lorsque la souris quitte le bouton
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trace.setText("Ajout d'un fait");
                cb1.addItem(FaitSaisi);
            }
        });
        // add a button delete to delete a fact
        delete=new JButton("Suppression d'un fait");
        delete.setBounds(230,240,200,30);
        delete.setFont(f4);
        // Créer une étiquette pour la zone de texte flottante
        JLabel labell = new JLabel("Selectionnez un fait");
        labell.setVisible(false); // rendre l'étiquette initialement invisible
        labell.setBounds(80, 240, 150, 20);
        labell.setFont(f4);
        getContentPane().add(labell);

        // Ajouter un écouteur d'événements pour le bouton delete
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labell.setVisible(true); // afficher l'étiquette lorsque la souris passe sur le bouton
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labell.setVisible(false); // masquer l'étiquette lorsque la souris quitte le bouton
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trace.setText("Suppression d'un fait");
                Object selected = cb1.getSelectedItem();
                cb1.removeItem(selected);
                JScrollBar defile = panneau.getVerticalScrollBar();
                defile.setValue(defile.getMinimum());
            }
        });
        // add to the parent container :
        getContentPane().add(cb1);
        getContentPane().add(labcb1);
        getContentPane().add(add).setBackground(new java.awt.Color(70, 130, 180));
        getContentPane().add(delete).setBackground(new java.awt.Color(70, 130, 180));

        // Faire la même chose pour la liste des faits déduits avec une JList

        // Créer l'objet JList avec ses éléments
        model = new DefaultListModel();
        lis = new JList(model);
        lis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lis.setFont(f4);
        for (int i = 0; i < contenu_bdf.size(); i++)
            model.add(i, contenu_bdf.get(i));

        // Ajouter la JList dans un JScrollPane
        pane = new JScrollPane(lis);
        pane.setBounds(520, 200, 200, 40);
        FaitsDeduits = new JLabel("Faits Deduits");
        FaitsDeduits.setFont(f4);
        FaitsDeduits.setBounds(580, 170, 100, 20);

        // Create an ActionListener for the JList component
        lis.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {     // la valeur de la sélection a changé
                    // Get the source of the component, which is our list
                    JList liste = (JList) e.getSource();
                    Object selected = liste.getSelectedValue();
                    trace.setText("Element choisi = " + selected);
                }
            }
        });

        getContentPane().add(FaitsDeduits);
        getContentPane().add(pane);


    }

    public void Init_Regles() {

        // Ajout d'une JTable "pour voir"

        // Intitulés des colonnes
        Vector<String> colonnes = new Vector<String>();
        // Contenu de la table
        Vector<Vector<String>> donnees = new Vector<Vector<String>>();
        for (int i = 0; i < contenu_bdr.size(); i++) {
            Regle r = contenu_bdr.get(i);
            if (i == 0) {
                String[] sch = r.getSchema();
                for (int j = 0; j < sch.length; j++)
                    colonnes.add(sch[j]);
            }

            String[] val = r.getValeurs();
            Vector<String> donnee = new Vector<String>();
            for (int j = 0; j < val.length; j++)
                donnee.add(val[j]);
            donnees.add(donnee);
        }

        // Création de la table
        table = new JTable(donnees, colonnes);
        table.setFont(f4);
        panneau = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        panneau.setBounds(200, 500, 750, 250);
        TabTitre = new JLabel("Base de Règles");
        TabTitre.setBounds(520, 470, 100, 20);
        TabTitre.setFont(f4);
        getContentPane().add(TabTitre);
        getContentPane().add(panneau);

    }
}

