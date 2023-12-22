package hse.course.android_lab3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hse.course.android_lab3.R
import hse.course.android_lab3.model.News

class AdapterImpl(private val newsData: ArrayList<News>) :

    RecyclerView.Adapter<AdapterImpl.ViewHolderImpl>() {

    class ViewHolderImpl(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val content: TextView

        init {
            title = view.findViewById(R.id.header)
            content = view.findViewById(R.id.content)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolderImpl {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item, viewGroup, false)

        return ViewHolderImpl(view)
    }

    override fun onBindViewHolder(viewHolderImpl: ViewHolderImpl, position: Int) {
        viewHolderImpl.title.text = newsData[position].title
        viewHolderImpl.content.text = newsData[position].content
    }

    override fun getItemCount() = newsData.size

}