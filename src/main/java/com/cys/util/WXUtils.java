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
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
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
        String encryptedData ="p5AmetFW1CrqAw1H7T5UP0TS+UGTG5tmPe+O1MRdGn16MzLNauTIbHzvDv3BfkXZB82/FwEh3Er5mXZZftgs6PfMdArMJ8TejpD+2TcN1f7TJlI9GaeAQXayGSspGFpel2jVGYeU2h/9rG94LDaqLJZCpccP36xQ3Y2jUMPsFcbvQT3SLMGJdQSjoGzrDqd5yozxGSJbU9Ek/5jZdT8poLqKER6y686IMoRPm7CphYxbA/PVkWMdmNxFsLn7Sxi59COtma3zxBMttMFHgXkqhevZerExIp10zwLx1cSQVHY95onb+3Hd3Ta8hFSOA6kRo1YaI3/0dFGP7apS+WyFfCkyIbR0NDaAgd6R+2INYK9Zf8d7heNr085nUXbvcwtSELm5+doaGEqwO+7vxpK4dsrQ+mR4YP4GKvI7tDZC2lTDxk/nnzEs3avIWKpsBZ1pC2a8xYir6Wa7jCIsaJQlDqQha7/NrSTWVYiDPMme8X4=";
        String iv = "XumygPZts04/JHAVSwo9jg==";
        String sessionKey ="45dc8700919d14c16bd5bb6764213b0f5d7176c0";
        JSONObject result =  WXUtils.getSessionKeyOropenid("003zzK8K0y88I52kXJbK0GZM8K0zzK8U");
        String openid =(String) result.get("openid");
        String session_key=(String) result.get("session_key");

        JSONObject jsonObject = WXUtils.getUserInfo(encryptedData,sessionKey,iv);
        System.out.println(jsonObject.toJSONString());
    }


}
