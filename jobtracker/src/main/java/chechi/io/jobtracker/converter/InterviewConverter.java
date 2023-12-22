package chechi.io.jobtracker.converter;

import chechi.io.jobtracker.dto.interview.InterviewRequest;
import chechi.io.jobtracker.dto.interview.InterviewResponse;
import chechi.io.jobtracker.entity.Interview;
import chechi.io.jobtracker.entity.JobApplication;
import chechi.io.jobtracker.exception.JobApplicationNotFound;
import chechi.io.jobtracker.repository.JobApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InterviewConverter {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobApplicationConverter jobApplicationConverter;

    public Interview createInterview (Integer jobId, InterviewRequest request) {
        JobApplication app = jobApplicationRepository.findById(jobId).orElseThrow(() -> new JobApplicationNotFound("Job Application Not Found"));

        return Interview.builder()
                .jobApplication(app)
                .interviewDate(request.getInterviewDate())
                .location(request.getLocation())
                .details(request.getDetails())
                .notes(request.getNotes())
                .build();
    }

    public InterviewResponse toResponse (Interview interview) {
        return InterviewResponse.builder()
                .id(interview.getId())
                .interviewDate(interview.getInterviewDate())
                .location(interview.getLocation())
                .details(interview.getDetails())
                .notes(interview.getNotes())
                .app(jobApplicationConverter.toResponse(interview.getJobApplication()))
                .build();
    }
}
