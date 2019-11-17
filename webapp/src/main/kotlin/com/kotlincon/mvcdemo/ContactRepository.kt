package com.kotlincon.mvcdemo

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ContactRepository : ReactiveCrudRepository<Contact, Long>