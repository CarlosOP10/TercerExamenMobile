package edu.bo.tercerexamen

import androidx.room.*

@Dao
interface IBookDao {

    @Query("SELECT * FROM book_table")
    fun getList(): List<Book>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book:Book)

    @Query("DELETE FROM book_table")
    suspend fun deleteAll()

    @Delete
    fun deleteBook( book: Book)

    @Update
    fun updateBook(book: Book)

    @Query("SELECT * FROM book_table WHERE id LIKE :id LIMIT 1")
    fun findById(id: String): Book

}