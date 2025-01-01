package iqbal.dot.dev.kotlin.restful.model

data class ListProductResponse(
    val totalData: Long,
    val totalPage: Int,
    val page: Int,
    val size: Int,
    val data: List<ProductResponse>
)
