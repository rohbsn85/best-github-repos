package com.rkerz.bestgithubrepos.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.iconmobile.core.bestgithubrepos.R
import com.rkerz.bestgithubrepos.common.model.Repo
import com.rkerz.bestgithubrepos.detail.model.RepoDetailRequest
import kotlinx.android.synthetic.main.repo_detail_fragment.*

class RepoDetailFragment : Fragment() {
    private lateinit var viewModel: RepoDetailViewModel

    private val navArgs: RepoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.repo_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, RepoDetailViewModelFactory())
            .get(RepoDetailViewModel::class.java)

        observeRepoDetails()
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchRepo(navArgs.repoOwner, navArgs.repoName)
    }

    private fun showDetailsFor(repo: Repo) {
        with(repo) {
            repoName.text = name
            repoOwner.text = getString(R.string.by_owner, owner.name)
            repoFollowerCount.text = getString(R.string.follower_count, watcherCount)
            repoLanguage.text = language
            repoStarCountValue.text = starCount.toString()
        }

    }

    private fun observeRepoDetails() {
        viewModel.reopoDetails().observe(this, Observer {
            when (it) {
                is RepoDetailRequest.Success -> showDetailsFor(it.repo)
                is RepoDetailRequest.Error -> TODO()
                is RepoDetailRequest.Loading -> TODO()
            }
        })
    }

}
