import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Vector;

public class VehicleSerializer implements JsonSerializer<Vehicle>, JsonDeserializer<Vehicle> {

    @Override
    public JsonElement serialize(Vehicle vehicle, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(vehicle.getClass().getSimpleName()));
        result.add("properties", jsonSerializationContext.serialize(vehicle, vehicle.getClass()));
        return result;
    }

    @Override
    public Vehicle deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String vehicleType = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");
        try {
            return jsonDeserializationContext.deserialize(element, Class.forName(vehicleType));
        } catch (ClassNotFoundException e) {
            throw new JsonParseException ("Unknown class type: " + vehicleType, e);
        }

    }


}
