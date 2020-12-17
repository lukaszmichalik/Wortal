package com.okta.Wortal.repo

import com.okta.Wortal.model.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<Users, Int> {
    fun findByEmailAndPassword(email: String, password: String): Users?
    fun findByEmail(email: String): Users?
}