package com.example.animationtwo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondActivity : AppCompatActivity() {

    private var adapter: CustomAdapter? = null
    private var productsUpdate: MutableList<Product>? = mutableListOf()
    private var products: MutableList<Product> = ProductDataBase.products

    private lateinit var toolbarSA: Toolbar
    private lateinit var recyclerSA: RecyclerView
    private lateinit var floatingActionButtonSA: FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolbarSA = findViewById(R.id.toolbarSA)
        recyclerSA = findViewById(R.id.recyclerSA)
        floatingActionButtonSA = findViewById(R.id.floatingActionButtonSA)

        setSupportActionBar(toolbarSA)
        title = "Магазин"

        recyclerSA.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(products)
        recyclerSA.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        recyclerSA.setHasFixedSize(true)
        adapter?.setOnProductClickListener(object :
            CustomAdapter.OnProductClickListener {
            override fun onProductClick(product: Product, position: Int) {
                val dialogBuilder = AlertDialog.Builder(this@SecondActivity)
                dialogBuilder.setTitle("Внимание!")
                dialogBuilder.setMessage("выберите действие:")
                dialogBuilder.setPositiveButton("В корзину") { _, _ ->
                    productsUpdate?.add(product)
                    Toast.makeText(applicationContext, "Продукт в корзине", Toast.LENGTH_LONG).show()
                }
                dialogBuilder.setNegativeButton("Отмена") { dialog, _ ->
                    dialog.cancel()
                }
                dialogBuilder.create().show()
            }
        })

        floatingActionButtonSA.setOnClickListener{
            floatingActionButtonSA.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.start()
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("productsUpdate", productsUpdate as ArrayList<Product>)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sa, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.exitSAMenu -> {
                finishAffinity()
                Toast.makeText(applicationContext, "Программа завершена", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}