package com.example.eus.viewPayment

import com.example.eus.ODT.ShipInfo

interface OnClickItem {
    fun OnCLickMenuUpdate(shipInfo: ShipInfo)

    fun OnClickMenuDelete(shipInfo: ShipInfo)
}