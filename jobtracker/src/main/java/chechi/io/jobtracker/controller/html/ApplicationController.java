package chechi.io.jobtracker.controller.html;

import chechi.io.jobtracker.dto.application.JobApplicationRequest;
import chechi.io.jobtracker.dto.application.JobApplicationResponse;
import chechi.io.jobtracker.dto.interview.InterviewResponse;
import chechi.io.jobtracker.entity.JobApplication;
import chechi.io.jobtracker.entity.StatusType;
import chechi.io.jobtracker.service.InterviewService;
import chechi.io.jobtracker.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/job-applications")
@AllArgsConstructor
public class ApplicationController {

    private final JobApplicationService jobApplicationService;
    private final InterviewService interviewService;

    @GetMapping
    public String applications(Model model) {
        List<JobApplicationResponse> allJobs = jobApplicationService.findAll();

        // Filter jobs by status
        List<JobApplicationResponse> pendingJobs = allJobs.stream()
                .filter(job -> job.getStatus() == StatusType.PENDING)
                .sorted(Comparator.comparing(JobApplicationResponse::getAppliedAt).reversed())
                .collect(Collectors.toList());

        List<JobApplicationResponse> inReviewJobs = allJobs.stream()
                .filter(job -> job.getStatus() == StatusType.IN_REVIEW)
                .sorted(Comparator.comparing(JobApplicationResponse::getAppliedAt).reversed())
                .collect(Collectors.toList());

        List<JobApplicationResponse> rejectedJobs = allJobs.stream()
                .filter(job -> job.getStatus() == StatusType.REJECTED)
                .sorted(Comparator.comparing(JobApplicationResponse::getAppliedAt).reversed())
                .collect(Collectors.toList());

        List<JobApplicationResponse> acceptedJobs = allJobs.stream()
                .filter(job -> job.getStatus() == StatusType.ACCEPTED)
                .sorted(Comparator.comparing(JobApplicationResponse::getAppliedAt).reversed())
                .collect(Collectors.toList());

        model.addAttribute("pendingJobs", pendingJobs);
        model.addAttribute("inReviewJobs", inReviewJobs);
        model.addAttribute("rejectedJobs", rejectedJobs);
        model.addAttribute("acceptedJobs", acceptedJobs);

        model.addAttribute("jobApplication", new JobApplication());

        return "applications";
    }

    @GetMapping("/job-details/{id}")
    @ResponseBody
    public JobApplicationResponse jobDetailsModal(@PathVariable Integer id) {
        JobApplicationResponse jobDetails = jobApplicationService.findById(id);

        return jobApplicationService.findById(id);
    }

    @GetMapping("/interview-details/{id}")
    public InterviewResponse interviewDetailsModal (@PathVariable Integer id) {
        return interviewService.findById(id);
    }


    @PutMapping("/update/{id}")
    @ResponseBody
    public JobApplicationResponse updateJobApplication(@PathVariable Integer id, @ModelAttribute @Valid JobApplicationRequest request, Model model) {

        JobApplicationResponse updatedJob = jobApplicationService.updateJobApplication(id, request);

        model.addAttribute("updatedJob", updatedJob);
        model.addAttribute("showSuccess", true);

        // Redirect back to the page or return a response as needed
        // You might redirect to the applications page or the homepage
        return updatedJob;
    }

    @ModelAttribute("jobApplication")
    public JobApplication getJobApplication() {
        return new JobApplication(); // Initialize an empty job application object
    }

    @DeleteMapping("/delete-entry/{jobId}") // Adjust the method type (POST/DELETE) and URL path
    @ResponseBody
    public String deleteEntry(@PathVariable Integer jobId) {
        // Logic to delete an entry from the application based on ID
        jobApplicationService.deleteJobApplication(jobId);
        // Return appropriate response or data if needed
        return "redirect:/job-applications";
    }


}
