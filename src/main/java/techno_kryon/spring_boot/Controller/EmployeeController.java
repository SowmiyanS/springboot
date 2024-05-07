package techno_kryon.spring_boot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import techno_kryon.spring_boot.Dto.EmployeeCreateDto;
import techno_kryon.spring_boot.Dto.EmployeeDto;
import techno_kryon.spring_boot.Service.EmployeeService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeCreateDto employeeCreateDto) {
        EmployeeDto createdEmployeeDto = employeeService.createEmployee(employeeCreateDto);
        return new ResponseEntity<>(createdEmployeeDto, HttpStatus.CREATED);
    }

    @PostMapping("/upload")
    public ResponseEntity uploadToFileSystem(@RequestParam("file") MultipartFile file) {
        String fileBasePath = "C:\\Users\\Administrator\\Projects\\new\\spring_boot\\src\\main\\resources\\";
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(fileBasePath + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
        String fileDownloadUri = fileBasePath + fileName;
        return ResponseEntity.ok(fileDownloadUri);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Integer employeeId) {
        Optional<EmployeeDto> employeeDto = employeeService.getEmployee(employeeId);
        if(employeeDto.isEmpty()) {
            return new ResponseEntity<>(employeeDto.orElse(new EmployeeDto()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeDto.orElse(new EmployeeDto()), HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public List<EmployeeDto> getEmployees(@RequestParam Long count) {
        List<EmployeeDto> employeeDtos = employeeService.getEmployees(count);
        return employeeDtos;
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
        String fileBasePath = "C:\\Users\\Administrator\\Projects\\new\\spring_boot\\src\\main\\resources\\";
        Resource resource = null;
        File dir = new File(fileBasePath+fileName);
        try{
            if(dir.exists()){
                resource = new UrlResource(dir.toURI());
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(resource == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
        }
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeCreateDto employeeCreateDto, @PathVariable Integer employeeId) {
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeCreateDto, employeeId);
        return new ResponseEntity<>(updatedEmployeeDto, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable Integer employeeId) {
        EmployeeDto deletedEmployeeDto = employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(deletedEmployeeDto, HttpStatus.OK);
    }

}
