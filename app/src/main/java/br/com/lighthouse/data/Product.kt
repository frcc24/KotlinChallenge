package br.com.lighthouse.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import java.text.DateFormat
import kotlinx.android.parcel.Parcelize
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table" )
@Parcelize
data class Product (
    val productName: String,
    val productType: String,
    val quantity: Int,
    val image: String,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") val id: Int = 0

) : Parcelable {

    val createdDateFormatted: String
        get() = DateFormat.getDateInstance().format(created)
}