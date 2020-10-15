package com.kxs109.sometest.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.kxs109.sometest.Book
import com.kxs109.sometest.BookControlAIDL

class AIDLService:Service() {
    private val mBookList = arrayListOf<Book>()
    override fun onCreate() {
        super.onCreate()
        initData()
    }

    private fun initData() {
        mBookList.add(Book("羊脂球"))
        mBookList.add(Book("海底一万年"))
        mBookList.add(Book("悲惨世界"))
        mBookList.add(Book("算法导论"))
        mBookList.add(Book("第一行代码"))
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }
   private val binder = object :BookControlAIDL.Stub(){
        override fun addBook(book: Book?) {
            println("添加新书本：$book")
            mBookList.add(book!!)
        }

        override fun getBookList(): ArrayList<Book> {
            return mBookList
        }
    }
}