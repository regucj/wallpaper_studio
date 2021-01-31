package com.fmdwallpaper.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.collections.ArrayList


class Home : Fragment(), (WallpapersModel) -> Unit {
// after git hub repository wallpaper_studio
    private val firebaseRepository = FirebaseRepository()
    private var navController: NavController? = null

    private var wallpapersList: List<WallpapersModel> =  ArrayList()
    private val wallpapersListAdapter: WallpapersListAdapter = WallpapersListAdapter(wallpapersList, this)

    private var isLoading: Boolean = true

    private val wallpapersViewModel: WallpapersViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialize action bar

        (activity as AppCompatActivity).setSupportActionBar(main_toolbar)

        var actionbar = (activity as AppCompatActivity).supportActionBar
        actionbar!!.title = "Wallpaper Studio"

        //Initialize nav controller
        navController = Navigation.findNavController(view)



        //Check if user is logged in
        if(firebaseRepository.getUser() == null){
            //user not logged in , go to register page
            navController!!.navigate(R.id.action_home_to_register)
            
        }

        //Intialize Recylcler view
        wallpapers_list_view.setHasFixedSize(true)
        wallpapers_list_view.layoutManager = GridLayoutManager(context, 3)
        wallpapers_list_view.adapter = wallpapersListAdapter

        //Reached bottom of the page
        wallpapers_list_view.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                 if (!recyclerView.canScrollVertically(1)&& newState == RecyclerView.SCROLL_STATE_IDLE){
                     //Reached at bottom and not scrolling anymore
                    if(!isLoading) {
                        //Load next page
                            wallpapersViewModel.loadWallpapersData()
                        firebaseRepository.queryWallpapers()
                        isLoading = true


                        Log.d("HOME_FRAGMENT_LOG", "REACHED Bottom: Loading New Content")


                    }
                 }
            }

        })



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        wallpapersViewModel.getWallpapersList().observe(viewLifecycleOwner, Observer {
            wallpapersList = it
            wallpapersListAdapter.wallpapersList = wallpapersList
            wallpapersListAdapter.notifyDataSetChanged()


            //Loading complete
            isLoading = false
        })

    }

    override fun invoke(wallpaper: WallpapersModel) {

        //Clicked on wallpaper Item from the list, Navigate to detail fragment

        val action = HomeDirections.actionHomeToDetail(wallpaper.image)
        navController!!.navigate(action)





    }


}