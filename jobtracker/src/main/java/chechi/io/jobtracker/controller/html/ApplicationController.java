package chechi.io.jobtracker.controller.html;

import chechi.io.jobtracker.dto.application.JobApplicationRequest;
import chechi.io.jobtracker.dto.application.JobApplicationResponse;
import chechi.io.jobtracker.entity.JobApplication;
import chechi.io.jobtracker.entity.StatusType;
import chechi.io.jobtracker.service.JobApplicationService;
import chechi.io.jobtracker.util.HtmlFragmentGenerator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/job-applications")
@AllArgsConstructor
public class ApplicationController {

    private final JobApplicationService jobApplicationService;
    private final HtmlFragmentGenerator fragmentGenerator;

    @GetMapping
    public String applications(Model model) {
        List<JobApplicationResponse> allJobs = jobApplicationService.findAll();

        // Filter jobs by status
        List<JobApplicationResponse> pendingJobs = allJobs.stream()
                .filter(job -> job.getStatus() == StatusType.PENDING)
                .collect(Collectors.toList());

        // Repeat the filtering process for other statuses

        model.addAttribute("pendingJobs", pendingJobs);

        model.addAttribute("jobApplication", new JobApplication());

        return "applications";
    }

    @GetMapping("/job-details/{id}")
    @ResponseBody
    public JobApplicationResponse jobDetailsModal(@PathVariable Integer id) {
        JobApplicationResponse jobDetails = jobApplicationService.findById(id);

        return jobApplicationService.findById(id);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public String updateJobApplication(@PathVariable Integer id, @ModelAttribute @Valid JobApplicationRequest request, Model model) {

        JobApplicationResponse updatedJob = jobApplicationService.updateJobApplication(id, request);

        model.addAttribute("updatedJob", updatedJob);
        model.addAttribute("message", "Job updated successfully");

        // Redirect back to the page or return a response as needed
        // You might redirect to the applications page or the homepage
        return "redirect:/job-applications";
    }

    @ModelAttribute("jobApplication")
    public JobApplication getJobApplication() {
        return new JobApplication(); // Initialize an empty job application object
    }
}
