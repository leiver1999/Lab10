package cr.ac.una.googlelocationservice.entity

data class WikipediaResponse(
    val pages: ArrayList<Page>? = null,
    val type: String? = null,
    val title: String? = null,
    val method: String? = null,
    val detail: String? = null,
    val uri: String? = null
)