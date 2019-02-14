package com.vikas.androidmvvm.views


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.vikas.androidmvvm.R
import com.vikas.androidmvvm.models.dataclasses.Row
import com.vikas.androidmvvm.views.CountryDetailFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_countrydetail.view.*

/**
 * [RecyclerView.Adapter] that can display a [Row] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyCountryDetailRecyclerViewAdapter(
    private var mValues: List<Row?>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyCountryDetailRecyclerViewAdapter.ViewHolder>() {

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
            .inflate(R.layout.fragment_countrydetail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mTitle.text = item?.title
        holder.mDescription.text = item?.description
        if (item?.imageHref != null) {
            holder.mThumb.visibility = VISIBLE
            Picasso.get().
                    load(item.imageHref)
                    .error(R.drawable.ic_photo_size_select_actual_black_24dp)
                    .placeholder(R.drawable.ic_photo_size_select_actual_black_24dp)
                    .into(holder.mThumb)
        } else
            holder.mThumb.visibility = GONE
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