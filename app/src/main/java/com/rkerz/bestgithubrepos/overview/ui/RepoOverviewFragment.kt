package com.rkerz.bestgithubrepos.overview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.iconmobile.core.bestgithubrepos.R
import com.rkerz.bestgithubrepos.common.model.Repo
import com.rkerz.bestgithubrepos.overview.model.RepoOverview
import com.rkerz.bestgithubrepos.overview.model.RepoOverviewRequest
import kotlinx.android.synthetic.main.repo_overview_fragment.*

class RepoOverviewFragment : Fragment() {
    private lateinit var viewModel: ReposOverviewViewModel
    private val repoAdapter = RepoOverviewAdapter(listOf()) { repo -> showDetailsForRepo(repo) }

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
        observeRepoOverviewRequest()
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchRepos()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        repoOverviewRecyclerView.adapter = null
    }

    private fun initRecyclerView() {
        repoOverviewRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = repoAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

    private fun showError(errorCode: Int) {
        errorLayout.visibility = View.VISIBLE
        errorText.text = getString(R.string.error_text, errorCode)
    }

    private fun hideError() {
        errorLayout.visibility = View.GONE
    }

    private fun showRepoOverview(repoOverview: RepoOverview) {
        repoOverviewRecyclerView.visibility = View.VISIBLE
        repoAdapter.repos = repoOverview.repoList
        repoAdapter.notifyDataSetChanged()
    }

    private fun hideRepoOverview() {
        repoOverviewRecyclerView.visibility = View.GONE
    }

    private fun showLoading() {
        repoOverviewProgress.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        repoOverviewProgress.visibility = View.GONE
    }

    private fun observeRepoOverviewRequest() {
        viewModel.repoOverview().observe(this, Observer {
            when (it) {
                is RepoOverviewRequest.Loading -> {
                    hideError()
                    hideRepoOverview()
                    showLoading()
                }
                is RepoOverviewRequest.Error -> {
                    hideLoading()
                    hideRepoOverview()
                    showError(it.errorCode)
                }
                is RepoOverviewRequest.Success -> {
                    hideLoading()
                    hideError()
                    showRepoOverview(it.repoOverview)
                }
            }
        })
    }

    private fun showDetailsForRepo(repo: Repo) {
        val navAction =
            RepoOverviewFragmentDirections.actionRepoOverviewFragmentToRepoDetailFragment(
                repo.owner.name,
                repo.name
            )

        findNavController().navigate(navAction)
    }

}
