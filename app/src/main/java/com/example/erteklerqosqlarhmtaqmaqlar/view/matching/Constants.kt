package com.example.erteklerqosqlarhmtaqmaqlar.view.matching

import androidx.annotation.DrawableRes
import com.example.erteklerqosqlarhmtaqmaqlar.R

object Constants {

    val saykes = listOf(
        listOf(
            saykesModel(R.drawable.aqilli_otinshi_pers1, R.drawable.aqilli_otinshi_pers2),
            saykesModel(R.drawable.dau_menen_bala_1, R.drawable.dau_menen_bala_2),
            saykesModel(R.drawable.duzaqshi_garri_pers2, R.drawable.duzaqshi_garri_pers3),
            saykesModel(R.drawable.arzu_armanina_jetken_jigit_pers1,R.drawable.arzu_armanina_jetken_jigit_pers2)
        )
    )


    val saykes2 = listOf(
        listOf(
            saykesModel(R.drawable.garga, R.drawable.garga_menen_pers1),
            saykesModel(R.drawable.ilimpaz_pers1, R.drawable.ilimpaz_pers2),
            saykesModel(R.drawable.iyttin_nesiybesi_pers1, R.drawable.iyttin_nesiybesi_pers2),
            saykesModel(R.drawable.jigit_ham_qarligash_pers1,R.drawable.jigit_ham_qarligash_pers2)
        )
    )



    val saykes3 = listOf(
        listOf(
            saykesModel(R.drawable.qarga_shigiritke_sava, R.drawable.garga_menen_pers1),
            saykesModel(R.drawable.qoraz_menen_tulki_pers2, R.drawable.qoraz_menen_tulki_qoraz),
            saykesModel(R.drawable.aqilli_otinshi_pers3, R.drawable.aqilli_otinshi_pers4),
            saykesModel(R.drawable.arzu_armanina_jetken_jigit_pers3,R.drawable.arzu_armanina_jetken_jigit_pers4)
        )
    )


    val saykes4 = listOf(
        listOf(
            saykesModel(R.drawable.dau_menen_bala_pers2, R.drawable.dau_menen_bala_bala),
            saykesModel(R.drawable.duzaqshi_garri_pers3, R.drawable.duzaqshi_garri_pers2),
            saykesModel(R.drawable.ilimpaz_pers3, R.drawable.ilimpaz_pers4),
            saykesModel(R.drawable.iyttin_nesiybesi_pers2,R.drawable.iyttin_nesiybesi_pers3
            )
        )
    )


    val saykes5 = listOf(
        listOf(
            saykesModel(R.drawable.aqilli_otinshi_pers5, R.drawable.aqilli_otinshi_pers1),
            saykesModel(R.drawable.arzu_armanina_jetken_jigit_pers1, R.drawable.arzu_armanina_jetken_jigit_pers4),
            saykesModel(R.drawable.dau_menen_bala_bala, R.drawable.dau_menen_bala_pers2),
            saykesModel(R.drawable.ilimpaz_pers5,R.drawable.ilimpaz_pers4
            )
        )
    )


    val saykes6 = listOf(
        listOf(
            saykesModel(R.drawable.qis_penen_jaz_qalay_janjellesti_pers_2, R.drawable.qis_penen_jaz_qalay_janjellesti_pers_1),
            saykesModel(R.drawable.maqtanshaq_tishqan_pers_4, R.drawable.maqtanshaq_tishqan_pers_2),
            saykesModel(R.drawable.jolbaris_ham_eshek_pers_3, R.drawable.jolbaris_ham_eshek_pers_1),
            saykesModel(R.drawable.dunyanin_zori_pers_2,R.drawable.dunyanin_zori_pers_3
            )
        )
    )


    val saykes7 = listOf(
        listOf(
            saykesModel(R.drawable.jolbaris_ham_eshek_pers_2, R.drawable.jolbaris_ham_eshek_pers_1),
            saykesModel(R.drawable.jetim_bala_pers_1, R.drawable.jetim_bala_pers_2),
            saykesModel(R.drawable.ilimpaz_pers6, R.drawable.ilimpaz_pers4),
            saykesModel(R.drawable.awizbirlik_pers_2,R.drawable.awizbirlik_pers_5
            )
        )
    )


    val saykes8 = listOf(
        listOf(
            saykesModel(R.drawable.arzu_armanina_jetken_jigit_pers4, R.drawable.arzu_armanina_jetken_jigit_pers1),
            saykesModel(R.drawable.aqilli_serke_ham_aqmaq_arislan_pers_1, R.drawable.aqilli_serke_ham_aqmaq_arislan_pers_2),
            saykesModel(R.drawable.dunyanin_zori_pers_4, R.drawable.dunyanin_zori_pers_6),
            saykesModel(R.drawable.qoraz_menen_tulki_pers1,R.drawable.qoraz_menen_tulki_qoraz
            )
        )
    )


    val saykes9 = listOf(
        listOf(
            saykesModel(R.drawable.dunyanin_zori_pers_1, R.drawable.dunyanin_zori_pers_2),
            saykesModel(R.drawable.awizbirlik_pers_1, R.drawable.awizbirlik_pers_3),
            saykesModel(R.drawable.dau_menen_bala_pers1, R.drawable.dau_menen_bala_pers2),
            saykesModel(R.drawable.iyttin_nesiybesi_pers1,R.drawable.iyttin_nesiybesi_pers2
            )
        )
    )


    val saykes10 = listOf(
        listOf(
            saykesModel(R.drawable.maqtanshaq_tishqan_pers_1, R.drawable.maqtanshaq_tishqan_pers_3),
            saykesModel(R.drawable.aqilli_serke_ham_aqmaq_arislan_pers_3, R.drawable.aqilli_serke_ham_aqmaq_arislan_pers_1),
            saykesModel(R.drawable.jetim_bala_pers_2, R.drawable.jetim_bala_pers_4),
            saykesModel(R.drawable.qis_penen_jaz_qalay_janjellesti_pers_3,R.drawable.qis_penen_jaz_qalay_janjellesti_pers_1
            )
        )
    )


}

class saykesModel(
    @DrawableRes val variant1: Int,
    @DrawableRes val variant2: Int
)

