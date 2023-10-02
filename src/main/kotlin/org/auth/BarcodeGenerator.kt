package org.auth

import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix

interface BarcodeGenerator {

    fun generate(
        format: BarcodeFormat = BarcodeFormat.QR_CODE,
        width: Int = 300,
        height: Int = 300
    ): BitMatrix
}
