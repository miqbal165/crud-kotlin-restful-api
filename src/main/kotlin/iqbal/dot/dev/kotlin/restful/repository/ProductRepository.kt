package iqbal.dot.dev.kotlin.restful.repository

import iqbal.dot.dev.kotlin.restful.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, String>