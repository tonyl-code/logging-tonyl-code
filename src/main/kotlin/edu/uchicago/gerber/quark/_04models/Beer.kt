package edu.uchicago.gerber.quark._04models

import org.bson.types.ObjectId

class Beer{
    var id: ObjectId? = null
    lateinit var event:String
    lateinit var session_email:String
    lateinit var timestamp:String
}
