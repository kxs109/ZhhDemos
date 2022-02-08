package com.kxs109.testSpan

/*
 * @Author zhh
 * @Date 2022/2/7
 * @Des  特殊富文本内容 书籍、或其他
 */
data class SpecialContent(
    var bookId: String = "",
    var bookTitle: String = "",
    var start: Int = 0,
    var end: Int = 0
)