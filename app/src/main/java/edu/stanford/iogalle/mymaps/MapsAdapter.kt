package edu.stanford.iogalle.mymaps

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import edu.stanford.iogalle.mymaps.models.UserMap

private const val TAG = "MapsAdapter"
class MapsAdapter(val context: Context, val userMaps: List<UserMap>, val onClickListener: OnClickListener) : RecyclerView.Adapter<MapsAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user_map, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = userMaps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userMap = userMaps[position]
        holder.itemView.setOnClickListener {
            Log.i(TAG, "Tapped on position $position")
            onClickListener.onItemClick(position)
        }

        // Find map title text view
        val textViewTitle = holder.itemView.findViewById<TextView>(R.id.tvMapTitle)
        val pin = if (userMap.places.size == 1) "pin" else "pins"
        val title = "%s %d ".format(userMap.title, userMap.places.size) + pin

        // Format text
        val spannable = SpannableString(title)
        spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorAccent)),
            0,userMap.title.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(StyleSpan(BOLD),0,userMap.title.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(StyleSpan(BOLD),0,userMap.title.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(RelativeSizeSpan(0.8f),userMap.title.length + 1,spannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set text
        textViewTitle.text = spannable

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}