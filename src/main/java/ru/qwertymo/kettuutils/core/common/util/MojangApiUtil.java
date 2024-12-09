package ru.qwertymo.kettuutils.core.common.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MojangApiUtil {
    private static final String UUID_URL = "https://api.mojang.com/users/profiles/minecraft/";
    private static final String PROFILE_URL = "https://sessionserver.mojang.com/session/minecraft/profile/";

    public static String getPlayerUUID(String name){
        try{
            String uuidUrl = UUID_URL + name;
            HttpURLConnection connection = (HttpURLConnection) new URL(uuidUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == 200) {
                String jsonResponse = IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
                JsonObject jsonObject = new JsonParser().parse(jsonResponse).getAsJsonObject();
                return jsonObject.get("id").getAsString();
            }
        } catch (Exception ignored) {}
        return "";
    }

    public static String getPlayerSkinURL(String uuid){
        try {
            String uuidUrl = PROFILE_URL + uuid;
            HttpURLConnection connection = (HttpURLConnection) new URL(uuidUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            String textureUrl = PROFILE_URL + uuid;
            connection = (HttpURLConnection) new URL(textureUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == 200) {
                String jsonResponse = IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
                JsonObject jsonObject = new JsonParser().parse(jsonResponse).getAsJsonObject();

                String textureValue = jsonObject.getAsJsonArray("properties").get(0).getAsJsonObject().get("value").getAsString();
                byte[] decoded = Base64.getDecoder().decode(textureValue);
                String decodedStr = new String(decoded, StandardCharsets.UTF_8);
                jsonObject = new JsonParser().parse(decodedStr).getAsJsonObject();
                return jsonObject.getAsJsonObject("textures").getAsJsonObject("SKIN").get("url").getAsString();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "";
    }

}
