package com.github.jesty.reactivekotlin

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ContactRepository : ReactiveCrudRepository<Contact, Long>