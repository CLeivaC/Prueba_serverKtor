package com.ejemplo.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer (val id:String,val first_name:String,val last_name:String,val email:String)