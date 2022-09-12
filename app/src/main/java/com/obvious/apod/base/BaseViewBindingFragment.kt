package com.obvious.apod.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.obvious.apod.R

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseViewBindingFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>,
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    protected fun showErrorSnack(
        view: View,
        message: String = getString(R.string.unexpected_error)
    ) {
        val snack = Snackbar.make(
            view,
            message,
            Snackbar.LENGTH_SHORT
        )
        val snackView = snack.view
        snackView.setBackgroundColor(ContextCompat.getColor(view.context, R.color.red))
        val textView =
            snackView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(view.context, R.color.white))
        textView.textSize = 12f
        snack.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}