package ch.wenksi.madlevel4task2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameResult::class], version = 1, exportSchema = false)
abstract class GameRoomDatabase : RoomDatabase() {
    abstract fun gameResultDao(): GameResultDao

    companion object {
        private const val DATABASE_NAME = "GAME_ROCK_PAPER_SCISSORS_DATABASE"

        @Volatile
        private var gameRoomDatabase: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase? {
            if (gameRoomDatabase == null) {
                synchronized(GameRoomDatabase::class.java) {
                    gameRoomDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        GameRoomDatabase::class.java, DATABASE_NAME
                    ).build()
                }
            }
            return gameRoomDatabase
        }
    }
}