package com.vikas.androidmvvm.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.vikas.androidmvvm.R
import com.vikas.androidmvvm.commons.IntentExtras.KEY_IS_DATA_LOADED
import com.vikas.androidmvvm.models.dataclasses.Row
import com.vikas.androidmvvm.viewmodels.CountryDetailViewModel
import kotlinx.android.synthetic.main.fragment_countrydetail_list.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [CountryDetailFragment.OnListFragmentInteractionListener] interface.
 */
class CountryDetailFragment : Fragment() {

    private var countryDetailViewModel: CountryDetailViewModel? =null

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_countrydetail_list, container, false)
        if (savedInstanceState==null ||
                !savedInstanceState.containsKey(KEY_IS_DATA_LOADED) ||
                savedInstanceState.getBoolean(KEY_IS_DATA_LOADED) || countryDetailViewModel==null) {
            countryDetailViewModel = ViewModelProviders.of(activity!!).get(CountryDetailViewModel::class.java)
            setObservers()
            countryDetailViewModel?.getCountryDetails()
        }
        return view
    }

    private fun setObservers() {
        countryDetailViewModel?.countryDetailsList?.observe(this, Observer {
            list.adapter = MyCountryDetailRecyclerViewAdapter(it!!, listener)
            llNoDataMessage.visibility = GONE
            swipeRefreshLayout.isRefreshing = false
        })
        countryDetailViewModel?.isLoading?.observe(this, Observer {
            when (it) {
                true -> progressBar.visibility = VISIBLE
                false -> progressBar.visibility = GONE
            }
        })
        countryDetailViewModel?.errorMsg?.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = false
            if (countryDetailViewModel?.countryDetailsList?.value.isNullOrEmpty())
                llNoDataMessage.visibility = VISIBLE
            Snackbar.make(list, it!!, Snackbar.LENGTH_SHORT).show()

        })
        countryDetailViewModel?.appBarTitle?.observe(this, Observer {
            activity?.title = it!!
        })
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener {
            countryDetailViewModel?.getCountryDetails()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Row?)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_IS_DATA_LOADED, countryDetailViewModel?.countryDetailsList?.value.isNullOrEmpty())

    }
}
