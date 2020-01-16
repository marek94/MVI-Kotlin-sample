package com.example.mvi_java_kotlin.kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvi_java_kotlin.R
import com.example.mvi_java_kotlin.kotlin.data.Comment
import com.example.mvi_java_kotlin.kotlin.data.Interactor
import com.example.mvi_java_kotlin.kotlin.di.DaggerAppComponent
import com.example.mvi_java_kotlin.kotlin.domain.CommentState
import com.example.mvi_java_kotlin.kotlin.domain.MainView
import com.example.mvi_java_kotlin.kotlin.presenter.MainPresenter
import com.example.mvi_java_kotlin.kotlin.ui.adapter.CommentAdapter
import com.example.mvi_java_kotlin.kotlin.util.provideViewModel
import com.example.mvi_java_kotlin.network.CommentApi.API_URL
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    lateinit var mainPresenter: MainPresenter

    @Inject
    lateinit var interactor: Interactor

    private val adapter by lazy { CommentAdapter() }

    override fun render(commentState: CommentState) {
        when (commentState) {
            is CommentState.LoadingState -> showProgressBar()
            is CommentState.ErrorState -> showError()
            is CommentState.DataState -> showCommentsList(commentState.data)
        }
    }

    override fun searchMovieIntent(): Observable<Boolean> {
        return RxView.clicks(get_comments_button).map { true }
    }

    private fun showProgressBar() {
        error_text.visibility = View.GONE
        get_comments_button.visibility = View.GONE
        recycler.visibility = View.GONE
    }

    private fun showError() {
        error_text.visibility = View.VISIBLE
        get_comments_button.visibility = View.VISIBLE
    }

    private fun showCommentsList(commentsList: List<Comment>) {
        recycler.visibility = View.VISIBLE
        get_comments_button.visibility = View.VISIBLE

        adapter.comments = commentsList
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.builder().baseUrl(API_URL).create().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainPresenter = provideViewModel { MainPresenter(interactor) }
        mainPresenter.bind(this)

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStop() {
        mainPresenter.unbind()
        super.onStop()
    }
}
