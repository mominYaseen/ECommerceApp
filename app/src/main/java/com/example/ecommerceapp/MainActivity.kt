package com.example.ecommerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var fab:FloatingActionButton
    private lateinit var rv:RecyclerView
    private lateinit var firebaseReference: DatabaseReference

    private lateinit var productAdapter: ProductAdapter
    private val listOfProducts = mutableListOf<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab= findViewById(R.id.fab)
        firebaseReference = FirebaseDatabase.getInstance().getReference("Products")
        rv=findViewById(R.id.rv)

        fab.setOnClickListener{
            val intent = Intent(this,login_page::class.java)
            startActivity(intent)
            Toast.makeText(this, "fab pressed", Toast.LENGTH_SHORT).show()
        }

        firebaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                listOfProducts.clear()
                for (dataSnapShot in snapshot.children){
                    val user = dataSnapShot.getValue(Product::class.java)
                    listOfProducts.add(user!!)
                }
                productAdapter = ProductAdapter(listOfProducts,this@MainActivity)
                rv.adapter = productAdapter
                rv.layoutManager =GridLayoutManager(this@MainActivity,2)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }





}