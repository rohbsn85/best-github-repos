package com.iconmobile.core.bestgithubrepos.overview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iconmobile.core.bestgithubrepos.R

class RepoOverviewFragment : Fragment() {

    companion object {
        fun newInstance() = RepoOverviewFragment()
    }

    private lateinit var viewModel: ReposOverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.repo_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, RepoOverviewViewModelFactory())
            .get(ReposOverviewViewModel::class.java)

        viewModel.repoList.observe(this, Observer {

        })
    }

    private fun initRecyclerView() {

    }

}
