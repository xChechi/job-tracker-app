package chechi.io.jobtracker.dto.application;

import chechi.io.jobtracker.entity.Interview;
import chechi.io.jobtracker.entity.StatusType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JobApplicationRequest {

    @NotBlank
    private String companyName;

    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    private String jobDescription;

    @NotBlank
    private String positionApplied;

    private LocalDate appliedAt;

    private String notes;

    private StatusType status;



    //private Interview lastInterview;
}
