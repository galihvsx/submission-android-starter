package com.gverse.dcnews

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.imageview.ShapeableImageView

class NewsDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NEWS = "news_item"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val topBar: MaterialToolbar = findViewById(R.id.top_app_bar)
        topBar.setNavigationOnClickListener {
            finish()
        }


        val news = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<News>(EXTRA_NEWS, News::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<News>(EXTRA_NEWS)
        }

        val tvTitle: TextView = findViewById(R.id.news_detail_title)
        val tvDate: TextView = findViewById(R.id.news_detail_date)
        val tvDescription: TextView = findViewById(R.id.news_detail_description)
        val tvSource: TextView = findViewById(R.id.news_detail_source)
        val newsThum: ShapeableImageView = findViewById(R.id.news_detail_image)

        tvTitle.text = news?.title
        tvDate.text = news?.date
        tvDescription.text = news?.description
        tvSource.text = "Liputan6"
        newsThum.setImageResource(news?.thumbnail!!)

        topBar.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.share_action -> {
                    val shareTitle = news.title ?: "Berita"
                    val shareDescription = news.description ?: "Deskripsi tidak tersedia"

                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "$shareTitle\n\n$shareDescription\n\nBaca lebih lanjut di: Liputan6")
                        type = "text/plain"
                    }

                    startActivity(Intent.createChooser(shareIntent, "Bagikan berita melalui"))
                    true
                }
                else -> false
            }
        }
    }
}