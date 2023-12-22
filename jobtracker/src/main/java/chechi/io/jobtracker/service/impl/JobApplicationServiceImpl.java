package chechi.io.jobtracker.service.impl;

import chechi.io.jobtracker.converter.JobApplicationConverter;
import chechi.io.jobtracker.dto.application.JobApplicationRequest;
import chechi.io.jobtracker.dto.application.JobApplicationResponse;
import chechi.io.jobtracker.entity.JobApplication;
import chechi.io.jobtracker.exception.JobApplicationNotFound;
import chechi.io.jobtracker.repository.JobApplicationRepository;
import chechi.io.jobtracker.service.JobApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationConverter jobApplicationConverter;
    private final JobApplicationRepository jobApplicationRepository;

    @Override
    public List<JobApplicationResponse> findAll() {
        List<JobApplication> applicationList = jobApplicationRepository.findAll();
        return applicationList.stream()
                .map(jobApplicationConverter::toResponse)
                .toList();
    }

    @Override
    public JobApplicationResponse findById(Integer jobId) {
        JobApplication jobApplication = jobApplicationRepository.findById(jobId).orElseThrow(() -> new JobApplicationNotFound("Job application not found"));
        return jobApplicationConverter.toResponse(jobApplication);
    }

    @Override
    public JobApplicationResponse createJobApplication(JobApplicationRequest request) {
        JobApplication jobApplication = jobApplicationConverter.createJobApplication(request);
        JobApplication savedApp = jobApplicationRepository.save(jobApplication);

        return jobApplicationConverter.toResponse(savedApp);
    }

    @Override
    public JobApplicationResponse updateJobApplication(Integer id, JobApplicationRequest request) {
        JobApplication app = jobApplicationRepository.findById(id).orElseThrow(() -> new JobApplicationNotFound("Job application not found"));



        app.setCompanyName(request.getCompanyName());
        app.setEmail(request.getEmail());
        app.setPhoneNumber(request.getPhoneNumber());
        app.setJobDescription(request.getJobDescription());
        app.setPositionApplied(request.getPositionApplied());
        app.setAppliedAt(request.getAppliedAt());
        app.setNotes(request.getNotes());
        app.setStatus(request.getStatus());


/*
        if (app.isInterview()) {
            List<Interview> interviews = app.getInterviews();
            if (!interviews.isEmpty()) {
                interviews.sort(Comparator.comparing(Interview::getInterviewDate).reversed());
                Interview lastInterview = interviews.get(0);
                app.setLastInterview(lastInterview);
            }
        } else {
            app.setLastInterview(null);
        }
*/
        JobApplication savedApp = jobApplicationRepository.save(app);
        return jobApplicationConverter.toResponse(savedApp);
    }

    @Override
    public void deleteJobApplication(Integer id) {
        jobApplicationRepository.deleteById(id);
    }
}
