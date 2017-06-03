package forms;

import play.data.validation.Constraints;

public class SearchResultForm {
    @Constraints.Required
    public Integer selectedRoom;
}
