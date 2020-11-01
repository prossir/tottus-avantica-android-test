package paolo.rossi.tottustest.data.repositories

import android.content.Context
import paolo.rossi.tottustest.data.AppDatabase
import paolo.rossi.tottustest.data.daos.UserDAO
import paolo.rossi.tottustest.data.models.User

/*
public class AppointmentRepository {
    private Application application;
    private AppointmentEventPersistenceDAO appointment_event_persistenceDAO;
    private AppointmentPersistenceDAO appointment_persistenceDAO;
    private PhysicalTestPersistenceDAO physical_test_persistenceDAO;
    private VisualRecordPersistenceDAO visual_record_persistenceDAO;

    public AppointmentRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.application = application;
        appointment_event_persistenceDAO = db.appointment_event_persistenceDAO();
        appointment_persistenceDAO = db.appointment_persistenceDAO();
        physical_test_persistenceDAO = db.physical_test_persistenceDAO();
        visual_record_persistenceDAO = db.visual_record_persistenceDAO();
    }

* */

class UserRepository(private val context: Context) {
    var userDAO: UserDAO

    init {
        val db = AppDatabase.getInstance(context)
        userDAO = db.userDAO()
    }


    fun retrieveIfLoggedIn() : User? {
        return userDAO.retrieveLoggedIn()
    }


    fun tryLoginAndRetrieve(email: String, password: String): User? {
        val user = userDAO.retrieveByEmailAndPassword(email, password)
        if (user != null) {
            userDAO.setLoggedIn(1, user.id)
        }
        return user
    }

    fun create(name: String, last_name: String, email: String, password: String): User? {
        val user = User(name = name, last_name = last_name, email = email, password = password, created_at = System.currentTimeMillis(), is_logged = false, status = 1)
        userDAO.insert(user)
        return user
    }


}