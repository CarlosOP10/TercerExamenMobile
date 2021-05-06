package edu.bo.tercerexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        fun refreshActivity(){
//            val refresh: Intent   = Intent(this,MainActivity::class.java);
//            startActivity(refresh)
//        }
        GlobalScope.launch {
            val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
            val repository = BookRepository(bookDao)
//            repository.insert(Book("Harry Potter","AF22","J.K. Rowling","12/02/2018",200,"Buen Libro","https://wl-genial.cf.tsp.li/resize/728x/jpg/fb1/d1b/28cce45b038d6baddba3db025d.jpg"))
//            repository.insert(Book("El Señor de los Anillos","BF67","J.R.R. Tolkien","23/09/1999",190,"Un buen libro","https://i2.wp.com/elanillounico.com/wp-content/uploads/2020/01/Pases-trilogia-extendida-El-Se%C3%B1or-de-los-Anillos-Barcelona-copia.jpg?w=660&ssl=1"))
//            repository.insert(Book("El Código da Vinci ","CJ65","Paulo Coelho","07/10/2010",230,"Buenisimo","https://m.media-amazon.com/images/I/51vC0FsLN6L._SY346_.jpg"))
            val list = repository.getListBooks()
            list.forEach {
                Log.d("DBTEST","Id book = ${it.id}, Title: ${it.title}, Pages: ${it.pages},Editorial: ${it.isbn}, Author: ${it.author}")
            }
            val adLayoutManager= LinearLayoutManager(applicationContext)
            adLayoutManager.orientation= LinearLayoutManager.VERTICAL

            bookRecyclerView.layoutManager=adLayoutManager
            bookRecyclerView.adapter=BookListRv(list,applicationContext )
        }
        addBook.setOnClickListener {
            val intent: Intent = Intent (this, AddBookActivity::class.java)
            startActivity(intent)
        }
    }
}