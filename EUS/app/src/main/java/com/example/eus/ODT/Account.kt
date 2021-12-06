package com.example.eus.ODT

data class Account (
    var mId : String? = null,
    var mUsername : String? = null,
    var mPhone : String? = null,
    var mPassword : String? = null,
    var mName : String? = null,
    var mDateOfBirth : Long? = null,
    var mEmail : String? = null,
    var mImage : String? = null
    )
{
   data class Builder(
       private var mId : String? = null,
       private var mUsername : String? = null,
       private var mPhone : String? = null,
       private var mPassword : String? = null,
       private var mName : String? = null,
       private var mDateOfBirth : Long? = null,
       private var mEmail : String? = null,
       private var mImage : String? = null
   ) {
       fun addId(id : String) = apply { this.mId = id }
       fun addUsername(username : String) = apply { this.mUsername = username }
       fun addPhone(phone : String) = apply { this.mPhone = phone }
       fun addPassword(password : String) = apply { this.mPassword = password }
       fun addName(name : String) = apply { this.mName = name }
       fun addDateOfBirth(dateOfBirth : Long) = apply { this.mDateOfBirth = dateOfBirth }
       fun addEmail(email : String) = apply { this.mEmail = email }
       fun addImage(mail : String) = apply { this.mImage = mail }
       fun build() = Account(mId,mUsername,mPhone,mPassword,mName,mDateOfBirth,mEmail)
   }
}