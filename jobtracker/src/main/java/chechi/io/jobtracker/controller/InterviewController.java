package chechi.io.jobtracker.controller;

import chechi.io.jobtracker.dto.interview.InterviewRequest;
import chechi.io.jobtracker.dto.interview.InterviewResponse;
import chechi.io.jobtracker.service.InterviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviews")
@AllArgsConstructor
public class InterviewController {

    private final InterviewService interviewService;

    @GetMapping
    public ResponseEntity<List<InterviewResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(interviewService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewResponse> findById (@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(interviewService.findById(id));
    }

    @PostMapping("/{jobId}")
    public ResponseEntity<InterviewResponse> createInterview (@PathVariable Integer jobId, @RequestBody @Valid InterviewRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(interviewService.createInterview(jobId, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterviewResponse> updateInterview (@PathVariable Integer id, @RequestBody @Valid InterviewRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(interviewService.updateInterview(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterview (@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

}
