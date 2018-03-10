package com.cys.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by liyuan on 2018/3/5.
 */
public class WXUtils {
    private static Logger log = Logger.getLogger(WXUtils.class);


    static {
        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }
    /**
     * 获取微信小程序 session_key 和 openid
     *
     * @author ly
     * @param code 调用微信登陆返回的Code
     * @return
     */
    public static JSONObject getSessionKeyOropenid(String code){
        //微信端登录code值
        String wxCode = code;
        ResourceBundle resource = ResourceBundle.getBundle("properties.weixin");   //读取属性文件
        String requestUrl = resource.getString("url");  //请求地址 https://api.weixin.qq.com/sns/jscode2session
        Map<String,String> requestUrlParam = new HashMap<String,String>();
        requestUrlParam.put("appid", resource.getString("appId"));  //开发者设置中的appId
        requestUrlParam.put("secret", resource.getString("appSecret")); //开发者设置中的appSecret
        requestUrlParam.put("js_code", wxCode); //小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", "authorization_code");    //默认参数

        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(UrlUtils.sendPost(requestUrl, requestUrlParam));
        return jsonObject;
    }

    /**
     * 解密用户敏感数据获取用户信息
     *
     * @author ly
     * @param sessionKey 数据进行加密签名的密钥
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv 加密算法的初始向量
     * @return
     */
    public static JSONObject getUserInfo(String encryptedData,String sessionKey,String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            log.error(e.getMessage(), e);
        } catch (InvalidParameterSpecException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            log.error(e.getMessage(), e);
        } catch (BadPaddingException e) {
            log.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            log.error(e.getMessage(), e);
        } catch (InvalidAlgorithmParameterException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchProviderException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[]   args){
        String encryptedData ="4n4WucjU7QRyzz8ZG1l/w84lnr9Ghjd6M/U3MZ2Nq/GBtM2xQWPWIQadXUxkcMoBHCsQ8SQADftafNYRr+Dv5sra/udwL7n4kyhRswIuk7qicFHery0uybZ5Av56H31midtV9blaJ+F1Xb6jv9KL5hjRe/qSI/Lure/QbS0AW4oWpvpZN3c0l+w+zBxudtyWsgZIfikAPL2bA2VdR8ewjjr8W7DNTrzDuzjhwXys9GHZLMIKMJAKcb9CGXJnrLlMs2r4kEPMv+tCdlE4hA4ocaOf/lJq8e2f2/Jvlr4BAqJj4raCksPTGmHbpjaz6g6Bi6b7TM6XGEtYlC6FlV/xT7vNPL8ZkN78ZLFAiUHppaT8hEBxcik9ST+dBicQXtYNTlfBC/qriq9P0BDpymr7ds9vLjp5bGsaU5Wu63vCqgVnwvsMs/Vb1aFbqHXaVTlh6q507V0R6mXzgrkqRQia48cfEDmrOYksHB1RxKGsxBU=";
        String iv = "Aar00v6VAnNabVF06Nj5ow==";
        String sessionKey ="Ye9h6hzvraJOXZrQIpi5pg==";
        /*JSONObject result =  WXUtils.getSessionKeyOropenid("003E73dz0V1B3h18wDcz0klPcz0E73dN");
        String openid =(String) result.get("openid");
        String session_key=(String) result.get("session_key");*/

        JSONObject jsonObject = WXUtils.getUserInfo(encryptedData,sessionKey,iv);
        System.out.println(jsonObject.toJSONString());
    }


}
