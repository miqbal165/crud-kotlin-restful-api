package iqbal.dot.dev.kotlin.restful.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "api_keys")
data class ApiKey(
    @Id
    val id: String = UUID.randomUUID().toString()
)
