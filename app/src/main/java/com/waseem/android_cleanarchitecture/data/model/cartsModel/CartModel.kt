package com.waseem.android_cleanarchitecture.data.model.cartsModel

import com.google.gson.annotations.SerializedName


data class CartsModel (

    @SerializedName("carts" ) var carts : ArrayList<Carts> = arrayListOf(),
    @SerializedName("total" ) var total : Int?             = null,
    @SerializedName("skip"  ) var skip  : Int?             = null,
    @SerializedName("limit" ) var limit : Int?             = null

)

data class Products (

    @SerializedName("id"                 ) var id                 : Int?    = null,
    @SerializedName("title"              ) var title              : String? = null,
    @SerializedName("price"              ) var price              : Double? = null,
    @SerializedName("quantity"           ) var quantity           : Int?    = null,
    @SerializedName("total"              ) var total              : Double? = null,
    @SerializedName("discountPercentage" ) var discountPercentage : Double? = null,
    @SerializedName("discountedTotal"    ) var discountedTotal    : Double? = null,
    @SerializedName("thumbnail"          ) var thumbnail          : String? = null

)
data class Carts (

    @SerializedName("id"              ) var id              : Int?                = null,
    @SerializedName("products"        ) var products        : ArrayList<Products> = arrayListOf(),
    @SerializedName("total"           ) var total           : Double?             = null,
    @SerializedName("discountedTotal" ) var discountedTotal : Double?             = null,
    @SerializedName("userId"          ) var userId          : Int?                = null,
    @SerializedName("totalProducts"   ) var totalProducts   : Int?                = null,
    @SerializedName("totalQuantity"   ) var totalQuantity   : Int?                = null

)