package org.auth.google

import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.client.j2se.MatrixToImageWriter
import de.taimos.totp.TOTP
import org.apache.commons.codec.binary.Base32
import org.apache.commons.codec.binary.Hex
import org.auth.extension.encode
import java.awt.image.BufferedImage

class GoogleOtp(
    val issuer: String,
    val account: String,
    secretKeyGenerator: () -> String
) {

    val secretKey = secretKeyGenerator()

    fun otpUrl(): String {
        return "otpauth://totp/${"$issuer:$account".encode()}?secret=${secretKey.encode()}&issuer=${issuer.encode()}"
    }

    fun qrCodeImage(width: Int = 300, height: Int = 300): BufferedImage {
        val matrix = MultiFormatWriter().encode(this.otpUrl(), BarcodeFormat.QR_CODE, width, height)

        return MatrixToImageWriter.toBufferedImage(matrix)
    }

    fun otpNumber(): String {
        val bytes = Base32().decode(this.secretKey)
        val hexKey = Hex.encodeHexString(bytes)

        return TOTP.getOTP(hexKey)
    }
}
