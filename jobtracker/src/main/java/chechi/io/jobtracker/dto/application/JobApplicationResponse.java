package chechi.io.jobtracker.dto.application;

import chechi.io.jobtracker.entity.StatusType;
import chechi.io.jobtracker.entity.Interview;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JobApplicationResponse {

    private Integer id;

    private String companyName;

    private String email;

    private String phoneNumber;

    private String jobDescription;

    private String positionApplied;

    private LocalDate appliedAt;

    private String notes;



    private StatusType status;

    //private Interview currentInterview;
}
