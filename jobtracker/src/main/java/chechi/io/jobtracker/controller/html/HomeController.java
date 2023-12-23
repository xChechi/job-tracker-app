package chechi.io.jobtracker.controller.html;

import chechi.io.jobtracker.dto.application.JobApplicationRequest;
import chechi.io.jobtracker.dto.application.JobApplicationResponse;
import chechi.io.jobtracker.entity.JobApplication;
import chechi.io.jobtracker.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

    private final JobApplicationService jobApplicationService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("jobApplication", new JobApplication());
        return "home";
    }

    @PostMapping
    public String createJobApplication(JobApplicationRequest request, Model model) { //@ModelAttribute @Valid
        JobApplicationResponse createdApplication = jobApplicationService.createJobApplication(request);

        model.addAttribute("showSuccess", true);
        model.addAttribute("jobApplication", new JobApplication());

        return "home";
    }


}
