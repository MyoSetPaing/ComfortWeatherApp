package com.myosetpaing.domain.usecase

interface SuspendUseCase<Input, Output> {
   suspend fun invoke(input: Input): Output
}