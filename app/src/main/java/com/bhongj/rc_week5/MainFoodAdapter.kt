package com.bhongj.rc_week5

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bhongj.rc_week5.databinding.ItemRecyclerBinding
import com.bhongj.rc_week5.model.Row

class MainFoodAdapter(private val dataList: MutableList<Row>) :
    RecyclerView.Adapter<MainFoodAdapter.ViewHolder>() {
    private lateinit var binding: ItemRecyclerBinding
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        var imgFoodView: ImageView = binding.rcyImgFood
        var imgStar: ImageView = binding.rcyImgStar
        var region: TextView = binding.rcyTxtRegion
        var restrntNameView: TextView = binding.rcyTxtRestrntName
        var restrntType: TextView = binding.rcyTxtRestrntType
        var rateView: TextView = binding.rcyTxtRate

        init {
            // Define click listener for the ViewHolder's View.
        }

        fun bind(item: Row) {
            imgFoodView.setImageResource(item.PIC)
            region.text = item.SIGNGU_NM
            restrntNameView.text = item.BIZPLC_NM
            restrntType.text = item.INDUTYPE_DETAIL_NM
            rateView.text = item.RATE.toString()
//            name.text = item.
//            message.text = item.message[item.message.size-1]
//            img.setImageResource(item.imgRsc)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_recycler, parent, false)
//        return ViewHolder(view)
        binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        holder.bind(item)
//        if (item.message.size > 0) {
//            holder.bind(item)
//
//            holder.itemView.setOnClickListener { v ->
//                val intent = Intent(v.context, TalkPageActivity::class.java)
//                intent.putExtra("name", item.name)
//                intent.putExtra("message", item.message[item.message.size-1])
//                intent.putExtra("imgRsc", item.imgRsc)
//                ContextCompat.startActivity(v.context, intent, null)
//            }
//        }

    }

    override fun getItemCount() = dataList.size

}