package chechi.io.jobtracker.dto.interview;

import chechi.io.jobtracker.dto.application.JobApplicationResponse;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InterviewResponse {

    private Integer id;

    private LocalDateTime interviewDate;

    private String location;

    private String details;

    private String notes;

    private JobApplicationResponse app;
}
