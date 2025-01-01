package iqbal.dot.dev.kotlin.restful.service

import iqbal.dot.dev.kotlin.restful.model.*

interface ProductService {
    fun create(createProductRequest: CreateProductRequest): ProductResponse
    fun get(id: String): ProductResponse
    fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse
    fun delete(id: String)
    fun list(listProductRequest: ListProductRequest): ListProductResponse
}