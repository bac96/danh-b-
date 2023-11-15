package com.example.danhba

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.ActionMode
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class DanhBaAdapter(
    val items: ArrayList<DanhBaModel>,
    val itemClickListener: (DanhBaModel) -> Unit,
    val itemLongClickListener: (DanhBaModel) -> Boolean
) :
    RecyclerView.Adapter<DanhBaAdapter.DanhBaItemViewHolder>() {
    private var selectedItem: DanhBaModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DanhBaItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)
        return DanhBaItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DanhBaItemViewHolder, position: Int) {
        val item = items[position]
        holder.avatar.text = item.username.first().uppercaseChar().toString()
        holder.username.text = items[position].username
        holder.layoutItem.setOnClickListener {
            itemClickListener.invoke(item)
        }
        holder.layoutItem.setOnLongClickListener {
            selectedItem = item
            itemLongClickListener.invoke(item)
        }
    }

    fun getSelectedContact(): DanhBaModel? {
        return selectedItem
    }

    class DanhBaItemViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: TextView
        val username: TextView
        val layoutItem: ConstraintLayout

        init {
            avatar = itemView.findViewById(R.id.avatar)
            username = itemView.findViewById(R.id.name)
            layoutItem = itemView.findViewById(R.id.layout_item)
        }
    }

}