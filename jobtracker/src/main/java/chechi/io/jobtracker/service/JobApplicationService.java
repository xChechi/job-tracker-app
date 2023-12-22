package chechi.io.jobtracker.service;

import chechi.io.jobtracker.dto.application.JobApplicationRequest;
import chechi.io.jobtracker.dto.application.JobApplicationResponse;

import java.util.List;

public interface JobApplicationService {

    List<JobApplicationResponse> findAll ();

    JobApplicationResponse findById (Integer jobId);

    JobApplicationResponse createJobApplication (JobApplicationRequest request);

    JobApplicationResponse updateJobApplication (Integer id, JobApplicationRequest request);

    void deleteJobApplication (Integer id);
}
