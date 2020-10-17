package ro.sda.spring.boot.mvcController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.sda.spring.boot.entity.Doctor;
import ro.sda.spring.boot.service.DoctorService;

import javax.validation.Valid;

@Controller
public class DoctorMVCController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorMVCController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public String showDoctors(Model model) {
        model.addAttribute("doctors", this.doctorService.findAllDoctors());
        return "index";
    }

    @GetMapping(path = "/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "add-doctor";
    }

    @GetMapping(path = "/doctor/edit/{id}")
    public String showEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("doctor", this.doctorService.findDoctorById(id));
        return "edit-doctor";
    }

    @GetMapping(path = "/doctor/delete/{id}")
    public String deleteDoctorById(@PathVariable("id") Long id) {
        this.doctorService.deleteDoctorById(id);
        return "redirect:/";
    }

    @PostMapping(path = "/doctor/add")
    public String addDoctor(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult result) {
        if (result.hasErrors()) {
            return "add-doctor";
        }

        this.doctorService.saveDoctor(doctor);
        return "redirect:/";
    }

    @PostMapping(path = "/doctor/update")
    public String editDoctor(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-doctor";
        }

        this.doctorService.saveDoctor(doctor);
        return "redirect:/";
    }


}