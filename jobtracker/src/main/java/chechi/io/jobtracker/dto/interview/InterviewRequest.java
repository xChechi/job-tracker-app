package chechi.io.jobtracker.dto.interview;

import jakarta.validation.constraints.Future;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InterviewRequest {

    @Future
    private LocalDateTime interviewDate;

    private String location;

    private String details;

    private String notes;
}
