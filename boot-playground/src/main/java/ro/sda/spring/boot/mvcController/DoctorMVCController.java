package ro.sda.spring.boot.mvcController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.sda.spring.boot.entity.Doctor;
import ro.sda.spring.boot.service.DoctorService;

import javax.validation.Valid;

@Controller
public class DoctorMVCController {

    final private DoctorService doctorService;

    @Autowired
    public DoctorMVCController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public String showDoctors(Model model) {
        model.addAttribute("doctors", this.doctorService.findAllDoctors());
        return "index";
    }

    @GetMapping(path = "/doctor/delete/{id}")
    public String deleteDoctorById(@PathVariable("id") Long id, Model model) {
        this.doctorService.deleteDoctorById(id);
        model.addAttribute("doctors", this.doctorService.findAllDoctors());
        return "index";
    }

    @GetMapping(path = "/signup")
    public String signup(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "add-doctor";
    }

    @PostMapping(path = "/doctor/add")
    public String addDoctor(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-doctor";
        }
        this.doctorService.saveDoctor(doctor);
        model.addAttribute("doctors", this.doctorService.findAllDoctors());
        return "index";
    }

    @GetMapping(path = "/doctor/edit/{id}")
    public String showEditDoctorView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("doctor", doctorService.findDoctorById(id));
        return "edit-doctor";
    }

    @PostMapping(path = "/doctor/update/{id}")
    public String editDoctor(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-doctor";
        }
        this.doctorService.saveDoctor(doctor);
        model.addAttribute("doctors", this.doctorService.findAllDoctors());
        return "index";
    }

}
