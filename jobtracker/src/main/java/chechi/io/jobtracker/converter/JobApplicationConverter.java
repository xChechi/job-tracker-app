package chechi.io.jobtracker.converter;

import chechi.io.jobtracker.dto.application.JobApplicationRequest;
import chechi.io.jobtracker.dto.application.JobApplicationResponse;
import chechi.io.jobtracker.dto.interview.InterviewResponse;
import chechi.io.jobtracker.entity.Interview;
import chechi.io.jobtracker.entity.JobApplication;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JobApplicationConverter {

    public JobApplication createJobApplication (JobApplicationRequest request) {

        return JobApplication.builder()
                .companyName(request.getCompanyName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .jobDescription(request.getJobDescription())
                .positionApplied(request.getPositionApplied())
                .appliedAt(request.getAppliedAt())
                .notes(request.getNotes())
                .status(request.getStatus())

                .build();
    }

    public JobApplicationResponse toResponse (JobApplication jobApplication) {

        Interview lastInterview = jobApplication.getLastInterview();
        InterviewResponse interviewResponse = null;

        if (lastInterview != null) {
            interviewResponse = InterviewResponse.builder()
                    .id(lastInterview.getId())
                    .interviewDate(lastInterview.getInterviewDate())
                    .location(lastInterview.getLocation())
                    .details(lastInterview.getDetails())
                    .notes(lastInterview.getNotes())
                    .build();
        }

        return JobApplicationResponse.builder()
                .id(jobApplication.getId())
                .companyName(jobApplication.getCompanyName())
                .email(jobApplication.getEmail())
                .phoneNumber(jobApplication.getPhoneNumber())
                .jobDescription(jobApplication.getJobDescription())
                .positionApplied(jobApplication.getPositionApplied())
                .appliedAt(jobApplication.getAppliedAt())
                .notes(jobApplication.getNotes())
                .status(jobApplication.getStatus())
                .lastInterview(interviewResponse)
                .build();
    }
}
