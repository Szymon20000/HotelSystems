package helpers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import play.libs.Json;

import static play.mvc.Controller.flash;

public class SessionMessages {
    static public void addError(String m) {
        String messages = flash().get("_messages_error");
        ArrayNode json = messages == null ? JsonNodeFactory.instance.arrayNode() : (ArrayNode) Json.parse(messages);
        json.add(m);
        flash("_messages_error", json.toString());
    }

    static public void addWarning(String m) {
        String messages = flash().get("_messages_warning");
        ArrayNode json = messages == null ? JsonNodeFactory.instance.arrayNode() : (ArrayNode) Json.parse(messages);
        json.add(m);
        flash("_messages_warning", json.toString());
    }

    static public void addSuccess(String m) {
        String messages = flash().get("_messages_success");
        ArrayNode json = messages == null ? JsonNodeFactory.instance.arrayNode() : (ArrayNode) Json.parse(messages);
        json.add(m);
        flash("_messages_success", json.toString());
    }

}
