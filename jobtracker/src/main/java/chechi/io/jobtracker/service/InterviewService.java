package chechi.io.jobtracker.service;

import chechi.io.jobtracker.dto.interview.InterviewRequest;
import chechi.io.jobtracker.dto.interview.InterviewResponse;

import java.util.List;

public interface InterviewService {

    List<InterviewResponse> findAll ();

    InterviewResponse findById (Integer id);

    InterviewResponse createInterview (Integer jobId, InterviewRequest request);

    InterviewResponse updateInterview (Integer id, InterviewRequest request);

    void deleteInterview (Integer id);

}
