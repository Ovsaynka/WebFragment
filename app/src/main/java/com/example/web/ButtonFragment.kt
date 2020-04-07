package com.example.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.web_button.*

 class ButtonFragment : Fragment() {

     lateinit var comm: Communicator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.web_button, container, false)   //find xml_file our fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comm = activity as Communicator
        //val pageUrl = "https://androidwave.com/"
        googleButton.setOnClickListener {
            comm.passDataCom("https://www.google.com.ua")
        }
        facebookButton.setOnClickListener {
            comm.passDataCom("https://www.facebook.com")
        }

        twitterButton.setOnClickListener {
            comm.passDataCom("https://twitter.com")
        }

    }
}