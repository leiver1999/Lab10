package cr.ac.una.googlelocationservice.entity

import java.io.Serializable

data class Page(
    val pageid: Int? = null,
    val ns: Int? = null,
    val index: Int? = null,
    val type: String? = null,
    val title: String? = null,
    val displaytitle: String? = null,
    val namespace: Namespace? = null,
    val wikibase_item: String? = null,
    val titles: Titles? = null,
    val thumbnail: Image? = null,
    val originalimage: Image? = null,
    val lang: String? = null,
    val dir: String? = null,
    val revision: String? = null,
    val tid: String? = null, //uuid?
    val timestamp: String? = null, //date?
    val description: String? = null,
    val description_source: String? = null,
    val content_urls: ContentUrls? = null,
    val extract: String? = null,
    val extract_html: String? = null,
    val normalizedtitle: String? = null
) : Serializable