package com.example.utils

sealed class State<T>

open class StateSuccess<T>(val data: T) : State<T>()
open class StateError<T>(val errorData: Error? = null) : State<T>()
open class StatePaginatingError<T>(val errorData: Error? = null) : State<T>()
open class StatePaginatingLoading<T> : State<T>()
open class StateLoading<T>(val loading: Boolean) : State<T>()