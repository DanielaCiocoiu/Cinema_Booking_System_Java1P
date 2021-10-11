package Controller;

import Dao.FilmDao;
import Model.Film;

import java.util.List;

public class FilmeController {

    private static final class SingletonHolder {
        public static final FilmeController INSTANCE = new FilmeController();
    }

    private FilmDao filmDao;

    private FilmeController() {
        filmDao = new FilmDao(
                ConnectionManager.getInstance().getConnection()
        );
    }

    public static FilmeController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public List<Film> findByPersoana(int persoanaId) {
        return filmDao.findByPersoana(persoanaId);
    }

    public boolean create(Film  film) {
        return filmDao.create(film);
    }
}
