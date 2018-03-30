package com.projectx.fisioapp.domain.model

import java.util.*

open class Appointment (val id: String,
                        val servicePrice: String,
                        val customerName: String,
                        val isConfirmed: Boolean,
                        val isCancelled: Boolean,
                        val date: Date,
                        val latitude: String,
                        val longitude: String,
                        val extraInfo: String
                        )
