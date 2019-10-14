package com.antonpopoff.testproj.utils.viewstate

sealed class Model<T>

class Loading<T> : Model<T>()

class Empty<T> : Model<T>()

class Error<T>(val throwable: Throwable) : Model<T>()

class Data<T>(val data: T) : Model<T>()
