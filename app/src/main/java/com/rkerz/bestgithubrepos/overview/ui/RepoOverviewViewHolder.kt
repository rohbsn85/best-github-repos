package com.rkerz.bestgithubrepos.overview.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rkerz.bestgithubrepos.common.model.Repo
import kotlinx.android.synthetic.main.repo_overview_item.view.*

class RepoOverviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(repo: Repo, onRepoItemClickedListener: (Repo) -> Unit) {
        itemView.repoNameTextView.text = repo.name
        itemView.starCountTextView.text = repo.starCount.toString()
        itemView.setOnClickListener {
            onRepoItemClickedListener(repo)
        }
    }
}