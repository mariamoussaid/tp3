package com.example.hosp12.web;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model; // Import the correct Model class from Spring Framework
import com.example.hosp12.entities.Patient;
import com.example.hosp12.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping("/user/index")
    public  String index(Model model,
                         @RequestParam(name= "page",defaultValue = "0") int page,
                         @RequestParam(name = "size",defaultValue = "4") int size,
                         @RequestParam(name = "keyword",defaultValue = "")String keyword){
        Page<Patient> pagePatients=patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }

    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ROLE ADMIN')")
    public String deletePatient(@RequestParam(name = "id") Long id, String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/formPatient")
    @PreAuthorize("hasRole('ROLE ADMIN')")
    public String formPatient(Model model ){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }
    @PostMapping("/admin/savePatient")
    @PreAuthorize("hasRole('ROLE ADMIN')")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient);
        return "formPatient";
    }
    @GetMapping("/admin/editPatient")
    @PreAuthorize("hasRole('ROLE ADMIN')")
    public String editPatient(@RequestParam(name = "id") Long id, Model model){
        Patient patient=patientRepository.findById(id).get();
        model.addAttribute("patient",patient);
        return "editPatient";
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }




}