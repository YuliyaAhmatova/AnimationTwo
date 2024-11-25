package com.example.animationtwo

class ProductDataBase {
    companion object {
        val products = mutableListOf(
            Product("Молочный коктейль",
                "169,99 ₽",
                R.drawable.koktel),
            Product("Сахар",
                "99,99 ₽",
                R.drawable.sahar),
            Product("Молоко",
                "149,99 ₽",
                R.drawable.moloko)
        )
    }
}