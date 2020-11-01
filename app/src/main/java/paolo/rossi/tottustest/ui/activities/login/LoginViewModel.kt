package paolo.rossi.tottustest.ui.activities.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import paolo.rossi.tottustest.data.models.User
import paolo.rossi.tottustest.data.repositories.UserRepository


class LoginViewModel(application: Application)
    : AndroidViewModel(application) {

    private var user_repository: UserRepository = UserRepository(application)


    fun tryLogin(email: String, password: String) : User? {
        return user_repository.tryLoginAndRetrieve(email, password)
    }
}