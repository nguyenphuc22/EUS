package com.example.eus.ODT

interface Payment {

    fun getType() : String

    fun paid(): Boolean
}