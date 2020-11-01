package paolo.rossi.tottustest.data.repositories

import android.content.Context
import paolo.rossi.tottustest.data.AppDatabase
import paolo.rossi.tottustest.data.daos.UserDAO
import paolo.rossi.tottustest.data.models.User


class UserRepository(context: Context) {

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

    fun logout(): Int {
        userDAO.logout()
        if (userDAO.retrieveLoggedIn() == null) {
            return 1
        } else {
            return 0
        }
    }


}