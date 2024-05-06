package com.vengeful.utils


    fun checkForValid(string: String): Boolean {
        return if (Regex("""/\A[^@]+@([^@\.]+\.)+[^@\.]+\z/""").matches(string)){
            true
        } else false
    }
