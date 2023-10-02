package org.auth.google

import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.client.j2se.MatrixToImageWriter
import de.taimos.totp.TOTP
import org.apache.commons.codec.binary.Base32
import org.apache.commons.codec.binary.BaseNCodec
import org.apache.commons.codec.binary.Hex
import org.auth.DefaultSecretKeyGenerator
import org.auth.SecretKeyGenerator
import org.auth.extension.encode
import java.awt.image.BufferedImage

class GoogleOtp(
    val issuer: String,
    val account: String,
    private val baseNCodec: BaseNCodec = Base32(),
    secretKeyGenerator: SecretKeyGenerator = DefaultSecretKeyGenerator()
) {

    val secretKey: String = secretKeyGenerator.generate().run {
        baseNCodec.encodeToString(this)
    }

    val otpUrl: String =
        "otpauth://totp/${"$issuer:$account".encode()}?secret=${secretKey.encode()}&issuer=${issuer.encode()}"

    fun generateOtpNumber(): String {
        val bytes = this.baseNCodec.decode(this.secretKey)
        val hexKey = Hex.encodeHexString(bytes)

        return TOTP.getOTP(hexKey)
    }

    fun generateQrCodeImage(width: Int = 300, height: Int = 300): BufferedImage {
        val matrix = MultiFormatWriter().encode(this.otpUrl, BarcodeFormat.QR_CODE, width, height)

        return MatrixToImageWriter.toBufferedImage(matrix)
    }
}
