package paolo.rossi.tottustest.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "member")
data class Member(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "created_at") val created_at: Long,
    @ColumnInfo(name = "team_id") val team_id: Long,
) : Parcelable