package com.example.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), Communicator{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnFragment = ButtonFragment()
        supportFragmentManager.beginTransaction().add(R.id.btnFragmentView, btnFragment).commit()
    }

    override fun passDataCom(pageUrl: String) {
        val bundle = Bundle()
        bundle.putString("url",pageUrl)

        val transaction = this.supportFragmentManager.beginTransaction()
        val webFragment = WebFragment()
        webFragment.arguments = bundle

        transaction.add(R.id.webViewFrag, webFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

}
