package com.kxs109.viewlearn

import android.content.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.kxs109.sometest.Book
import com.kxs109.sometest.BookControlAIDL

class TestAIDLActivity : AppCompatActivity() {
    lateinit var bookControl: BookControlAIDL
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_learn_activity_test_aidl)
        val intent = Intent()
        intent.setPackage("com.kxs109.sometest")
        intent.action = "com.kxs109.sometest"
        bindService(intent, object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {

            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                bookControl = BookControlAIDL.Stub.asInterface(service)
            }
        }, Context.BIND_AUTO_CREATE)
    }

    fun inOutAdd(view: View) {
        bookControl.addBook(Book("java基础教学"))
    }

    fun inAdd(view: View) {}
    fun outAdd(view: View) {}
    fun getBookList(view: View) {
        println(bookControl.bookList)
    }


}
