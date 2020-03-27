package com.usefi.tmdb.features.SearchActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.usefi.tmdb.R
import com.usefi.tmdb.base.BaseActivity
import com.usefi.tmdb.features.MovieDetailActivity.DetailActivity
import com.usefi.tmdb.pojo.Result
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity() {

    private val vm : SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val clickListener : (Int)->Unit={
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("itemId", it)
            startActivity(intent)
        }

        btnSearch.setOnClickListener {
            val searchQuery = edtSearch.text.toString()
            vm.getSearchQuery(searchQuery)
        }

        vm.getResultData().observe(this, androidx.lifecycle.Observer {
            val movieAdapter = movieRecyclerAdapter(it,clickListener)
            recycler.adapter = movieAdapter
        })


    }

}
