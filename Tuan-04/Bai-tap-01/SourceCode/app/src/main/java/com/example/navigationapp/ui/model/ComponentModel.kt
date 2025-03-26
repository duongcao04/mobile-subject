package com.example.navigationapp.ui.model

data class Quote(
    val id: Int,
    val text: String,
    val author: String
)

// Khai báo danh sách quotes toàn cục
val quotes = listOf(
    Quote(1, "The only way to do great work is to love what you do.", "Steve Jobs"),
    Quote(
        2,
        "Success is not the key to happiness. Happiness is the key to success.",
        "Albert Schweitzer"
    ),
    Quote(3, "Do what you can, with what you have, where you are.", "Theodore Roosevelt"),
    Quote(4, "Believe you can and you're halfway there.", "Theodore Roosevelt"),
    Quote(5, "It does not matter how slowly you go as long as you do not stop.", "Confucius"),
    Quote(6, "Act as if what you do makes a difference. It does.", "William James"),
    Quote(7, "Opportunities don't happen. You create them.", "Chris Grosser"),
    Quote(8, "Don't watch the clock; do what it does. Keep going.", "Sam Levenson"),
    Quote(9, "Quality means doing it right when no one is looking.", "Henry Ford"),
    Quote(10, "I have not failed. I've just found 10,000 ways that won't work.", "Thomas Edison")
)