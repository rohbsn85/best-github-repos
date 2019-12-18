package com.iconmobile.core.bestgithubrepos.overview.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iconmobile.core.bestgithubrepos.R

class RepoDetailFragment : Fragment() {

    companion object {
        fun newInstance() = RepoDetailFragment()
    }

    private lateinit var viewModel: RepoDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.repo_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RepoDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
