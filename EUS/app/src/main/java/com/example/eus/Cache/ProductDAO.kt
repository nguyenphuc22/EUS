package com.example.eus.Cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface ProductDAO {
    @Insert
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("Select * From product")
    fun getAll() : LiveData<List<Product>>

    @Query("DELETE FROM product")
    fun deleteAll()
}