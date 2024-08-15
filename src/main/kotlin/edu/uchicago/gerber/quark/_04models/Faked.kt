package edu.uchicago.gerber.quark._04models

import com.github.javafaker.Faker
import org.bson.types.ObjectId

object Faked {

    val faker = Faker()
    val FAKE_ID = "5063114bd386d8fadbd6b004"

    fun genRawEntity(): Beer {

        val fakerBeer = faker.beer()
        val beer = Beer()

        beer.event = fakerBeer.name()
        beer.session_email = fakerBeer.yeast()
        beer.timestamp = fakerBeer.malt()
        return beer
    }

    // USED ONLY FOR POSTMAN. We will use id "5063114bd386d8fadbd6b004".
    fun genTestBeer(hash: String): Beer{
        val beer = genRawEntity()
        beer.id = ObjectId(hash)
        return beer

    }

}
