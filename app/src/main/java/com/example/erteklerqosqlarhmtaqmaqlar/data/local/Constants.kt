package com.example.erteklerqosqlarhmtaqmaqlar.data.local

import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.RecoPicData
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

object Constants {
    val items = arrayOf(
        CbnMenuItem(
            R.drawable.ic_ertek,
            R.drawable.avd_ertek,
            R.id.ertekFragment
        ),
        CbnMenuItem(
            R.drawable.ic_qosiq,
            R.drawable.avd_qosiq,
            R.id.qosiqFragment
        ),
        CbnMenuItem(
            R.drawable.ic_taqmaq,
            R.drawable.avd_taqmaq,
            R.id.taqmaqFragment
        )
    )

    val recomendations = listOf(
        //0-id degi ertek  (Ǵarǵa menen shegirtke)
        listOf(

            RecoPicData(0, R.drawable.garga_menen_shigirtke_8, 0),
            RecoPicData(0, R.drawable.garga_menen_shigirtke_9, 30),
            RecoPicData(0, R.drawable.garga_menen_shigirtke_1, 65),
            RecoPicData(0, R.drawable.garga_menen_shigirtke_10, 86),
            RecoPicData(0, R.drawable.garga_menen_shigirtke_3, 98),
            RecoPicData(0, R.drawable.garga_menen_shigirtke_6, 118),
            RecoPicData(0, R.drawable.garga_menen_shigirtke_7, 128),
            RecoPicData(0, R.drawable.garga_menen_shigirtke_9, 138),
            RecoPicData(0, R.drawable.garga_menen_shigirtke_5, 154),
            RecoPicData(0, R.drawable.garga_menen_shigirtke_2, 164),
            RecoPicData(0, R.drawable.garga_menen_shigirtke_4, 204),
            RecoPicData(0, R.drawable.garga_menen_shigirtke_9, 226),


            ),
        //1-id degi ertek(тулки менен кораз)
        listOf(
            RecoPicData(0, R.drawable.tulki_menen_qoraz_8, 0),
            RecoPicData(0, R.drawable.tulki_menen_qoraz_7, 4),
            RecoPicData(0, R.drawable.tulki_menen_qoraz_5, 14),
            RecoPicData(0, R.drawable.tulki_menen_qoraz_6, 32),
            RecoPicData(0, R.drawable.tulki_menen_qoraz_10, 45),
            RecoPicData(0, R.drawable.tulki_menen_qoraz_5, 60),
            RecoPicData(0, R.drawable.tulki_menen_qoraz_4, 88),
            RecoPicData(0, R.drawable.tulki_menen_qoraz_3, 106),
            RecoPicData(0, R.drawable.tulki_menen_qoraz_1, 124),
            RecoPicData(0, R.drawable.tulki_menen_qoraz_2, 130),
            RecoPicData(0, R.drawable.tulki_menen_qoraz_9, 140),

            ),
        //2-id degi ertek(Aqıllı otınshı)
        listOf(
            RecoPicData(0, R.drawable.akilli_otinshi_2, 0),
            RecoPicData(0, R.drawable.akilli_otinshi_1, 30),
            RecoPicData(0, R.drawable.akilli_otinshi_5, 76),
            RecoPicData(0, R.drawable.akilli_otinshi_4, 88),
            RecoPicData(0, R.drawable.akilli_otinshi_3, 96),
        ),


        //3-id degi ertek(Daw menen bala)
        listOf(
            RecoPicData(0, R.drawable.dau_menen_bala_1, 0),
            RecoPicData(0, R.drawable.dau_menen_bala_2, 28),
            RecoPicData(0, R.drawable.dau_menen_bala_3, 70),
            RecoPicData(0, R.drawable.dau_menen_bala_4, 108),
            RecoPicData(0, R.drawable.dau_menen_bala_5, 148),
            RecoPicData(0, R.drawable.dau_menen_bala_6, 197),
            RecoPicData(0, R.drawable.dau_menen_bala_7, 222),
            RecoPicData(0, R.drawable.dau_menen_bala_8, 254),
            RecoPicData(0, R.drawable.dau_menen_bala_9, 266),
            RecoPicData(0, R.drawable.dau_menen_bala_10, 268),

            ),


        //4-id degi ertek(duzaqshi garri)
        listOf(
            RecoPicData(0, R.drawable.duzaqshi_garri_1, 0),
            RecoPicData(0, R.drawable.duzaqshi_garri_2, 53),
            RecoPicData(0, R.drawable.duzaqshi_garri_3, 83),
            RecoPicData(0, R.drawable.duzaqshi_garri_4, 103),
            RecoPicData(0, R.drawable.duzaqshi_garri_5, 141),
            RecoPicData(0, R.drawable.duzaqshi_garri_6, 169),
            RecoPicData(0, R.drawable.duzaqshi_garri_7, 192),
            RecoPicData(0, R.drawable.duzaqshi_garri_8, 216),
            RecoPicData(0, R.drawable.duzaqshi_garri_9, 245),

            ),


        //5-id degi ertek(jigit_ham_qarligash)
        listOf(
            RecoPicData(0, R.drawable.jigit_ham_qarligash_1, 0),
            RecoPicData(0, R.drawable.jigit_ham_qarligash_2, 48),
            RecoPicData(0, R.drawable.jigit_ham_qarligash_3, 54),
            RecoPicData(0, R.drawable.jigit_ham_qarligash_4, 69),
            RecoPicData(0, R.drawable.jigit_ham_qarligash_5, 100),
            RecoPicData(0, R.drawable.jigit_ham_qarligash_6, 113),
            RecoPicData(0, R.drawable.jigit_ham_qarligash_7, 129),
            RecoPicData(0, R.drawable.jigit_ham_qarligash_8, 143),
            RecoPicData(0, R.drawable.jigit_ham_qarligash_9, 161),
            RecoPicData(0, R.drawable.jigit_ham_qarligash_10, 170),

            ),

        //6-id degi ertek(Iyttin_nesiybesi)
        listOf(
            RecoPicData(0, R.drawable.iyttin_nesiybesi_1, 0),
            RecoPicData(0, R.drawable.iyttin_nesiybesi_2, 38),
            RecoPicData(0, R.drawable.iyttin_nesiybesi_3, 53),
            RecoPicData(0, R.drawable.iyttin_nesiybesi_4, 69),
            RecoPicData(0, R.drawable.iyttin_nesiybesi_5, 81),
            RecoPicData(0, R.drawable.iyttin_nesiybesi_6, 106),
            RecoPicData(0, R.drawable.iyttin_nesiybesi_7, 153),
            RecoPicData(0, R.drawable.iyttin_nesiybesi_8, 200),
            RecoPicData(0, R.drawable.iyttin_nesiybesi_9, 217),
            RecoPicData(0, R.drawable.iyttin_nesiybesi_10, 231),
            RecoPicData(0, R.drawable.iyttin_nesiybesi_11, 263),
            RecoPicData(0, R.drawable.iyttin_nesiybesi_12, 285),
            RecoPicData(0, R.drawable.iyttin_nesiybesi_13, 311),
            RecoPicData(0, R.drawable.iyttin_nesiybesi_14, 350),

            ),

        //7-id degi ertek(arzu_armanina_jetken_jigit)
        listOf(
            RecoPicData(0, R.drawable.arzu_armanina_jetken_jigit_1, 0),
            RecoPicData(0, R.drawable.arzu_armanina_jetken_jigit_2, 40),
            RecoPicData(0, R.drawable.arzu_armanina_jetken_jigit_3, 106),
            RecoPicData(0, R.drawable.arzu_armanina_jetken_jigit_4, 168),
            RecoPicData(0, R.drawable.arzu_armanina_jetken_jigit_5, 211),
            RecoPicData(0, R.drawable.arzu_armanina_jetken_jigit_6, 236),
            RecoPicData(0, R.drawable.arzu_armanina_jetken_jigit_7, 271),
            RecoPicData(0, R.drawable.arzu_armanina_jetken_jigit_8, 317),
            RecoPicData(0, R.drawable.arzu_armanina_jetken_jigit_9, 395),
            RecoPicData(0, R.drawable.arzu_armanina_jetken_jigit_10, 441),

            ),

        //8-id degi ertek(ilimpaz bala)
        listOf(
            RecoPicData(0, R.drawable.ilimpaz_1, 0),
            RecoPicData(0, R.drawable.ilimpaz_2, 37),
            RecoPicData(0, R.drawable.ilimpaz_3, 51),
            RecoPicData(0, R.drawable.ilimpaz_4, 75),
            RecoPicData(0, R.drawable.ilimpaz_5, 156),
            RecoPicData(0, R.drawable.ilimpaz_6, 171),
            RecoPicData(0, R.drawable.ilimpaz_7, 196),
            RecoPicData(0, R.drawable.ilimpaz_8, 236),
            RecoPicData(0, R.drawable.ilimpaz_9, 254),
            RecoPicData(0, R.drawable.ilimpaz_10, 291),

            ),


        //9-id degi ertek(Aqıllı serke hám aqmaq arıslan)
        listOf(
            RecoPicData(0, R.drawable.aqilli_serke_ham_aqmaq_arislan_1, 0),
            RecoPicData(0, R.drawable.aqilli_serke_ham_aqmaq_arislan_2, 28),
            RecoPicData(0, R.drawable.aqilli_serke_ham_aqmaq_arislan_3, 41),
            RecoPicData(0, R.drawable.aqilli_serke_ham_aqmaq_arislan_4, 54),
            RecoPicData(0, R.drawable.aqilli_serke_ham_aqmaq_arislan_5, 71),
            RecoPicData(0, R.drawable.aqilli_serke_ham_aqmaq_arislan_6, 89),
            RecoPicData(0, R.drawable.aqilli_serke_ham_aqmaq_arislan_7, 100),
            RecoPicData(0, R.drawable.aqilli_serke_ham_aqmaq_arislan_8, 126),
            RecoPicData(0, R.drawable.aqilli_serke_ham_aqmaq_arislan_9, 151),
            RecoPicData(0, R.drawable.aqilli_serke_ham_aqmaq_arislan_10, 169),
            ),


        //10-id degi ertek(Dunyanin zori)
        listOf(
            RecoPicData(0, R.drawable.dunyanin_zori_1, 0),
            RecoPicData(0, R.drawable.dunyanin_zori_2, 16),
            RecoPicData(0, R.drawable.dunyanin_zori_3, 31),
            RecoPicData(0, R.drawable.dunyanin_zori_4, 37),
            RecoPicData(0, R.drawable.dunyanin_zori_5, 46),
            RecoPicData(0, R.drawable.dunyanin_zori_6, 54),
            RecoPicData(0, R.drawable.dunyanin_zori_7, 65),
            RecoPicData(0, R.drawable.dunyanin_zori_8, 73),
            RecoPicData(0, R.drawable.dunyanin_zori_9, 86),
            ),


        //11-id degi ertek(Jetim bala)
        listOf(
            RecoPicData(0, R.drawable.jetim_bala_1, 0),
            RecoPicData(0, R.drawable.jetim_bala_2, 51),
            RecoPicData(0, R.drawable.jetim_bala_3, 83),
            RecoPicData(0, R.drawable.jetim_bala_4, 103),
            RecoPicData(0, R.drawable.jetim_bala_5, 117),
        ),


        //12-id degi ertek(Qıs penen jaz qalay jánjellesti)
        listOf(
            RecoPicData(0, R.drawable.qis_penen_jaz_qalay_janjellesti_1, 0),
            RecoPicData(0, R.drawable.qis_penen_jaz_qalay_janjellesti_2, 15),
            RecoPicData(0, R.drawable.qis_penen_jaz_qalay_janjellesti_3, 70),
            RecoPicData(0, R.drawable.qis_penen_jaz_qalay_janjellesti_4, 109),
            RecoPicData(0, R.drawable.qis_penen_jaz_qalay_janjellesti_5, 153),
        ),



        )

