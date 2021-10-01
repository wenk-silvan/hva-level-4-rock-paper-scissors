package ch.wenksi.madlevel4task2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "statisticTable")
data class GameResult(
    @ColumnInfo(name = "result")
    val result: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long,

    @ColumnInfo(name = "handPlayer")
    val playerHand: String,

    @ColumnInfo(name = "handComputer")
    val computerHand: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)