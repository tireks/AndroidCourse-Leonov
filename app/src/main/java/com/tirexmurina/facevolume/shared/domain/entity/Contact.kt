package com.tirexmurina.facevolume.shared.domain.entity

data class Contact(
    val id : Long,
    val name : String,
    val pic : String? = null,
    val phone : String? = null,
    val email : String? = null,
    val location : Location? = null,
    val note : String? = null
) {
    data class Location(
        val country : String? = null,
        val city : String? = null,
        val address : String? = null,
        val timezone : Timezone? = null
    )

    enum class Timezone( val zone: Int, val zoneName: String) {
        MSK_B15(-15, "МСК -15"),
        MSK_B14(-14, "МСК -14"),
        MSK_B13(-13, "МСК -13"),
        MSK_B12(-12, "МСК -12"),
        MSK_B11(-11, "МСК -11"),
        MSK_B10(-10, "МСК -10"),
        MSK_B9(-9, "МСК -9"),
        MSK_B8(-8, "МСК -8"),
        MSK_B7(-7, "МСК -7"),
        MSK_B6(-6, "МСК -6"),
        MSK_B5(-5, "МСК -5"),
        MSK_B4(-4, "МСК -4"),
        MSK_B3(-3, "МСК -3"),
        MSK_B2(-2, "МСК -2"),
        MSK_B1(-1, "МСК -1"),
        MSK_0(0, "МСК 0"),
        MSK_1(1, "МСК +1"),
        MSK_2(2, "МСК +2"),
        MSK_3(3, "МСК +3"),
        MSK_4(4, "МСК +4"),
        MSK_5(5, "МСК +5"),
        MSK_6(6, "МСК +6"),
        MSK_7(7, "МСК +7"),
        MSK_8(8, "МСК +8"),
        MSK_9(9, "МСК +9"),
        MSK_10(10, "МСК +10"),
        MSK_11(11, "МСК +11")
    }
}