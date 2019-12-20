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
    private val navArgs: RepoDetailFragmentArgs by navArgs()
    private val observer = Observer<RepoDetailRequest> {
        when (it) {
            is RepoDetailRequest.Success -> {
                hideError()
                hideLoading()
                showDetailsFor(it.repo)
            }
            is RepoDetailRequest.Error -> {
                stopObserving()
                hideLoading()
                hideDetails()
                showError(it.errorCode)
            }
            is RepoDetailRequest.Loading -> {
                hideError()
                hideDetails()
                showLoading()
            }
        }
    }

    private lateinit var viewModel: RepoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.repo_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            RepoDetailViewModelFactory(navArgs.repoOwner, navArgs.repoName)
        )
            .get(RepoDetailViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repoTryAgainButton.setOnClickListener {
            observeRepoDetails()
        }

        observeRepoDetails()
    }

    private fun showDetailsFor(repo: Repo) {
        repoName.visibility = View.VISIBLE
        repoOwner.visibility = View.VISIBLE
        repoStarCountValue.visibility = View.VISIBLE
        repoStarImage.visibility = View.VISIBLE
        repoFollowerCount.visibility = View.VISIBLE
        repoLanguage.visibility = View.VISIBLE

        with(repo) {
            repoName.text = name
            repoOwner.text = getString(R.string.by_owner, owner.name)
            repoFollowerCount.text = getString(R.string.follower_count, watcherCount)
            repoLanguage.text = getString(R.string.language, language ?: getString(R.string.n_a))
            repoStarCountValue.text = starCount.toString()
        }
    }

    private fun hideDetails() {
        repoName.visibility = View.GONE
        repoOwner.visibility = View.GONE
        repoStarCountValue.visibility = View.GONE
        repoStarImage.visibility = View.GONE
        repoFollowerCount.visibility = View.GONE
        repoLanguage.visibility = View.GONE
    }

    private fun showError(errorCode: Int) {
        repoErrorLayout.visibility = View.VISIBLE
        repoErrorText.text = getString(R.string.error_text, errorCode)
    }

    private fun hideError() {
        repoErrorLayout.visibility = View.GONE
    }

    private fun showLoading() {
        repoProgress.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        repoProgress.visibility = View.GONE
    }

    private fun observeRepoDetails() {
        viewModel.repoDetailRequestLiveData.observe(this, observer)
    }

    private fun stopObserving() {
        viewModel.repoDetailRequestLiveData.removeObserver(observer)
    }

}
