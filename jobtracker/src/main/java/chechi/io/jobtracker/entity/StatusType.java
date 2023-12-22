package chechi.io.jobtracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusType {

    PENDING("Pending"),
    IN_REVIEW("In review"),
    REJECTED("Rejected"),
    ACCEPTED("Accepted");

    private final String displayName;
}
