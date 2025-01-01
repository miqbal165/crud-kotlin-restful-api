package iqbal.dot.dev.kotlin.restful.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "m_product")
data class Product(
    @Id
    var id: String = UUID.randomUUID().toString(),

    @Column(name = "name")
    var name: String,

    @Column(name = "price")
    var price: Long,

    @Column(name = "quantity")
    var quantity: Int,

    @Column(name = "created_at")
    val createdAt: Date,

    @Column(name = "update_at")
    var updateAt: Date?
)