    val recoPics = listOf(
        RecoPicData(0, R.drawable.akilli_otinshi_1, 0),
        RecoPicData(0, R.drawable.akilli_otinshi_2, 5000),
        RecoPicData(0, R.drawable.akilli_otinshi_3, 10000),
        RecoPicData(0, R.drawable.akilli_otinshi_4, 20000),
    )

    const val BASE_URL = "https://fcm.googleapis.com"
    const val SERVER_KEY =
        "key=AAAAs8fS-OM:APA91bEtkezBMww3T-2Qj2iEUZ0R_dKNlt86Zyetm-H0cJX6bdsKXW6hY2qK4kAOixtpfYUrRpP4ifMMg8HQESYwRW_P8UUBqs-DKRWELIJijrSay26-n-pV6ZGQCch0NUalvugap1_m"
//    const val ADMIN_TOKEN = "drZXDR8ATcmjxqTb3c_nQv:APA91bE6ulgjPr6fZcfv5vF7e1_zNhsY4sjUSUZrUWikhv_fl7ePkzsRCAg5iHb0Ytpjy5GxKUx2EFfoHDE0weo7vjRPcbhJADsJPA1qMu9ukeIxmfdhDhWSNyeqzBUYmti1WCXIiIxV"

//    Damirdiki
//        const val ADMIN_TOKEN = "eDeqbniGS0qGeEoscR2YbO:APA91bFglHgoJRkD7sCXWtioB-_fF_yabh1kKS9DDcVz7rtwxwUUUcL0JR7jeUszKaFQQh5npr_e3lt7yn3ziIwv7cKSq_POSBb4EDFXWZIpkfdSoC5Cv6gOMglEK61AmYTcD3jz0TeM"

