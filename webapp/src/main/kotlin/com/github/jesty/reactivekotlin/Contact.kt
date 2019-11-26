package com.github.jesty.reactivekotlin

import org.springframework.data.annotation.Id

data class Contact(@Id var id: Int? = null,
                   val name: String,
                   val surname: String
)