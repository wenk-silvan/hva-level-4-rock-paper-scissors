package ch.wenksi.madlevel4task2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameResultDao {
    @Query("SELECT * FROM statisticTable")
    suspend fun getStatistics(): List<GameResult>

    @Insert
    suspend fun insertGameResult(result: GameResult)

    @Query("DELETE FROM statisticTable")
    suspend fun deleteStatistics()
}