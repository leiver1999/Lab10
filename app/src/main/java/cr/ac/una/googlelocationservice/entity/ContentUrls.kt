package cr.ac.una.googlelocationservice.entity

import java.io.Serializable

data class ContentUrls(
    val desktop: Desktop? = null, //device?
    val mobile: Mobile? = null  //device?
) : Serializable