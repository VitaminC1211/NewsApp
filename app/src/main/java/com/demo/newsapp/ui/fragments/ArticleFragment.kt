package com.demo.newsapp.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import com.demo.newsapp.R
import com.demo.newsapp.databinding.FragmentArticleBinding
import com.demo.newsapp.ui.NewsActivity
import com.demo.newsapp.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar


class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var newsViewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()
    lateinit var binding: FragmentArticleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)

        newsViewModel = (activity as NewsActivity).newsViewModel
        val article = args.article

        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let{
                loadUrl(it)
            }
        }

        binding.fab.setOnClickListener{
            newsViewModel.addToFavourites(article)
            Snackbar.make(view,"Added to favourites", Snackbar.LENGTH_SHORT).show()
        }
    }
}