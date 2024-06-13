package cr.ac.una.googlelocationservice.entity

import java.io.Serializable

data class Image(
    val source: String? = null,
    val width: Int? = null,
    val height: Int? = null
) : Serializable