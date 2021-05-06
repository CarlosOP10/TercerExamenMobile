package edu.bo.tercerexamen

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_book.view.*
import kotlinx.android.synthetic.main.book_row.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookListRv(val list: List<Book>, val applicationContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
    val repository = BookRepository(bookDao)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.book_row,parent,false)
        return BookListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val book=list.get(position)
        holder.itemView.nameBook.text=book.title
        holder.itemView.pagesBook.text=book.pages.toString()
        holder.itemView.descriptionBook.text=book.description
        holder.itemView.authorBook.text=book.author
        holder.itemView.dateBook.text=book.date
        holder.itemView.isbnBook.text=book.isbn
        Picasso.get().load(book.photoUrl).into(holder.itemView.imageBook)
        holder.itemView.deleteButton.setOnClickListener {
            GlobalScope.launch {
                repository.deleteBook(book)
            }
            val context=holder.itemView.context
            val intent = Intent( context, MainActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.count()
    }

    class BookListViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    }
}
