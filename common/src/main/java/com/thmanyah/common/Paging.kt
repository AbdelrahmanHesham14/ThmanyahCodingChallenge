package com.thmanyah.common

data class Paging<T>(val data: T, val totalPages: Int, val currentPage: Int)