package com.example.shopbasket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val cart = generateCart()
        val shipping = 1960.00
        val tax = 8750.24
        val total = cart.sumOf{ it.price } + shipping + tax

        val subTotPrice = findViewById<TextView>(R.id.subtotal)
        subTotPrice.text = String.format("%.2f", cart.sumOf{ it.price })

        val shipPrice = findViewById<TextView>(R.id.shipping)
        shipPrice.text = String.format("%.2f", shipping)

        val taxPrice = findViewById<TextView>(R.id.tax)
        taxPrice.text = String.format("%.2f", tax)

        val tPrice = findViewById<TextView>(R.id.total)
        tPrice.text = String.format("%.2f", total)

        val itemCount = findViewById<TextView>(R.id.itemCount)
        itemCount.text = "${cart.count()} items in your cart"

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemCartAdapter(this, cart)
    }

    private fun generateCart(): List<ItemCart> {
        val randomValues = List(7) { Random.nextInt(0, 20) }
        return listOf(
            ItemCart(
                imageURL = "https://avatars.mds.yandex.net/get-verba/1030388/2a00000160957a4ba6ef2e891bad77d37f48/cattouch",
                productName = "Nissan GT-R",
                price = 7626.55,
                count = 0
            ),
            ItemCart(
                imageURL = "https://yastatic.net/naydex/autoru/h2jw6K616/e947d5SR/VsO2-mUF0_3L9JL2Va0kaT_GnFMG1_hqCd1uitB2mJvkZAQ2X9z_F7hj6YWcTxMnr6zyeHyIB1Z9UW5oSpVVYOayhv4QUcf8XnST57QdUHxqsh2yYxO5mpp_2EjTBCqZ56KnxE75W3JJF93D6o_j4C9Ys145fxPEX1dPaSvh88cEEUTLB1TgG-il43PEnjTJPsc5g9X0X5trDtj449V6ePeQlmXduRjzH0bPEpu4gVIPSGJfys0z5G5mD1paoWDktLB18",
                productName = "Rolls-Royce Ghost Black Badge II",
                price = 25000.0,
                count = randomValues[0]
            ),
            ItemCart(
                imageURL = "https://avatars.mds.yandex.net/get-autoru-vos/2174341/0c2896342feeba523b8bdf0625b66484/1200x900n",
                productName = "Volkswagen ID.4 Crozz",
                price = 2596.42,
                count = randomValues[1]
            ),
            ItemCart(
                imageURL = "https://avatars.mds.yandex.net/get-autoru-vos/3690741/a7d331137457cc85e943a78329fe9277/1200x900n",
                productName = "Mercedes-Benz G-Класс AMG Brabus",
                price = 60000.0,
                count = randomValues[2]
            ),
            ItemCart(
                imageURL = "https://avatars.mds.yandex.net/get-autoru-vos/4525519/3e52de4dce247b8de92ed2e5432cc3fd/1200x900n",
                productName = "ГАЗ М-20 «Победа»",
                price = 7100.0,
                count = randomValues[3]
            ),
            ItemCart(
                imageURL = "https://avatars.mds.yandex.net/get-autoru-vos/4338488/2dfc9875c351fbe5207a1035d9b8ca98/1200x900n",
                productName = "Rolls-Royce Phantom Long Extended VIII",
                price = 73000.0,
                count = randomValues[4]
            ),
            ItemCart(
                imageURL = "https://avatars.mds.yandex.net/get-verba/1030388/2a0000017f7078c337de81ac2482210a9744/cattouch",
                productName = "Exeed LX",
                price = 2439.9,
                count = randomValues[5]
            ),
            ItemCart(
                imageURL = "https://avatars.mds.yandex.net/get-verba/1030388/2a000001806f6227893f54dc807c242dfe87/cattouch",
                productName = "BMW X7 I(G07)",
                price = 17812.564,
                count = randomValues[6]
            )
        )
    }
}