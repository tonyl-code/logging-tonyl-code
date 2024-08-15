package edu.uchicago.gerber.quark._01resources

import edu.uchicago.gerber.quark._02services.BeerService
import edu.uchicago.gerber.quark._04models.Beer
import edu.uchicago.gerber.quark._04models.Faked
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.bson.types.ObjectId
import java.lang.Exception

@Path("/beers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class BeerResource {

    @Inject
    lateinit var beerService: BeerService

    val TOTAL_PER_PAGE = 20

    @POST
    fun create(beer: Beer): Beer {
        beerService.create(beer)
        return beer
    }

    @GET
    fun readAll(): List<Beer>{
        return beerService.readAll()
    }

    @GET
    @Path("{id}")
    fun readById(@PathParam("id") id: String): Beer {
        return beerService.readById(id)

    }

    @GET
    @Path("/paged/{page}")
    fun paged(@PathParam("page") page: Int): List<Beer> {
        val pagedBeers: PanacheQuery<Beer>? = beerService.findAll()
        return pagedBeers?.page(page, TOTAL_PER_PAGE)?.list() ?: throw Exception("Paged query returned null")
    }

    @PUT
    @Path("/{id}")
    fun update(@PathParam("id") id: String, updatedBeer: Beer): Beer {
        // Ensure we can find the beer with id.
        beerService.readById(id)

        beerService.update(updatedBeer)

        return updatedBeer
    }

    @DELETE
    @Path("/{id}")
    fun deleteById(@PathParam("id")id: String): Beer{
        val beer = beerService.readById(id)
        beerService.deleteById(id)
        return beer
    }


}