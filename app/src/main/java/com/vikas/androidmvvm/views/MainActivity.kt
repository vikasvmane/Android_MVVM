package com.vikas.androidmvvm.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.vikas.androidmvvm.R
import com.vikas.androidmvvm.models.dataclasses.Row

class MainActivity : AppCompatActivity(), CountryDetailFragment.OnListFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListFragment()
    }

    private fun setListFragment() {
        var fragment = supportFragmentManager.findFragmentByTag("CountryFragmentTag")
        if (fragment == null) {
            val ft = supportFragmentManager.beginTransaction()
            fragment = CountryDetailFragment()
            ft.add(R.id.flContainer, fragment, "CountryFragmentTag")
            ft.commit()
        }
    }

    /**
     *  Returns [Row] object on click of a row in recyclerview
     */
    override fun onListFragmentInteraction(item: Row?) {
        Toast.makeText(this, """${item!!.title} clicked""", Toast.LENGTH_SHORT).show()
    }
}
