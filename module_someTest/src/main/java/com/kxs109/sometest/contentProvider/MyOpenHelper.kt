package com.kxs109.sometest.contentProvider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyOpenHelper(
    context: Context,
    name: String,
    cursorFactory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(
    context, name, cursorFactory, version
) {
    constructor(context: Context) : this(context, "person.db", null, 1)

    override fun onCreate(db: SQLiteDatabase?) {
        val sql =
            "CREATE TABLE demo(_id integer not null primary key autoincrement, name text not null);"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}