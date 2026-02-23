package in.learn.employeeregistration.controller;

import in.learn.employeeregistration.dto.EmployeeDto;
import in.learn.employeeregistration.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable(name="employeeId") Long Id) {
        EmployeeDto employeeDto=employeeService.getById(Id);
        return ResponseEntity.ok(employeeDto);}

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<EmployeeDto>> getAll(@RequestParam(required = false, name="employeeId") Long Id,
                                                    @RequestParam(required = false, name="name") String name){
        List<EmployeeDto> employeeDtoList=employeeService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(employeeDtoList);}
    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody @Valid EmployeeDto newEmployee){
        EmployeeDto employeeDto=employeeService.createNewEmployee(newEmployee);
     return  new ResponseEntity<>(employeeDto,HttpStatus.CREATED);}
    @PutMapping(path="/{id}")//For Complete change of body of given id
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody @Valid EmployeeDto updateDtoInfo,@PathVariable(name="id") Long id){
     EmployeeDto employeeDto=employeeService.updateEmployee(updateDtoInfo,id);
     return new ResponseEntity<>(employeeDto,HttpStatus.CREATED);}
    @DeleteMapping("{employeeid}")
    public ResponseEntity<String> deleteById(@PathVariable(name="employeeid") Long id){
        return ResponseEntity.ok(employeeService.deleteById(id));}
    @PatchMapping(path = "/{employeeid}")
    public ResponseEntity<EmployeeDto> updateField(@RequestBody @Valid Map<String,Object> updates,@PathVariable(name="employeeid")Long id){
        return new ResponseEntity<>(employeeService.updateField(updates,id), HttpStatus.ACCEPTED);}
}
