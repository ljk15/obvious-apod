package com.obvious.apod.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

typealias BindingFactory<T> = (LayoutInflater) -> T

abstract class BaseViewBindingActivity<VB : ViewBinding>(
    private val bindingFactory: BindingFactory<VB>,
) : AppCompatActivity() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
    }
}