    //Ruslandiki
    const val ADMIN_TOKEN =
        "eDeqbniGS0qGeEoscR2YbO:APA91bFglHgoJRkD7sCXWtioB-_fF_yabh1kKS9DDcVz7rtwxwUUUcL0JR7jeUszKaFQQh5npr_e3lt7yn3ziIwv7cKSq_POSBb4EDFXWZIpkfdSoC5Cv6gOMglEK61AmYTcD3jz0TeM"
//        "d2j_1LmNpPC_Y0c2iFCaSY:APA91bGGtBF8qbIvK9OnmEQtmXn556x8jL2trMeYuEGuovuma-uZpv4l7VkPPIIIuGywVIeITo6JhWITbrr24AXZqqXd2ktbkbk-my-3exmZP9vcjfF1ebSgmFjPybXb0SKv3WT2wd9x"
//        "eblSZe08Q2ejNJrbiisoiK:APA91bEPVifhEHYcbb-e_aLevAKaaWaglRSTrO_nQfgiltTfPelBja869XwxIFT9cOL3R29vS0HxvkMvPzeBiCKb44eDGuIeb-7di2A5EP6342hpYK9urcrI465-G_AROCwAWHiWVatt"
//        "fPWm0JBRT7qrc4gVGoiPF7:APA91bFKdMx51iO398QNclh7l6H-IDsSJHNbIf9PejDfc415NXaJhI4j8fQhVgh2UHoUKQIDitpeUiqF9BAlBDrk7DR2Wl5WbxT_m1KwrNJUNyCuAbkvvem1H955oHRQsnckQY3SwH0C"
}