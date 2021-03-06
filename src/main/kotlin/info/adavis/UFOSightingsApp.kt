package info.adavis

import info.adavis.dao.importData
import info.adavis.di.mainModule
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.locations.Locations
import org.koin.Koin
import org.koin.log.PrintLogger
import org.koin.standalone.StandAloneContext.startKoin

@Suppress("unused")
fun Application.main() {
    Koin.logger = PrintLogger()
    startKoin(listOf(mainModule))

    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    importData()

    log.info("Application setup complete")
}
