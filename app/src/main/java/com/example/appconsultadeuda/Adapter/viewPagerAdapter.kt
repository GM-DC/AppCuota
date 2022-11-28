package com.example.appconsultadeuda.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.appconsultadeuda.ui.frgAllListCuota
import com.example.appconsultadeuda.ui.frgDetalleCuota

class viewPagerAdapter(fragmentActivity: FragmentActivity, private var totalCount: Int) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return totalCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> frgAllListCuota()
            1 -> frgDetalleCuota()
            else -> frgAllListCuota()
        }
    }

}