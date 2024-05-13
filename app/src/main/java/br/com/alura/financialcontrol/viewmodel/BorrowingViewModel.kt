package br.com.alura.financialcontrol.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.financialcontrol.integration.dtos.request.PayParcelBorrowingRequestDTO
import br.com.alura.financialcontrol.integration.dtos.response.BorrowingResponseDTO
import br.com.alura.financialcontrol.integration.network.Result
import br.com.alura.financialcontrol.integration.repositories.BorrowingRepository

class BorrowingViewModel(private val repository: BorrowingRepository) : ViewModel() {
    fun findAll(
    ): LiveData<Result<List<BorrowingResponseDTO>?>> =
        repository.findAll()

    fun payParcel(
        id: String,
        request: PayParcelBorrowingRequestDTO
    ): LiveData<Result<Void?>> = repository.payParcel(id, request)
}
