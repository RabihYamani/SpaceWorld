package com.example.spaceworld

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.spaceworld.rover_list.RoversList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var cnfPassword: EditText
    private lateinit var fAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view =  inflater.inflate(R.layout.fragment_register, container, false)

        username = view.findViewById(R.id.reg_username)
        password = view.findViewById(R.id.reg_password)
        cnfPassword = view.findViewById(R.id.reg_cnf_password)
        fAuth= Firebase.auth

        view.findViewById<Button>(R.id.btn_login_reg).setOnClickListener(){
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(LoginFragment(), false)
        }

        view.findViewById<Button>(R.id.btn_register_reg).setOnClickListener {
            validateEmptyForm()
        }
        return view

    }

    private fun firebaseSignUp(){
        btn_register_reg.isEnabled= false
        btn_register_reg.alpha=0.5f
        fAuth.createUserWithEmailAndPassword(username.text.toString(),
            password.text.toString()).addOnCompleteListener{
            task ->
            if(task.isSuccessful){

                var navHome = activity as FragmentNavigation
                navHome.navigateFrag(RoversList(), addToStack = true)
               // Toast.makeText(context, "Register Successful", Toast.LENGTH_SHORT).show()
            }else {
                btn_register_reg.isEnabled= true
                btn_register_reg.alpha=1.0f
                Toast.makeText(context,task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateEmptyForm(){
        when{
            TextUtils.isEmpty(username.text.toString().trim()) ->{
                username.setError("Please Enter Username")
            }
            TextUtils.isEmpty(password.text.toString().trim()) ->{
                password.setError("Please Enter Password")
            }
            TextUtils.isEmpty(cnfPassword.text.toString().trim()) ->{
                cnfPassword.setError("Please Enter Password Again")
            }

            username.text.toString().isNotEmpty() &&
                    password.text.toString().isNotEmpty() &&
                    cnfPassword.text.toString().isNotEmpty() ->
            {
                if (username.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
                    if (password.text.toString().length >= 5) {
                        if (password.text.toString() == cnfPassword.text.toString()) {

                           firebaseSignUp()
//                            Toast.makeText(context, "Register Successful", Toast.LENGTH_SHORT)
//                                .show()
                        } else {
                            cnfPassword.setError("Password didn't match")
                        }
                    } else {
                        password.setError("Please Enter atleast 5 charecters")
                    }
                }else{
                    username.setError("Please Enter Valid Email Id")
                }
            }

            }
    }


}