package com.greedy.practice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greedy.practice.databinding.ItemRecyclerBinding

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Holder>(){

    var listData = mutableListOf<Memo>()
    var helper:SqliteHelper? = null

    inner class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){

        var mMemo: Memo? = null

        init {
            binding.deleteButton.setOnClickListener{
                helper?.deleteMemo(mMemo!!)
                listData.remove(mMemo)
                notifyDataSetChanged()
            }
        }

        fun setMemo(memo: Memo){
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content

            mMemo = memo
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  Holder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData[position]
        holder.setMemo(memo)
    }

}