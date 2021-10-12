package Gui;

import Controller.FilmeController;
import Model.Film;
import Model.Persoana;

import javax.swing.*;
import java.util.List;

public class FilmFrame extends JFrame{
    private JList list1;
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton adaugaButton;

    private Persoana persoana;

    private DefaultListModel<Film> model;

    public FilmFrame(Persoana persoana) {
        this.persoana = persoana;

        model = new DefaultListModel<>();
        list1.setModel(model);

        afisFilme();
        adaugaButton.addActionListener(ev -> adaugaFilm());

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void afisFilme() {
        List<Film> filme = FilmeController.getInstance()
                .findByPersoana(persoana.getId());

        model.clear();
        filme.forEach(model::addElement);
    }

    private void adaugaFilm() {
        String nume = textField1.getText();

        Film film = new Film(
                0,
                nume,
                persoana.getId()
        );

        boolean rez = FilmeController.getInstance()
                .create(film);

        if (rez) {
            afisFilme();
        } else {
            JOptionPane.showMessageDialog(null, "Eroare");
        }

        textField1.setText("");
    }
}
