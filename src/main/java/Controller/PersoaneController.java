package Controller;

import Dao.PersoaneDao;
import Model.Persoane;

import java.util.List;
import java.util.Optional;

// interfata grafica va expune persoanele obtinute din clasa controller
public class PersoaneController {

    private static final class SingletonHolder {
        public static final PersoaneController INSTANCE = new PersoaneController();
    }

    private PersoaneDao persoanaDao;
    private Optional<Persoane> loggedPerson;

    public PersoaneController() {
        persoanaDao = new PersoaneDao(
                ConnectionManager.getInstance().getConnection()
        );
    }

    public static PersoaneController getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public List<Persoane> findAll() {
        return persoanaDao.findAll();
    }

    public boolean delete(int id) {
        return persoanaDao.delete(id);
    }

    public boolean create(Persoane p) {
        Optional<Persoane> persoanaOptional = persoanaDao.findByNume(new Persoane(p.getName(), p.getPassword() ));
        if (persoanaOptional.isEmpty()) {
            return persoanaDao.create(p);
        } else {
            return false;
        }
    }
}
