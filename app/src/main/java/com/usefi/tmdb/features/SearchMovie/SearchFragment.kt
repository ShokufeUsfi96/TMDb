package com.usefi.tmdb.features.SearchMovie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding3.widget.textChanges
import com.usefi.tmdb.R
import com.usefi.tmdb.base.BaseFragment
import com.usefi.tmdb.features.DetailMovie.DetailActivity
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.function.Consumer

class SearchFragment : BaseFragment() {

    private val vm : SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clickListener : (Int)->Unit={
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra("itemId", it)
            startActivity(intent)
        }

        edtSearch.setOnFocusChangeListener{ view: View, b: Boolean ->
            layoutSearchIcon.visibility = View.GONE
        }
        edtSearch.textChanges()
            .subscribe({
                //layoutSearchIcon.visibility = View.GONE
                val searchQuery = it.toString()
                vm.getSearchQuery(searchQuery)
            },{
                Log.d("TAG", "query was not received!")
            })
        btnSearch.setOnClickListener {
            layoutSearchIcon.visibility = View.GONE
            val searchQuery = edtSearch.text.toString()
            vm.getSearchQuery(searchQuery)
        }

        vm.getResultData().observe(this, androidx.lifecycle.Observer {
            val movieAdapter = movieRecyclerAdapter(it,clickListener)
            recycler.adapter = movieAdapter
        })
    }
}