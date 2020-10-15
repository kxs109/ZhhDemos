package com.kxs109.sometest.contentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log
import android.widget.Toast

class PersonContentProvider : ContentProvider() {
    lateinit var db: SQLiteDatabase
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.d("TAG", "insert: "+values.toString())
        db.insert("demo", null, values)
        return uri
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
       val cursor= db.query("demo",projection,selection,selectionArgs,null,null, sortOrder,null)
        return cursor
    }

    override fun onCreate(): Boolean {
        val helper = MyOpenHelper(context!!)
        db = helper.writableDatabase
        return false
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return db.version
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }
}