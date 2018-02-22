package com.ivanebernal.dogapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by iesparza on 2/21/18.
 */
data class DogImage(
        @SerializedName("status") @Expose val status: String,
        @SerializedName("message") @Expose val message: String
)