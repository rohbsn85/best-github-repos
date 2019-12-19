package com.rkerz.bestgithubrepos.overview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.iconmobile.core.bestgithubrepos.R
import com.rkerz.bestgithubrepos.common.model.RepoOverview
import kotlinx.android.synthetic.main.repo_overview_fragment.*

class RepoOverviewFragment : Fragment() {

    companion object {
        fun newInstance() =
            RepoOverviewFragment()
    }

    private lateinit var viewModel: ReposOverviewViewModel
    private val repoAdapter = RepoOverviewAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.repo_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            RepoOverviewViewModelFactory()
        ).get(ReposOverviewViewModel::class.java)

        tryAgainButton.setOnClickListener {
            viewModel.fetchRepos()
        }

        initRecyclerView()
        observeRepoOverview()
        observeError()
        observeLoading()
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchRepos()
    }

    private fun initRecyclerView() {
        repoOverviewRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = repoAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

    private fun showError(errorCode: Int) {
        repoOverviewRecyclerView.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
        errorText.text = getString(R.string.error_text, errorCode)
    }

    private fun updateRepoOverview(repoOverview: RepoOverview) {
        errorLayout.visibility = View.GONE
        repoOverviewRecyclerView.visibility = View.VISIBLE
        repoAdapter.repos = repoOverview.repoList
        repoAdapter.notifyDataSetChanged()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            errorLayout.visibility = View.GONE
            repoOverviewRecyclerView.visibility = View.GONE
        }
        repoOverviewProgress.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun observeRepoOverview() {
        viewModel.repoList().observe(this, Observer {
            updateRepoOverview(it)
        })
    }

    private fun observeError() {
        viewModel.error().observe(this, Observer {
            showError(it)
        })
    }

    private fun observeLoading() {
        viewModel.loading().observe(this, Observer {
            showLoading(it)
        })
    }

}
