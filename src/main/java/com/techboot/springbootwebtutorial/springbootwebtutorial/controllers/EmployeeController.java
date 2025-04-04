package com.techboot.springbootwebtutorial.springbootwebtutorial.controllers;

import com.techboot.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.techboot.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.techboot.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret message : abcde";
//    }

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() ->new ResourceNotFoundException("Employee not found with id : " + id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO newEmployeeDTO){
        EmployeeDTO savedEmployeeDTO = employeeService.createNewEmployee(newEmployeeDTO);
        return new ResponseEntity<>(savedEmployeeDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return  ResponseEntity.ok(employeeService.updateEmployeeById(employeeDTO, employeeId));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
//        return ResponseEntity.ok(employeeService.deleteEmployeeById(employeeId));
        boolean deleteStatus = employeeService.deleteEmployeeById(employeeId);
        if(deleteStatus) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
         EmployeeDTO updatedEmployee = employeeService.updatePartialEmployeeById(updates, employeeId);
         if(updatedEmployee == null) return ResponseEntity.notFound().build();
         return ResponseEntity.ok(updatedEmployee);
    }

}
