package chechi.io.jobtracker.service.impl;

import chechi.io.jobtracker.converter.InterviewConverter;
import chechi.io.jobtracker.dto.interview.InterviewRequest;
import chechi.io.jobtracker.dto.interview.InterviewResponse;
import chechi.io.jobtracker.entity.Interview;
import chechi.io.jobtracker.entity.JobApplication;
import chechi.io.jobtracker.exception.InterviewNotFoundException;
import chechi.io.jobtracker.exception.JobApplicationNotFound;
import chechi.io.jobtracker.repository.InterviewRepository;
import chechi.io.jobtracker.repository.JobApplicationRepository;
import chechi.io.jobtracker.service.InterviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewConverter interviewConverter;
    private final JobApplicationRepository jobApplicationRepository;

    @Override
    public List<InterviewResponse> findAll() {
        List<Interview> interviewList = interviewRepository.findAll();
        return interviewList.stream()
                .map(interviewConverter::toResponse)
                .toList();
    }

    @Override
    public InterviewResponse findById(Integer id) {
        Interview interview = interviewRepository.findById(id).orElseThrow(() -> new InterviewNotFoundException("Interview not found"));
        return interviewConverter.toResponse(interview);
    }

    @Override
    public InterviewResponse createInterview(Integer jobId, InterviewRequest request) {
        Interview interview = interviewConverter.createInterview(jobId, request);
        Interview savedInterview = interviewRepository.save(interview);

        JobApplication jobApplication = jobApplicationRepository.findById(jobId).orElseThrow(() -> new JobApplicationNotFound("Job Application Not Found"));

        jobApplication.getInterviews().add(savedInterview);
        jobApplication.setLastInterview(savedInterview);

        jobApplicationRepository.save(jobApplication);

        return interviewConverter.toResponse(savedInterview);
    }

    @Override
    public InterviewResponse updateInterview(Integer id, InterviewRequest request) {
        Interview interview = interviewRepository.findById(id).orElseThrow(() -> new InterviewNotFoundException("Interview not found"));

        interview.setInterviewDate(request.getInterviewDate());
        interview.setLocation(request.getLocation());
        interview.setDetails(request.getDetails());
        interview.setNotes(request.getNotes());

        Interview savedInterview = interviewRepository.save(interview);
        return interviewConverter.toResponse(interview);
    }

    @Override
    public void deleteInterview(Integer jobId, Integer id) {
        JobApplication jobApplication = jobApplicationRepository.findById(jobId).orElseThrow(() -> new JobApplicationNotFound("Job Application Not Found"));
        jobApplication.setLastInterview(null);
        jobApplicationRepository.save(jobApplication);
        interviewRepository.deleteById(id);
    }
}
