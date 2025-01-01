package iqbal.dot.dev.kotlin.restful.controller

import iqbal.dot.dev.kotlin.restful.model.*
import iqbal.dot.dev.kotlin.restful.service.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productService: ProductService
) {
    @PostMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun createProduct(
        @Valid @RequestBody body: CreateProductRequest
    ): ResponseEntity<WebResponse<ProductResponse>> {
        val productResponse = productService.create(body)

        return ResponseEntity(
            WebResponse(
                code = HttpStatus.CREATED.value(),
                status = "CREATED",
                data = productResponse
            ),
            HttpStatus.CREATED
        )
    }

    @GetMapping(
        value = ["/{product_id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getProduct(@PathVariable("product_id") id: String):
            ResponseEntity<WebResponse<ProductResponse>> {
        val productResponse = productService.get(id)

        return ResponseEntity(
            WebResponse(
                code = HttpStatus.OK.value(),
                status = "OK",
                data = productResponse
            ),
            HttpStatus.OK
        )
    }

    @PutMapping(
        value = ["/{product_id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateProduct(
        @PathVariable("product_id") id: String,
        @Valid @RequestBody updateProductRequest: UpdateProductRequest
    ): ResponseEntity<WebResponse<ProductResponse>> {

        val response = productService.update(id, updateProductRequest)

        return ResponseEntity(
            WebResponse(
                code = HttpStatus.OK.value(),
                status = "UPDATE",
                data = response
            ),
            HttpStatus.OK
        )
    }

    @DeleteMapping(
        value = ["/{product_id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun deleteProduct(@PathVariable("product_id") id: String):
            ResponseEntity<WebResponse<ProductResponse?>> {
        productService.delete(id)

        return ResponseEntity(
            WebResponse(
                code = HttpStatus.OK.value(),
                status = "DELETE",
                data = null
            ),
            HttpStatus.OK
        )
    }

    @GetMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getAllProducts(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int):
            ResponseEntity<WebResponse<ListProductResponse>> {
        val request = ListProductRequest(size, page)
        val responses = productService.list(request)

        return ResponseEntity(
            WebResponse(
                code = HttpStatus.OK.value(),
                status = "OK",
                data = responses
            ),
            HttpStatus.OK
        )
    }
}