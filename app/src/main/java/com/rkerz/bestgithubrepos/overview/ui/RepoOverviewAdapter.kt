package com.rkerz.bestgithubrepos.overview.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iconmobile.core.bestgithubrepos.R
import com.rkerz.bestgithubrepos.common.model.Repo

class RepoOverviewAdapter(var repos: List<Repo>, private val onRepoItemClickedListener: (Repo) -> Unit) : RecyclerView.Adapter<RepoOverviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepoOverviewViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.repo_overview_item,
                parent,
                false
            )
        )

    override fun getItemCount() = repos.size


    override fun onBindViewHolder(holder: RepoOverviewViewHolder, position: Int) {
        holder.bind(repos[position], onRepoItemClickedListener)
    }
}