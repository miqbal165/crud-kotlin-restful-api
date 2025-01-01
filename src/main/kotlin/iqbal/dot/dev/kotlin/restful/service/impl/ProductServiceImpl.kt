package iqbal.dot.dev.kotlin.restful.service.impl

import iqbal.dot.dev.kotlin.restful.entity.Product
import iqbal.dot.dev.kotlin.restful.exception.NotFoundException
import iqbal.dot.dev.kotlin.restful.model.*
import iqbal.dot.dev.kotlin.restful.repository.ProductRepository
import iqbal.dot.dev.kotlin.restful.service.ProductService
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
) : ProductService {
    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        val product = Product(
            name = createProductRequest.name!!,
            price = createProductRequest.price!!,
            quantity = createProductRequest.quantity!!,
            createdAt = Date(),
            updateAt = null
        )

        productRepository.save(product)
        return convertProductToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = productRepository.findByIdOrNull(id) ?:
            throw NotFoundException("Product with id $id is not found")

        return convertProductToProductResponse(product)
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = productRepository.findByIdOrNull(id) ?:
            throw NotFoundException("Product with id $id is not found")

        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            updateAt = Date()
        }

        productRepository.save(product)
        return convertProductToProductResponse(product)
    }

    override fun delete(id: String) {
        val product = productRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Product with id $id is not found")

        productRepository.delete(product)
    }

    override fun list(listProductRequest: ListProductRequest): ListProductResponse {
        val page = if (listProductRequest.page > 0) listProductRequest.page - 1 else 0
        val size = if (listProductRequest.size > 0) listProductRequest.size else 10
        val pageable = productRepository.findAll(PageRequest.of(page, size))

        val products = pageable.toList()

        return ListProductResponse(
            totalData = pageable.totalElements,
            totalPage = pageable.totalPages,
            page = page + 1,
            size = size,
            data = products.map(::convertProductToProductResponse)
        )
    }

    private fun convertProductToProductResponse(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updateAt = product.updateAt
        )
    }
}