package Gui;

import Controller.FilmeController;
import Model.Filme;
import Model.Persoane;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FilmeFrame extends JFrame {

    private JPanel mainPanel;
    private JList listaFilme;
    private JList listaPersoane;

    private JButton rezervareButton;

    private JTextField filmField;

    private JLabel filmLabel;
    private JLabel salaLabel;
    private JComboBox comboSala;

    private Persoane persoane;

    private DefaultListModel<Filme> model;

    public FilmeFrame(Persoane persoane) {
        this.persoane = persoane;

        model = new DefaultListModel<>();
        listaFilme.setModel(model);

       afiseazaFilm();

        rezervareButton.addActionListener(ev -> adaugaFilm());

        comboSala.addItem(1);
        comboSala.addItem(2);
        comboSala.addItem(3);
        comboSala.addItem(4);
        comboSala.addItem(5);
        comboSala.addItem(6);
        comboSala.addItem(7);
        comboSala.addItem(8);
        comboSala.addItem(9);
        comboSala.setSelectedItem(1);


        setTitle("Rezervari");
        setContentPane(mainPanel);
        pack();
        setSize(500, 600);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    protected void adaugaFilm() {
        String nume = filmField.getText();
        String salaNr = String.valueOf(comboSala.getSelectedItem());
        Filme film = new Filme(
                0, nume, salaNr, persoane.getId());

        boolean rez = FilmeController.getInstance().create(film);
        if (rez) {
            afiseazaFilm();
        } else {
            JOptionPane.showMessageDialog(null, "Eroare");
        }

        filmField.setText("");
    }

    private void afiseazaFilm() {
            List<Filme> film = FilmeController.getInstance().findByPersoana(persoane.getId());
            model.clear();
            film.forEach(model::addElement);
    }

}
