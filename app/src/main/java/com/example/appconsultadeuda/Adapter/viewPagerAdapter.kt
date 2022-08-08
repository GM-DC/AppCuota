package com.example.appconsultadeuda.Adapter

import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.appconsultadeuda.UI.frgAllListCuota
import com.example.appconsultadeuda.UI.frgDetalleCuota

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