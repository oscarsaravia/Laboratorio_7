package com.example.android.lab4v2.game

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.lab4v2.Guest
import com.example.android.lab4v2.ItemDataClass
import com.example.android.lab4v2.R
import com.example.android.lab4v2.utilities.Utils

class GameViewModel: ViewModel(){

    val index = MutableLiveData<Int>()
    val name = MutableLiveData<String>()
    val mail = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val registered = MutableLiveData<Int>()
    val guests = MutableLiveData<Int>()
    val namelist: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val roll = MutableLiveData<MutableList<ItemDataClass>>()
    val rolelist: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val people1 = MutableLiveData<MutableList<Guest>>()


    var listSize = 0
    var invitednum = 0
    lateinit var people: MutableList<Guest>


    init{

        people = mutableListOf<Guest>()
        for (item in Utils.lista){
            people.add(item)
        }
        //people = people1.value!!
        listSize = people.size
        index.value = 0
        namelist.value = ""
        rolelist.value = ""
        guests.value = 0
        registered.value = 0

        val item = ItemDataClass(R.drawable.ic_supervisor_account_black_24dp, "Item 1", "Organizador del evento")
        roll.value?.add(item)


    }


    private fun nextGuest() {
        if (index.value != people.size) {

            name.value = people[index.value!!].name
            mail.value = people[index.value!!].phone
            phone.value = people[index.value!!].mail


        }
    }

    fun isAssisting(){
        registered.value = (registered.value)?.plus(1)
        guests.value = (guests.value)?.plus(1)
        nextGuest()

    }

    fun notAssisting(){
        guests.value = (guests.value)?.plus(1)
        nextGuest()
    }

    fun setFirst(){
        nextGuest()
    }

    fun isAssistingLast(){
        registered.value = (registered.value)?.plus(1)
        guests.value = (guests.value)?.plus(1)
    }
    fun notAssistingLast(){
        guests.value = (guests.value)?.plus(1)
    }

    fun addList(person: MutableList<Guest>){
       people1.value = person


    }
}