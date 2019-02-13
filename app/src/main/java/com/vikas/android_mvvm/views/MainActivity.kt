package com.vikas.android_mvvm.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vikas.android_mvvm.R
import com.vikas.android_mvvm.views.dummy.DummyContent

class MainActivity : AppCompatActivity(), CountryDetailFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fm = supportFragmentManager
        var fragment = fm.findFragmentByTag("CountryFragmentTag")
        if (fragment == null) {
            val ft = fm.beginTransaction()
            fragment = CountryDetailFragment()
            ft.add(R.id.flContainer, fragment, "CountryFragmentTag")
            ft.commit()
        }
    }
}
