package com.github.jesty.reactivekotlin

import org.springframework.data.repository.CrudRepository

interface ContactRepository : CrudRepository<Contact, Int>