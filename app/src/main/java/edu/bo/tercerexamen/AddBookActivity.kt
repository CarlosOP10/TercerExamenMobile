package edu.bo.tercerexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_book.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        btnCancelEdit.setOnClickListener {
            onBackPressed()
        }

        btnEditBook.setOnClickListener {
            var title=txtAddTitle.text.toString()
            var author=txtAddAuthor.text.toString()
            var date=txtAddDate.text.toString()
            var pages=txtAddPages.text.toString().toInt()
            var url=txtAddUrl.text.toString()
            var isbn=txtIsbnAdd.text.toString()
            var description=txtAddDescription.text.toString()

            GlobalScope.launch {
                val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
                val repository = BookRepository(bookDao)
                repository.insert(Book(title,isbn,author,date,pages,description,url))
            }
            val intent: Intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}