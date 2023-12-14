package com.example.mind.providers

import com.example.mind.models.Users
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import java.util.HashMap;
//import java.util.Map;
import kotlin.collections.HashMap

class UserProvider {
    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
/*
    public fun create(user: Users) : Task<Void>?{
/*
        var map : HashMap<String, Any>
                = HashMap<String, Any> ()

        map.put("Nombre", user.Nombre)
        map.put("Correo", user.Email)*/

        return mDatabase.child(user.IdUser).setValue(map)
    }
    public fun update(user: Users) : Task<Void>?{

        var map : HashMap<String, Any>
                = HashMap<String, Any> ()

        user.Nombre?.let { map.put("name", it) }
        user.Matricula?.let { map.put("matricula",it) }
        user.Email?.let { map.put("email", it) }
        user.Semestre?.let { map.put("semestre", it) }
        user.Carrera?.let { map.put("carrera",it) }

        return user.IdUser?.let { mDatabase.child(it).updateChildren(map) }
    }
    public fun getUser(idUser: String) : DatabaseReference{
        return mDatabase.child(idUser)
    }
*/
}