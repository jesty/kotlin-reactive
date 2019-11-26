package com.github.jesty.reactivekotlin

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Contact(@Id @GeneratedValue var id: Long? = null,
                   val name: String,
                   val surname: String
)