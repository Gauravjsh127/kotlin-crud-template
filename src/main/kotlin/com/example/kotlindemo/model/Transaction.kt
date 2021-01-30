package com.example.kotlindemo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class Transaction (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get: NotBlank
    val senderIBAN: String = "",

    @get: NotBlank
    val recieverIBAN: String = ""

)