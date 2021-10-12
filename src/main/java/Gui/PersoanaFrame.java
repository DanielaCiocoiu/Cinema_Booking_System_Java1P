package Gui;

import Controller.PersoanaController;
import Model.Persoana;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PersoanaFrame extends JFrame {
    private JList list1;
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton adaugaButton;

    private DefaultListModel<Persoana> model;


    public PersoanaFrame(){

        model = new DefaultListModel<>();
        list1.setModel(model);

        afisPersoane();
        adaugaButton.addActionListener(ev -> adaugaPersoana());//cand apas button, iau numele din field, voi incerca sa creez o persoana, o adaug, daca reusesc ok, daca nu...eroare

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onMouseClickedForList(e);
            }
        });
    }
    private void onMouseClickedForList(MouseEvent e) {
        boolean isItemSelected = list1.getSelectedValue() != null;//daca in lista exista un element selectat...
//daca elementul este selectat si evenimentul = butonul 1 din stanga si daca nr de clickuri = 1, atunci deschid fereastra de filme
        if (isItemSelected &&
                e.getButton() == MouseEvent.BUTTON1 &&
                e.getClickCount() == 2) {

            Persoana selected = (Persoana) list1.getSelectedValue();

            new FilmFrame(selected);
        }

        //daca am doua click-uri ----sterg persoana

        if (isItemSelected &&
                e.getButton() == MouseEvent.BUTTON3 &&
                e.getClickCount() == 2) {

            Persoana selected = (Persoana) list1.getSelectedValue();
            boolean rez = PersoanaController.getInstance()
                    .delete(selected.getId());

            if (rez) { //update lista daca e cu succes
                afisPersoane();
            }
        }

    }

    private void afisPersoane(){
        List<Persoana> persoane = PersoanaController.getInstance().findAll();
        model.clear();
        persoane.forEach(model::addElement); //sau p -> model.addElement(p)
    }

    private void adaugaPersoana(){
        String nume = textField1.getText();
        Persoana p = new Persoana(0, nume);

        boolean rez = PersoanaController.getInstance().create(p);
        if (rez){
            afisPersoane();
        }else {
            JOptionPane.showMessageDialog(null, "Numele exista deja!");
            textField1.setText("");
        }
    }
}
