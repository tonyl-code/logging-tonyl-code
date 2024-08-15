package edu.uchicago.gerber.quark._03repositories

import edu.uchicago.gerber.quark._04models.Beer
import edu.uchicago.gerber.quark._04models.Faked
import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.NotFoundException
import org.bson.types.ObjectId


@ApplicationScoped
class MongoBeerRepository : PanacheMongoRepository<Beer>, BeerRepoInterface {

    fun onStart(@Observes ev: StartupEvent?) {

        val list = mutableListOf<Beer>()
        // list.add(Faked.genRawEntity())
        persist(list)

        // ***ONLY FOR TESTING. I am not sure if I should include this because
        // the specifications say 1000 entries, but I include so that the postman
        // tests are reproducible from other machines
        // val beer: Beer? = findById(ObjectId(Faked.FAKE_ID))
        //if (beer == null) persist(Faked.genTestBeer(Faked.FAKE_ID))

    }


    //CREATE
    override fun _create(beer: Beer) {
        this.persist(beer)
    }

    override fun _create(beers: List<Beer>) {
        this.persist(beers)
    }

    //READ
    override fun _readById(id: String): Beer {

        val beerId = ObjectId(id)

        return this.findById(beerId) ?: throw NotFoundException("No beer with that ID")
    }

    override fun _readAll(): List<Beer> {
        return this.listAll()
    }


    //UPDATE
    override fun _update(updatedBeer: Beer) {
        this.update(updatedBeer)
    }


    //DELETE
    override fun _deleteById(id: String) {
        val beerId = ObjectId(id)
        this.deleteById(beerId)
    }

    override fun _deleteAll() {
        this.deleteAll()
    }


    //COUNT
    override fun _count(): Long {
        return this.count()
    }

    override fun _findAll(): PanacheQuery<Beer>? {
        return this.findAll()
    }


}
