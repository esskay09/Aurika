package com.example.aurika.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.aurika.R
import com.example.aurika.databinding.FragmentSourceDialogBinding

class SourceDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentSourceDialogBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_source_dialog, container, false)

       

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->


        }

        return binding.root
    }
}