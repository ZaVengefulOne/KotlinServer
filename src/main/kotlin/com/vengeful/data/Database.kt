package com.vengeful.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import java.io.File

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): UserDAO
}

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("DELETE FROM users WHERE login = :login")
    suspend fun delete(login: String)

    @Query("SELECT * from users WHERE login = :login")
    suspend fun getItem(login: String): User

    @Query("SELECT * from users")
    suspend fun getAllItems(): List<User>

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val login: String,
    val email: String,
    val password: String,
    val token: String
)

fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath,
    )
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .fallbackToDestructiveMigration(true)
        .build()
}