package paolo.rossi.tottustest.ui.activities.account_creation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import paolo.rossi.tottustest.data.models.User
import paolo.rossi.tottustest.data.repositories.UserRepository


class AccountCreationViewModel(application: Application)
    : AndroidViewModel(application) {

    var user_repository: UserRepository

    init {
        user_repository = UserRepository(application)
    }


    fun createUser(name: String, last_name: String, email: String, password: String) : User? {
        return user_repository.create(name, last_name, email, password)
    }
}