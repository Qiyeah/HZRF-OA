package com.oa.controller.kit;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * 验证签名是否合法
 */
public class SignatureCheckKit {
	
	public static final SignatureCheckKit me = new SignatureCheckKit();
	
	public boolean checkSignature(String accessToken,String nonce, String timestamp, String signature,String key,String url) {
        String tempStr = null;
        tempStr = sign(accessToken, nonce,timestamp, key, url);

        return tempStr.equalsIgnoreCase(signature);
	}

    public static String sign(String accessToken,String nonceStr,String timestamp,String key,String url) {
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "accesstoken=" + accessToken +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&key=" + key +
                "&url=" + url;

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return signature;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}



