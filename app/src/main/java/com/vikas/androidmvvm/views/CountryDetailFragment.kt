package com.vikas.androidmvvm.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
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

    private var countryDetailViewModel: CountryDetailViewModel? = null

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_countrydetail_list, container, false)

        countryDetailViewModel = ViewModelProviders.of(activity!!).get(CountryDetailViewModel::class.java)
        setObservers()
        //If list data is available, no need to fetch country details
        if (savedInstanceState == null || savedInstanceState.getBoolean(KEY_IS_DATA_LOADED))
            countryDetailViewModel?.getCountryDetails()

        return view
    }

    /**
     * Creates observers to observe livedata from ViewModels
     */
    private fun setObservers() {
        // Observes country details list
        countryDetailViewModel?.countryDetailsList?.observe(this, Observer {
            //  list.addItemDecoration(DividerItemDecoration(activity,LinearLayoutManager.VERTICAL))
            list.adapter = MyCountryDetailRecyclerViewAdapter(it!!, listener)
            llNoDataMessage.visibility = GONE
            swipeRefreshLayout.isRefreshing = false
        })
        // Observes API call sequence for handling progressBar visibility
        countryDetailViewModel?.isLoading?.observe(this, Observer {
            when (it) {
                true -> progressBar.visibility = VISIBLE
                false -> progressBar.visibility = GONE
            }
        })
        // Observes error messages
        countryDetailViewModel?.errorMsg?.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = false
            if (countryDetailViewModel?.countryDetailsList?.value.isNullOrEmpty())
                llNoDataMessage.visibility = VISIBLE
            Snackbar.make(list, it!!, Snackbar.LENGTH_SHORT).show()

        })
        // Observes title from API to be shown as appBarTitle
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

    /**
     * Saves if list data is available which can be used while orientation change
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_IS_DATA_LOADED, countryDetailViewModel?.countryDetailsList?.value.isNullOrEmpty())

    }
}
