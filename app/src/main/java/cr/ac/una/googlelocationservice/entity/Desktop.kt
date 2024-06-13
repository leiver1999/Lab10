package cr.ac.una.googlelocationservice.entity

import java.io.Serializable

data class Desktop(
    val page: String? = null,
    val revisions: String? = null,
    val edit: String? = null,
    val talk: String? = null
) : Serializable