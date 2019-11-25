package com.kotlincon.webappdemo

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ContactRepository : ReactiveCrudRepository<Contact, Long>