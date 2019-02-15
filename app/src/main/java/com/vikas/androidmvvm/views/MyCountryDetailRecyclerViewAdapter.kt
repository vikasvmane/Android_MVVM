package com.vikas.androidmvvm.views


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vikas.androidmvvm.R
import com.vikas.androidmvvm.commons.GlideApp
import com.vikas.androidmvvm.models.dataclasses.Row
import com.vikas.androidmvvm.views.CountryDetailFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.country_detail_item.view.*


/**
 * [RecyclerView.Adapter] that can display a [Row] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyCountryDetailRecyclerViewAdapter(
    private var mValues: List<Row?>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyCountryDetailRecyclerViewAdapter.ViewHolder>() {
    private lateinit var context: Context

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Row
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_detail_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mTitle.text = item?.title
        holder.mDescription.text = item?.description
        //If title is not available, removes the item row from the list
        if (item?.title.isNullOrEmpty()) {
            holder.mView.visibility = GONE
            holder.mView.layoutParams = RecyclerView.LayoutParams(0, 0)
        }
        else
            holder.mView.visibility = VISIBLE
        // If image attribute is null, hides imageView
        if (item?.imageHref != null) {
            GlideApp.with(context)
                .asBitmap()
                .load(item.imageHref)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_broken_image_black_24dp)
                .placeholder(R.drawable.ic_photo_size_select_actual_black_24dp)
                .into(holder.mThumb)

        } else {
            holder.mThumb.setImageDrawable(null)
        }
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitle: TextView = mView.txtTitle
        val mDescription: TextView = mView.txtDescription
        val mThumb: ImageView = mView.imgThumb

        override fun toString(): String {
            return super.toString() + " '" + mDescription.text + "'"
        }
    }
}
