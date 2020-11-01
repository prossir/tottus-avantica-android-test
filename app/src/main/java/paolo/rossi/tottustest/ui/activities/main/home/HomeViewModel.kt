package paolo.rossi.tottustest.ui.activities.main.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import paolo.rossi.tottustest.data.models.User
import paolo.rossi.tottustest.data.repositories.UserRepository


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var user_repository: UserRepository = UserRepository(application)

    var user : User = user_repository.retrieveIfLoggedIn()!!
}