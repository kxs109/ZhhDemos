package com.kxs109.viewlearn

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TestContentProviderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_learn_activity_test_content_provider)

        /* "sddd".sumBy {
             it.toInt()
         }*/

    }

    fun insertData(view: View) {
        val values = ContentValues()
        values.put("name", "史蒂夫")
        contentResolver.insert(Uri.parse("content://com.kxs109.sometest.person"), values)
        values.put("name", "的风格吧")
        contentResolver.insert(Uri.parse("content://com.kxs109.sometest.person"), values)
        values.put("name", "热人不够")
        contentResolver.insert(Uri.parse("content://com.kxs109.sometest.person"), values)
        values.put("name", "品牌覅")
        contentResolver.insert(Uri.parse("content://com.kxs109.sometest.person"), values)
    }

    fun queryData(view: View) {
        val cursor = contentResolver.query(
            Uri.parse("content://com.kxs109.sometest.person"),
            null,
            null,
            null,
            null
        )
        while (cursor != null && cursor?.moveToNext()) {
            println(cursor.getString(1))
        }
    }

    fun deleteData(view: View) {}
    fun modifyData(view: View) {}
}
