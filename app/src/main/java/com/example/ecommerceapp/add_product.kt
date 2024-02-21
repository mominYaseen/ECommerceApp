package com.example.ecommerceapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AbsSeekBar
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import java.util.UUID

class add_product : AppCompatActivity() {
    private lateinit var productImgPrev : ImageView
    private lateinit var productName :EditText
    private lateinit var productPrice:EditText
    private lateinit var productDis :EditText
    private lateinit var productRating: EditText
    private lateinit var selectProductBtn : Button
    private lateinit var uploadProduct:Button
    private lateinit var databaseRef : DatabaseReference
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        //intializing
        productImgPrev = findViewById(R.id.product_image_preview)
        productName = findViewById(R.id.product_name)
        productPrice = findViewById(R.id.product_price)
        productDis = findViewById(R.id.product_discription)
        productRating = findViewById(R.id.product_rating)
        selectProductBtn = findViewById(R.id.select_product_btn)
        uploadProduct = findViewById(R.id.upload_product_btn)
        progressBar = findViewById(R.id.progress_bar)

        databaseRef = Firebase.database.getReference("Products")

        selectProductBtn.setOnClickListener {
            val galIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galIntent,101)
        }

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode== RESULT_OK){
            val uri = data?.data
            productImgPrev.setImageURI(data?.data)

            uploadProduct.setOnClickListener {
              progressBar.visibility = View.VISIBLE
                val productName = productName.text.toString()
                val productPrice = productPrice.text.toString()
                val productDis = productDis.text.toString()
                val productRating = productRating.text.toString()

                val fileName = UUID.randomUUID().toString() +".jpg"
                val storageRef = FirebaseStorage.getInstance().reference.child("ProductImages/$fileName")
                //getting the link of the image uploaded to the FIREBASE

                storageRef.putFile(uri!!).addOnSuccessListener {
                    val result = it.metadata?.reference?.downloadUrl
                       result?.addOnSuccessListener {
                           upload(productName,productPrice,productDis,productRating,it.toString())
                       }
                }
                startActivity(
                    Intent(this,MainActivity::class.java)
                )

             }

        }
    }

    private fun upload(
        productName: String,
        productPrice: String,
        productDis: String,
        productRating: String,
        link: String
    ) {


        val product = Product(
            productName = productName,
            productPrice = productPrice,
            productDis = productDis,
            productRating = productRating,
            productImg = link

        )
        databaseRef.child(productName).setValue(product)
        progressBar.visibility = View.GONE
    }
}






















