package org.newsapi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsapi.api.model.Article
import dagger.hilt.android.AndroidEntryPoint
import org.newsapi.CATEGORY_GENERAL
import org.newsapi.CATEGORY_LIST
import org.newsapi.data.AppCache
import org.newsapi.databinding.FragmentHeadlinesBinding
import org.newsapi.ui.HomeViewModel
import org.newsapi.ui.NewsRecyclerAdapter

@AndroidEntryPoint
class HeadlinesFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var newsRecyclerAdapter: NewsRecyclerAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private var binding: FragmentHeadlinesBinding? = null
    private var progressBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeadlinesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        val category = homeViewModel.lastFetchCategory
        if (category.isNullOrEmpty()) observeForData(CATEGORY_GENERAL)
        else observeForData(category)
    }

    private fun observeForData(category: String?) {
        progressBar?.visibility = View.VISIBLE
        binding?.homeRecyclerview?.visibility = View.GONE
        homeViewModel.fetchTopHeadlines(AppCache.CURRENT_COUNTRY, category)
            .observe(viewLifecycleOwner, {
                progressBar?.visibility = View.GONE
                if (it.isNullOrEmpty()) {
                    showEmptyView("Not able to load data from server!")
                    return@observe
                }
                binding?.homeRecyclerview?.visibility = View.VISIBLE
                binding?.categoryRecyclerView?.visibility = View.GONE
                newsRecyclerAdapter.submitList(it)
            })
    }

    private fun showEmptyView(message: String) {
        binding?.emptyView?.visibility = View.VISIBLE
        binding?.emptyViewText?.text = message
    }

    private fun setUpRecyclerView() {
        newsRecyclerAdapter =
            NewsRecyclerAdapter { article, imageView -> onArticleClicked(article, imageView) }
        binding?.homeRecyclerview?.layoutManager = LinearLayoutManager(context)
        binding?.homeRecyclerview?.adapter = newsRecyclerAdapter

        categoryAdapter = CategoryAdapter { onCategoryClicked(it) }
        binding?.categoryRecyclerView?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding?.categoryRecyclerView?.adapter = categoryAdapter
        categoryAdapter.submitList(CATEGORY_LIST)

        progressBar = binding?.progressBar
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun onCategoryClicked(category: String) {
        observeForData(category)
    }

    private fun onArticleClicked(article: Article, imageView: ImageView) {
        homeViewModel.setSelectedArticle(article)
        val transitionUniqueId = article.articleUniqueId
        val extras = FragmentNavigatorExtras(imageView to transitionUniqueId)
        val action =
            HeadlinesFragmentDirections.actionNavigationHeadlinesFragmentToDetailFragment(
                transitionUniqueId
            )

        findNavController().navigate(action, extras)
    }
}