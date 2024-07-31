package com.lvlconsulting.actividad1.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lvlconsulting.actividad1.model.Project
import com.lvlconsulting.actividad1.model.User

@Database(entities = [User::class, Project::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun projectDao(): ProjectDao
}