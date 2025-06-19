package com.thmanyah.domain.model

enum class SectionType(val value: String) {
    SQUARE("square"),
    TWO_LINES_GRID("2_lines_grid"),
    BIG_SQUARE("big_square"),
    QUEUE("queue");

    companion object {
        fun from(value: String?): SectionType {
            // The API is inconsistent with section type values (e.g., "big_square" vs. "big square"),
            // so a default value is used to handle unknown or mismatched types gracefully.
            return SectionType.values().firstOrNull { it.value == value } ?: SQUARE
        }
    }
}