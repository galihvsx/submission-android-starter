package com.gverse.dcnews

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var rvNews: RecyclerView
    private var list = ArrayList<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvNews = findViewById(R.id.rv_news)
        rvNews.setHasFixedSize(true)

        list.addAll(getNewsList())
        showRecyclerList()

        val toolbar: MaterialToolbar = findViewById(R.id.top_app_bar)
        setSupportActionBar(toolbar)
        toolbar.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.about_page -> {
                    val intent = Intent(this@MainActivity, AboutPageActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_top_menu, menu)
        return true
    }
    private fun showRecyclerList() {
        rvNews.layoutManager = LinearLayoutManager(this)
        val listNewsAdapter = NewsListAdapter(list)
        rvNews.adapter = listNewsAdapter
    }

    private fun getNewsList(): ArrayList<News> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataDescription = resources.getStringArray(R.array.data_desc)
        val dataDate = resources.getStringArray(R.array.data_date)
        val dataThumbnail = resources.obtainTypedArray(R.array.data_thumbnail)

        val newsListData = ArrayList<News>()
        for(i in dataTitle.indices) {
            val news = News(dataTitle[i], dataDescription[i], dataDate[i], dataThumbnail.getResourceId(i, -1))
            newsListData.add(news)
        }
        return newsListData
    }

}
