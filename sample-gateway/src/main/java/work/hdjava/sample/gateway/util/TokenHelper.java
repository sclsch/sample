package work.hdjava.sample.gateway.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

/**
 * All rights Reserved, Designed By hdjava.work.
 *
 * @author: suncl
 * @date: 2023/7/30 22:26
 * @Copyright ?2022 hdjava.work. All rights reserved.
 * 注意：本内容仅限于hdjava.work内部传阅，禁止外泄以及用于其他的商业用途。
 */
@Slf4j
public class TokenHelper {
    /**
     * 生成公钥
     * @param modulus
     * @param exponent
     * @return
     */
    public static PublicKey getPublicKey(String modulus, String exponent)  {
        try {
            byte exponentB[] = Base64.getUrlDecoder().decode(exponent);
            byte modulusB[] = Base64.getUrlDecoder().decode(modulus);
            BigInteger bigExponent = new BigInteger(1,exponentB);
            BigInteger bigModulus = new BigInteger(1,modulusB);
            PublicKey publicKey;
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(bigModulus, bigExponent));
            return publicKey;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            log.error("验证token失败",e);
        }
        return null;
    }

    /**
     * 验证token
     * @param publicKey 公钥
     * @param token token
     * @return
     */
    public static boolean verify(PublicKey publicKey,String token){
        String jwt = token;
        String signedData = jwt.substring(0, jwt.lastIndexOf("."));
        String signatureB64u = jwt.substring(jwt.lastIndexOf(".")+1,jwt.length());
        byte signature[] = Base64.getUrlDecoder().decode(signatureB64u);

        Signature sig = null;
        try {
            sig = Signature.getInstance("SHA256withRSA");
        } catch (NoSuchAlgorithmException e) {
            log.error("无此算法",e);
        }
        try {
            sig.initVerify(publicKey);
            sig.update(signedData.getBytes());
            boolean isVerify = sig.verify(signature);
            return isVerify;
        } catch (InvalidKeyException e) {
            log.error("密钥异常",e);
        } catch (SignatureException e) {
            log.error("签名异常",e);
        }
        return false;
    }


}


