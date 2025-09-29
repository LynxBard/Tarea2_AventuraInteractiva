package com.example.t2_aventurainteractiva

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ComponentData(
    val id: String,
    val nombre: String,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int
)

object ComponentRepository {
    val CPU_SOCKET = ComponentData(
        id = "cpu_socket",
        nombre = "CPU Socket",
        imageRes = R.drawable.cpu_detail,
        descriptionRes = R.string.cpu_description
    )

    val RAM_SLOTS = ComponentData(
        id = "ram_slots",
        nombre = "RAM Slots",
        imageRes = R.drawable.ram_detail,
        descriptionRes = R.string.ram_description
    )

    val PCIE_SLOTS = ComponentData(
        id = "pcie_slots",
        nombre = "PCIe Slots",
        imageRes = R.drawable.pcie_detail,
        descriptionRes = R.string.pcie_description
    )

    private val ALL = listOf(CPU_SOCKET, RAM_SLOTS, PCIE_SLOTS)

    fun findById(id: String?): ComponentData? = ALL.find { it.id == id }
}
