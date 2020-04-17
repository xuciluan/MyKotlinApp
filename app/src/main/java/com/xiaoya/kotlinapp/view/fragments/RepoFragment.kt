package com.xiaoya.kotlinapp.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.xiaoya.kotlinapp.R
import com.xiaoya.kotlinapp.view.common.CommonViewPagerFragment
import com.xiaoya.kotlinapp.view.config.FragmentPage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RepoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RepoFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RepoFragment : CommonViewPagerFragment(){

    override fun getFragmentPagesLoggedIn(): List<FragmentPage> {
        return listOf(FragmentPage(RepoListFragment(),""))
    }

    override fun getFragmentPagesNotLoggedIn(): List<FragmentPage> {

    }

}