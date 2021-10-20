package Controller;

import Dao.FilmeDao;
import Model.Filme;

import java.util.List;

public class FilmeController {

    private static final class SingletonHolder {
        public static final FilmeController INSTANCE = new FilmeController();
    }

    private FilmeDao filmDao;

    private FilmeController() {
        filmDao = new FilmeDao(
                ConnectionManager.getInstance().getConnection()
        );
    }

    public static FilmeController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public List<Filme> findByPersoana(int persoanaId) {
        return filmDao.findByPersoana(persoanaId);
    }

    public boolean create(Filme film) {
        return filmDao.create(film);
    }

    public boolean delete(int idFilm) {
        return filmDao.delete(idFilm);
    }


}
