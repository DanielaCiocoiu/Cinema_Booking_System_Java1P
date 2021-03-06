package Gui;

import Controller.PersoaneController;
import Model.Persoane;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class PersoaneFrame extends JFrame {

    private JList PersonsList;
    private JPanel mainPanel;
    private JTextField addText;
    private JButton addButton;
    private JButton loginButton;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private DefaultListModel<Persoane> model;
    private Persoane persoane;

    public PersoaneFrame() {

        model = new DefaultListModel<>();
        PersonsList.setModel(model);

/*       verifică dacă ȋn baza de date este o ȋntregistrare cu usernameul
        și parola introduse. Dacă persoana exista se deschide o fereastra de tip
        „Rezervări”, dacă nu se afișază mesajul „Username-ul sau parola gresita!”*/

        loginButton.addActionListener(ev -> configureLoginButton());


        //adăuga o nouă persoană ȋn baza de date.
        addButton.addActionListener(ev -> addPerson());

        PersonsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onMouseClickedForList(e);
            }
        });

        displayPersons();
        setContentPane(mainPanel);
        pack();
        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Person");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void onMouseClickedForList(MouseEvent e) {
        boolean isItemSelected = PersonsList.getSelectedValue() != null;
        if (isItemSelected && checkCredentials() &&
                e.getButton() == MouseEvent.BUTTON1 &&
                e.getClickCount() == 2) {
            Persoane selected = (Persoane) PersonsList.getSelectedValue();
            new FilmeFrame(selected);
        }
        if (isItemSelected &&
                e.getButton() == MouseEvent.BUTTON3 &&
                e.getClickCount() == 2) {
            Persoane selected = (Persoane) PersonsList.getSelectedValue();
            boolean rez = PersoaneController.getInstance()
                    .delete(selected.getId());
            if (rez) { //update lista
                displayPersons();
            }
        }
    }

    private void configureLoginButton() {
        loginButton.addActionListener(e -> {
            String name = usernameField.getText();
            String password = new String(this.passwordField.getPassword());
            Persoane persoane = new Persoane(0, name, password);
            boolean rez = PersoaneController.getInstance().create(persoane);
            if (checkCredentials()) {
                if (rez) {
                   displayPersons();
                } else {
                    JOptionPane.showMessageDialog(null, "configureLoginButton wrong!");
                    addText.setText("");
                }
            }
        });
    }

       /*Ȋn baza de date nu trebuie să existe doi utilizatori cu același nume, iar
       parola trebuie să se termine cu o cifră
       verifică dacă ȋn baza de date este o ȋntregistrare cu usernameul
       și parola introduse. Dacă persoana exista se deschide o fereastra de tip
       „Rezervări”, dacă nu se afișază mesajul „Username-ul sau parola gresita!”.*/

    protected void displayPersons() {
        List<Persoane> persoane = PersoaneController.getInstance().findAll();
        model.clear();
        persoane.forEach(model::addElement); //sau p -> model.addElement(p)
    }

    protected void addPerson() {
        String name = addText.getText();
        String password = new String(this.passwordField.getPassword());
        Persoane persoane = new Persoane(0, name, password);
        boolean rez = PersoaneController.getInstance().create(persoane);
        if (rez) {
            if (checkCredentials()){
            displayPersons();
        } else {
            JOptionPane.showMessageDialog(null, "Name already exist!");
            addText.setText("");
        }
    }}

    public boolean checkCredentials() {
        boolean check = false;
        String name = usernameField.getText();
        String password = new String(this.passwordField.getPassword());

        if (!authenticateUser(name, password)) {
            JOptionPane.showMessageDialog(null, "Invalid credentials: username null", "Mesaj", JOptionPane.ERROR_MESSAGE);
            usernameField.setText("");
            passwordField.setText("");
        }
        if (!isDigit(password)) {
            JOptionPane.showMessageDialog(null, "Invalid credentials: password without digit", "Mesaj", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
        if (!checkPerson(name)) {
            JOptionPane.showMessageDialog(null, "Invalid credentials: username already exist", "Mesaj", JOptionPane.ERROR_MESSAGE);
            usernameField.setText("");
        }else {
            System.out.println("Password ok");
            check = true;
        }
      return  check;

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

    static boolean checkPerson(String name) {
        boolean numberPresent = false;
        List<Persoane> persoane = PersoaneController.getInstance().findAll();
        for (Persoane persoane1 : persoane) {
            if (persoane1.getName().equalsIgnoreCase(name)) {

                numberPresent = true;
            }
        }
        return numberPresent;
    }

    static boolean authenticateUser(String userName, String password) {
        if (userName.isEmpty() || password.isEmpty() || userName == null) {
            return false;
        } else {
            return true;
        }
    }
}
