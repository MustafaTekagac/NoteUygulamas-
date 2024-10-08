package com.example.notetakingapp

import androidx.room.*
import com.example.notetakingapp.BaseEntity

@Dao
abstract class BaseDao<T : BaseEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: T): Long

    @Update
    abstract suspend fun update(entity: T)

    @Delete
    abstract suspend fun delete(entity: T)
}
