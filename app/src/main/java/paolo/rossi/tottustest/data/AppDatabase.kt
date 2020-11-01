package paolo.rossi.tottustest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import paolo.rossi.tottustest.data.daos.MemberDAO
import paolo.rossi.tottustest.data.daos.TeamDAO
import paolo.rossi.tottustest.data.daos.UserDAO
import paolo.rossi.tottustest.data.models.Member
import paolo.rossi.tottustest.data.models.Team
import paolo.rossi.tottustest.data.models.User


@Database(
    entities = [
        // Synchronizable
        Member::class,
        Team::class,
        User::class
    ],
    version = 1,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        val DATABASE_NAME = "TOTTUS_DATABASE"
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }


    // synchronizable with remote database
    abstract fun memberDAO(): MemberDAO
    abstract fun teamDAO(): TeamDAO
    abstract fun userDAO(): UserDAO
}