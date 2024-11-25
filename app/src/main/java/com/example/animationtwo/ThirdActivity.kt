package com.example.animationtwo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animationtwo.SecondActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ThirdActivity : AppCompatActivity() {

    private var adapter: CustomAdapter? = null
    private var products: MutableList<Product> = mutableListOf()

    private lateinit var toolbarTA: Toolbar
    private lateinit var recyclerTA: RecyclerView
    private lateinit var floatingActionButtonTA: FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarTA = findViewById(R.id.toolbarTA)
        recyclerTA = findViewById(R.id.recyclerTA)
        floatingActionButtonTA = findViewById(R.id.floatingActionButtonTA)

        setSupportActionBar(toolbarTA)
        title = "Магазин"

        products = intent.getSerializableExtra("productsUpdate") as MutableList<Product>
        recyclerTA.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(products)
        recyclerTA.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        recyclerTA.setHasFixedSize(true)
        adapter?.setOnProductClickListener(object :
            CustomAdapter.OnProductClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onProductClick(product: Product, position: Int) {
                val dialogBuilder = AlertDialog.Builder(this@ThirdActivity)
                dialogBuilder.setTitle("Внимание!")
                dialogBuilder.setMessage("выберите действие:")
                dialogBuilder.setPositiveButton("Удалить из корзины") { _, _ ->
                    products.remove(product)
                    adapter?.notifyDataSetChanged()
                }
                dialogBuilder.setNegativeButton("Отмена") { dialog, _ ->
                    dialog.cancel()
                }
                dialogBuilder.create().show()
            }
        })
        floatingActionButtonTA.setOnClickListener{
            floatingActionButtonTA.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.start()
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("products", products as ArrayList<Product>)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ta, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.exitTAMenu -> {
                finishAffinity()
                Toast.makeText(applicationContext, "Программа завершена", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}