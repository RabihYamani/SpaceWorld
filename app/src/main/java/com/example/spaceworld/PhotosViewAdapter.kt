package com.example.spaceworld

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class PhotosViewAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    val allFragmentList:ArrayList<Fragment> = ArrayList()
    val titleList: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return allFragmentList.get(position)
    }

    override fun getCount(): Int {
        return allFragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String)
    {
        allFragmentList.add(fragment)
        titleList.add(title)
        notifyDataSetChanged()

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }
}