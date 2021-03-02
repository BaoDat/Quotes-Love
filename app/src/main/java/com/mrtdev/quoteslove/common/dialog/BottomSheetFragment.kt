package com.mrtdev.quoteslove.common.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.database.models.Quote
import org.parceler.Parcels


class BottomSheetFragment: BottomSheetDialogFragment() {

    companion object {
        const val EXTRA_PARAMS = "extra_params"

        @JvmStatic
        fun newInstance(param: Quote) =  BottomSheetFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_PARAMS, Parcels.wrap(param))
            }
        }
    }

    private val quoteParam by lazy {
        Parcels.unwrap(requireArguments().getParcelable(EXTRA_PARAMS)) as Quote
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.bottom_sheet, container, false)
    }
}
