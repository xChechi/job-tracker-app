package chechi.io.jobtracker.controller;

import chechi.io.jobtracker.dto.application.JobApplicationRequest;
import chechi.io.jobtracker.dto.application.JobApplicationResponse;
import chechi.io.jobtracker.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping
    public ResponseEntity<List<JobApplicationResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.findAll());
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobApplicationResponse> findById (@PathVariable Integer jobId) {
        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.findById(jobId));
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponse> createJobApplication (@RequestBody @Valid JobApplicationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobApplicationService.createJobApplication(request));
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<JobApplicationResponse> updateJobApplication (@PathVariable Integer jobId, @RequestBody @Valid JobApplicationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.updateJobApplication(jobId, request));
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJobApplication (@PathVariable Integer jobId) {
        jobApplicationService.deleteJobApplication(jobId);
        return ResponseEntity.noContent().build();
    }
}
