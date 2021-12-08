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

       fun addId(id : String) : Builder {
           this.mId = id
           return this
       }
       fun addUsername(username : String) : Builder {
           this.mUsername = username
           return this
       }
       fun addPhone(phone : String) : Builder {
           this.mPhone = phone
           return this
       }
       fun addPassword(password : String) : Builder {
           this.mPassword = password
           return this
       }
       fun addName(name : String) : Builder {
           this.mName = name
           return this
       }
       fun addDateOfBirth(dateOfBirth : Long) : Builder {
           this.mDateOfBirth = dateOfBirth
           return this
       }
       fun addEmail(email : String) : Builder {
           this.mEmail = email
           return this
       }
       fun addImage(url : String) : Builder {
           this.mImage = url
           return this
       }
       fun build() : Account {
           return Account(mId,mUsername,mPhone,mPassword,mName,mDateOfBirth,mEmail)
       }
   }
}