package paolo.rossi.tottustest.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class Team(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "catchphrase") val catchphrase: String,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "created_at") val created_at: String,
    @ColumnInfo(name = "user_id") val user_id: Long
)