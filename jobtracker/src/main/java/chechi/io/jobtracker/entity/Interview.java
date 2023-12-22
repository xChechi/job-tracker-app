package chechi.io.jobtracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private Integer id;

    @Future
    private LocalDateTime interviewDate;

    private String location;

    private String details;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private JobApplication jobApplication;
}
