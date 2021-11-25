package container;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/***
 * Utility class in charge of serializing and deserializing objects,
 * this class uses a final instance of Gson
 */
public abstract class Serializer {
    private Serializer() {
    }

    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime()).serializeNulls().create();

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object object) {
        if (object == null)
            return "";
        return gson.toJson(object).replace("null", "{}");
    }
}
