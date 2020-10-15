// BookControlAIDL.aidl
package com.kxs109.sometest;

// Declare any non-default types here with import statements
import com.kxs109.sometest.Book;
interface BookControlAIDL {
    List<Book> getBookList();
    void addBook(inout Book book);
}
