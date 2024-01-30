package com.example.eldarwallet.core.helpers

import java.security.Key
import java.security.spec.KeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import kotlin.text.Charsets.UTF_8

class EncryptionHelper {

    companion object {
        //todo CONTRASEÑA DE PRUEBA
        private val password =
            "Vr{=Dy?Q47fK+BL:2tk5v;L7D>Hj4c^ZKfbp%RxG#S8UX9`G%y!hD_{v*NrA>uJaDqyfE.2&B%R3?hG@TtJ}px8#_WkK;7j.-N>2eaGS`B"
        private val salt = byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8)

        private val keySpec: KeySpec = PBEKeySpec(password.toCharArray(), salt, 65536, 256)
        private val secretKeyFactory: SecretKeyFactory =
            SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        private val keyBytes: ByteArray = secretKeyFactory.generateSecret(keySpec).encoded
        private val key: Key = SecretKeySpec(keyBytes, "AES")

        private val cipher: Cipher = Cipher.getInstance("AES")

        fun encrypt(data: String): String {
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val encryptedBytes = cipher.doFinal(data.toByteArray(UTF_8))
            return android.util.Base64.encodeToString(encryptedBytes, android.util.Base64.DEFAULT)
        }

        fun decrypt(encryptedData: String): String {
            cipher.init(Cipher.DECRYPT_MODE, key)
            val encryptedBytes =
                android.util.Base64.decode(encryptedData, android.util.Base64.DEFAULT)
            val decryptedBytes = cipher.doFinal(encryptedBytes)
            return String(decryptedBytes, UTF_8)
        }
    }
}


