package chechi.io.jobtracker.util;

import org.springframework.stereotype.Component;

@Component
public class HtmlFragmentGenerator {

    public String generateFragment() {
        // Generate your HTML fragment here dynamically
        StringBuilder fragment = new StringBuilder();
        // Append your HTML code here using StringBuilder or any other approach

        fragment.append("<input type=\"text\" class=\"form-control\" id=\"companyName\" th:value=\"${jobDetails.companyName}\" required>");
        fragment.append("<input type=\"email\" class=\"form-control\" id=\"email\" th:value=\"${jobDetails.email}\">");
        // Add other fields similarly

        return fragment.toString();
    }
}
