package org.auth.totp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TOtpBarcodeGeneratorTest {

    @Test
    fun `바코드 생성`() {
        val barcodeGenerator = TOtpBarcodeGenerator(createGoogleOtp())

        val bitMatrix = barcodeGenerator.generate()

        assertThat(bitMatrix.width).isEqualTo(300)
        assertThat(bitMatrix.height).isEqualTo(300)
    }
}
