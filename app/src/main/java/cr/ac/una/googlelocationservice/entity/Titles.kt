package cr.ac.una.googlelocationservice.entity

import java.io.Serializable

data class Titles(
    val canonical: String? = null,
    val normalized: String? = null,
    val display: String? = null
) : Serializable