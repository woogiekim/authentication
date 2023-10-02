package org.auth.totp

import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import org.auth.BarcodeGenerator

class TOtpBarcodeGenerator(
    private val totp: TOtp
) : BarcodeGenerator {

    override fun generate(format: BarcodeFormat, width: Int, height: Int): BitMatrix {
        return MultiFormatWriter().encode(totp.generateUrl(), format, width, height)
    }
}
