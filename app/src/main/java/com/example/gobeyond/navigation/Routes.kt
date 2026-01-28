package com.example.gobeyond.navigation

object Routes {
    const val MAIN = "main"
    const val EXPLORE = "explore"
    const val LIST = "list"
    const val COUNTRY = "country/{countryId}"
    const val DESTINATION = "destination/{destinationId}"

    fun country(countryId: String) = "country/$countryId"

    fun destination(destinationId: String) = "destination/$destinationId"
}
