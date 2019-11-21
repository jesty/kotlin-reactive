package com.kotlincon.mvcdemo

import org.springframework.data.repository.CrudRepository

interface ContactRepository : CrudRepository<Contact, Int>