package com.fmdwallpaper.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*


class Register : Fragment() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

        //setup Nav controller
        navController = Navigation.findNavController(view)

        //Check if User Already Loggedin
        if(firebaseAuth.currentUser == null){
            //Not Logged in, Create new account
            register_text.text = "Creating New Account"
            firebaseAuth.signInAnonymously().addOnCompleteListener {
                if(it.isSuccessful){
                    //Account Created, go to home
                    register_text.text = "Account Created, Logging in"
                    navController!!.navigate(R.id.action_register_to_home)
                }else {
                    //error
                    register_text.text = "Error  : ${it.exception!!.message}"
                }
            }

        } else {
            //Logged in,Send back to homepage
            navController!!.navigate(R.id.action_register_to_home)
        }

    }




}