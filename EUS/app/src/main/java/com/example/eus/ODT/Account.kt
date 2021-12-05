package com.example.eus.ODT

data class Account (
    var mId : String?,
    var mPhone : String?,
    var mPassword : String?,
    var mName : String?,
    var mDateOfBirth : Long?,
    var mEmail : String?
        )
{
   data class Builder(
       var mId : String? = null,
       var mPhone : String? = null,
       var mPassword : String? = null,
       var mName : String? = null,
       var mDateOfBirth : Long? = null,
       var mEmail : String? = null
   ) {
       fun addId(id : String) = apply { this.mId = id }
       fun addPhone(phone : String) = apply { this.mPhone = phone }
       fun addPassword(password : String) = apply { this.mPassword = password }
       fun addName(name : String) = apply { this.mName = name }
       fun addDateOfBirth(dateOfBirth : Long) = apply { this.mDateOfBirth = dateOfBirth }
       fun setEmail(email : String) = apply { this.mEmail = email }
       fun build() = Account(mId,mPhone,mPassword,mName,mDateOfBirth,mEmail)
   }
}