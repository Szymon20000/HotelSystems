package helpers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import play.libs.Json;

import static play.mvc.Controller.flash;

public class SessionMessages {
    static public void addMessage(String m) {
        String messages = flash().get("_messages");
        ArrayNode json = messages == null ? JsonNodeFactory.instance.arrayNode() : (ArrayNode) Json.parse(messages);
        json.add(m);
        flash("_messages", json.toString());
    }
}
