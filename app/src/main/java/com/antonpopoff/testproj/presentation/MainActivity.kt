package com.antonpopoff.testproj.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.antonpopoff.testproj.R
import com.antonpopoff.testproj.presentation.symbolsselection.SymbolsSelectionFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addTickersFragmentIfAbsent()
    }

    private fun addTickersFragmentIfAbsent() {
        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentsContainer, SymbolsSelectionFragment())
                .commit()
        }
    }
}
