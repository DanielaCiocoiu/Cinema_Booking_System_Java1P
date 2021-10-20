package Gui;

import Controller.PersoaneController;
import Model.Persoane;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class PersoaneFrame extends JFrame {

    private JList listaPersoane;
    private JPanel mainPanel;
    private JTextField adauga;
    private JButton adaugaButton;
    private JButton loginButton;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private DefaultListModel<Persoane> model;

    public PersoaneFrame() {

        model = new DefaultListModel<>();
        listaPersoane.setModel(model);

/*       verifică dacă ȋn baza de date este o ȋntregistrare cu usernameul
        și parola introduse. Dacă persoana exista se deschide o fereastra de tip
        „Rezervări”, dacă nu se afișază mesajul „Username-ul sau parola gresita!”*/
        loginButton.addActionListener(ev -> configureLoginButton());


        //adăuga o nouă persoană ȋn baza de date.
        adaugaButton.addActionListener(ev -> adaugaPersoana());

        listaPersoane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onMouseClickedForList(e);
            }
        });
        afisPersoane();
        setContentPane(mainPanel);
        pack();
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Persoana");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    private void onMouseClickedForList(MouseEvent e) {
/*        String nume = usernameField.getText();
        String password = passwordField.getText();*/

        boolean isItemSelected = listaPersoane.getSelectedValue() != null;
        if (isItemSelected &&
                e.getButton() == MouseEvent.BUTTON1 &&
                e.getClickCount() == 2) {
            Persoane selected = (Persoane) listaPersoane.getSelectedValue();
            new FilmeFrame(selected);
/*            if (authenticateUser(nume, password) && isDigit(password) && verificPersoane(nume)) {
                afisPersoane();
                new FilmeFrame(selected);
            }*/

        }
        if (isItemSelected &&
                e.getButton() == MouseEvent.BUTTON3 &&
                e.getClickCount() == 2) {
            Persoane selected = (Persoane) listaPersoane.getSelectedValue();
            boolean rez = PersoaneController.getInstance()
                    .delete(selected.getId());
            if (rez) { //update lista
                afisPersoane();
            }
        }
    }

    protected void afisPersoane() {
        List<Persoane> persoane = PersoaneController.getInstance().findAll();
        model.clear();
        persoane.forEach(model::addElement); //sau p -> model.addElement(p)
    }

    protected void adaugaPersoana() {
        String nume = adauga.getText();
        String password = adauga.getText();
        Persoane p = new Persoane(0, nume, password);
        boolean rez = PersoaneController.getInstance().create(p);
        if (rez) {
            afisPersoane();
        } else {
            JOptionPane.showMessageDialog(null, "Numele exista deja!");
            adauga.setText("");
        }
    }

    /*Ȋn baza de date nu trebuie să existe doi utilizatori cu același nume, iar
       parola trebuie să se termine cu o cifră
       verifică dacă ȋn baza de date este o ȋntregistrare cu usernameul
       și parola introduse. Dacă persoana exista se deschide o fereastra de tip
       „Rezervări”, dacă nu se afișază mesajul „Username-ul sau parola gresita!”.*/

    private void configureLoginButton() {
        loginButton.addActionListener(e -> {
            Persoane selected = (Persoane) listaPersoane.getSelectedValue();

            String nume = usernameField.getText();
            String password = passwordField.getText();

            if (authenticateUser(nume, password) && isDigit(password) && verificPersoane(nume)) {

              new FilmeFrame(selected).setVisible(true);


                } else {
                JOptionPane.showMessageDialog(null, "Credentiale invalide", "Mesaj", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");

            }
        });
    }

    static boolean authenticateUser(String userName, String password) {
        if (userName.isEmpty() || password.isEmpty() || userName == null) {
            return false;
        } else {
            return true;
        }
    }

    static boolean isDigit(String input) {
        char currentCharacter;
        boolean numberPresent = false;
        for (int i = 0; i < input.length(); i++) {
            currentCharacter = input.charAt(i);
            if (Character.isDigit(currentCharacter)) {
                numberPresent = true;
            }
        }
        return numberPresent;
    }

    static boolean verificPersoane(String nume) {
        boolean numberPresent = false;
        List<Persoane> persoane = PersoaneController.getInstance().findAll();
        for (Persoane persoane1 : persoane) {
            if (persoane1.getNume().equalsIgnoreCase(nume)) {

                numberPresent = true;
            }
        }
        return numberPresent;
    }
}
