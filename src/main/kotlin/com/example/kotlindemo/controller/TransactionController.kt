package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Transaction
import com.example.kotlindemo.repository.TransactionRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponses
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.Api

@Api(value = "TransactionsRestController", description = "Restful APIs related to transactions")
@RestController
@RequestMapping("/transactions")
class TransactionController(private val transactionRepository: TransactionRepository) {

    @GetMapping("/")
    fun getAllTransactions(): List<Transaction> =
            transactionRepository.findAll()


    @PostMapping("/")
    fun createNewTransaction(@Valid @RequestBody transaction: Transaction): Transaction =
            transactionRepository.save(transaction)


    @GetMapping("/{id}")
    fun getTransactionById(@PathVariable(value = "id") transactionId: Long): ResponseEntity<Transaction> {
        return transactionRepository.findById(transactionId).map { transaction ->
            ResponseEntity.ok(transaction)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/{id}")
    fun updateTransactionById(@PathVariable(value = "id") transactionId: Long,
                          @Valid @RequestBody newTransaction: Transaction): ResponseEntity<Transaction> {

        return transactionRepository.findById(transactionId).map { existingTransaction ->
            val updatedTransaction: Transaction = existingTransaction
                    .copy(senderIBAN = newTransaction.senderIBAN, recieverIBAN = newTransaction.recieverIBAN, amount = newTransaction.amount)

            ResponseEntity.ok().body(transactionRepository.save(updatedTransaction))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/{id}")
    fun deleteTransactionById(@PathVariable(value = "id") transactionId: Long): ResponseEntity<Void> {

        return transactionRepository.findById(transactionId).map { transaction ->
            transactionRepository.delete(transaction)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}