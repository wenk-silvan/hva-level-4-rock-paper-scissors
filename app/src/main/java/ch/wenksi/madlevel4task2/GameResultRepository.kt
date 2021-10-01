package ch.wenksi.madlevel4task2

import android.content.Context

class GameResultRepository(context: Context) {
    private var dao: GameResultDao

    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        dao = gameRoomDatabase!!.gameResultDao()
    }

    suspend fun getStatistics(): List<GameResult> = dao.getStatistics()

    suspend fun insertGameResult(gameResult: GameResult) = dao.insertGameResult(gameResult)

    suspend fun deleteStatistics() = dao.deleteStatistics()
}