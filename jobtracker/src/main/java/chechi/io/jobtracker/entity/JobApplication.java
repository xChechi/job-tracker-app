package chechi.io.jobtracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_applications")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Integer id;

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

    @Enumerated(EnumType.STRING)
    private StatusType status;

    @OneToMany(mappedBy = "jobApplication", cascade = CascadeType.ALL)
    private List<Interview> interviews = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "last_interview_id")
    private Interview lastInterview;

}
