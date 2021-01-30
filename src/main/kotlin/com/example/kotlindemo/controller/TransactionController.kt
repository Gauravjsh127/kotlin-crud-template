package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Transaction
import com.example.kotlindemo.repository.TransactionRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class TransactionController(private val transactionRepository: TransactionRepository) {

    @GetMapping("/transactions")
    fun getAllTransactions(): List<Transaction> =
            transactionRepository.findAll()


    @PostMapping("/transactions")
    fun createNewTransaction(@Valid @RequestBody transaction: Transaction): Transaction =
            transactionRepository.save(transaction)


    @GetMapping("/transactions/{id}")
    fun getTransactionById(@PathVariable(value = "id") transactionId: Long): ResponseEntity<Transaction> {
        return transactionRepository.findById(transactionId).map { transaction ->
            ResponseEntity.ok(transaction)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/transactions/{id}")
    fun updateTransactionById(@PathVariable(value = "id") transactionId: Long,
                          @Valid @RequestBody newTransaction: Transaction): ResponseEntity<Transaction> {

        return transactionRepository.findById(transactionId).map { existingTransaction ->
            val updatedTransaction: Transaction = existingTransaction
                    .copy(senderIBAN = newTransaction.senderIBAN, recieverIBAN = newTransaction.recieverIBAN)

            ResponseEntity.ok().body(transactionRepository.save(updatedTransaction))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/transactions/{id}")
    fun deleteTransactionById(@PathVariable(value = "id") transactionId: Long): ResponseEntity<Void> {

        return transactionRepository.findById(transactionId).map { transaction ->
            transactionRepository.delete(transaction)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}