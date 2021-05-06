package edu.bo.tercerexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_book.*

import kotlinx.android.synthetic.main.activity_edit_book.*
import kotlinx.android.synthetic.main.activity_edit_book.btnCancelEdit
import kotlinx.android.synthetic.main.activity_edit_book.btnEditBook
import kotlinx.android.synthetic.main.activity_edit_book.txtAddAuthor
import kotlinx.android.synthetic.main.activity_edit_book.txtAddDate
import kotlinx.android.synthetic.main.activity_edit_book.txtAddDescription
import kotlinx.android.synthetic.main.activity_edit_book.txtAddPages
import kotlinx.android.synthetic.main.activity_edit_book.txtAddTitle
import kotlinx.android.synthetic.main.activity_edit_book.txtAddUrl
import kotlinx.android.synthetic.main.activity_edit_book.txtIsbnAdd
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        var bundle=intent.extras
        var atributes = bundle?.getStringArray("book")

        textViewId.setText(atributes?.get(0))
        txtAddTitle.setText(atributes?.get(1))
        txtAddAuthor.setText(atributes?.get(2))
        txtAddDate.setText(atributes?.get(4))
        txtAddPages.setText(atributes?.get(7))
        txtAddUrl.setText(atributes?.get(5))
        txtIsbnAdd.setText(atributes?.get(3))
        txtAddDescription.setText(atributes?.get(6))

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
                if (atributes?.get(0)!=null ){
                    var book:Book=repository.findById(atributes?.get(0))
                    book.title=title
                    book.author=author
                    book.date=date
                    book.pages=pages
                    book.photoUrl=url
                    book.isbn=isbn
                    book.description=description
                    repository.uptateBook(book)
                }

            }
            val intent: Intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}