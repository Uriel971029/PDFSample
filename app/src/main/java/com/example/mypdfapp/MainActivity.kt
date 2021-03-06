package com.example.mypdfapp

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loader : ProgressBar = findViewById(R.id.loader)
        val pdfViewer : ImageView = findViewById(R.id.pdfPage)
        val mainRepository = MainRepository()
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.requestPDF(mainRepository, "http://192.168.0.4:8888/contrato_sample.pdf",
            "libe291209", this@MainActivity)

        viewModel.isLoading.observe(this, {
            if(it){
                loader.visibility = View.VISIBLE
            }else{
                loader.visibility = View.GONE
            }
        })

        viewModel.pdfPage.observe(this, {
            pdfViewer.setImageBitmap(it)
        })
    }
}