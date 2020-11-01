package paolo.rossi.tottustest.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "team")
data class Team(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "catchphrase") val catchphrase: String,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "created_at") val created_at: Long,
    @ColumnInfo(name = "user_id") val user_id: Long
) : Parcelable {
    @Ignore var members_count : Int = 0
}