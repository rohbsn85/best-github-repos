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

        initRecyclerView()
        observeRepoList()
    }

    private fun initRecyclerView() {
        repoOverviewRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = repoAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

    private fun observeRepoList() {
        viewModel.repoList.observe(this, Observer {
            repoOverviewProgress.visibility = View.GONE
            repoAdapter.repos = it.repoList
            repoAdapter.notifyDataSetChanged()
        })
    }

}
