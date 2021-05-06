package edu.bo.tercerexamen

class BookRepository(private val bookDao: IBookDao) {

    suspend fun insert(book: Book) {
        bookDao.insert(book)
    }

    fun getListBooks(): List<Book> {
        return bookDao.getList()
    }

    fun deleteBook(book: Book){
        bookDao.deleteBook(book)
    }

    fun uptateBook(book: Book){
        bookDao.updateBook(book)
    }
}