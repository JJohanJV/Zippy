package com.zippy.users.controller;

import com.zippy.users.dto.PersonalInformationDTO;
import com.zippy.users.mappers.PersonalInformationMapper;
import com.zippy.users.service.interfaces.IPersonalInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class PersonalInformationController {
    private IPersonalInformationService personalInformationService;

    private PersonalInformationMapper personalInformationMapper;

    @GetMapping("")
    public ResponseEntity<List<PersonalInformationDTO>> getAllUsers() {
        return ResponseEntity.ok(personalInformationService.getAllPersonalInformation().stream()
                .map(personalInformationMapper::PersonalInformatonToPersonalInformationDTO).toList());
    }

    @PostMapping("/new")
    public ResponseEntity<PersonalInformationDTO> addUser(@RequestBody PersonalInformationDTO personalInformationDTO) {
        return personalInformationService
                .newPersonalInformation(personalInformationMapper.PersonalInformatonDTOToPersonalInformation(personalInformationDTO))
                .map(personalInformationMapper::PersonalInformatonToPersonalInformationDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonalInformation(@PathVariable Long id) {
        personalInformationService.deletePersonalInformation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalInformationDTO> getUserById(@PathVariable Long id) {
        return personalInformationService.getPersonalInformationById(id)
                .map(personalInformationMapper::PersonalInformatonToPersonalInformationDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalInformationDTO> updateUser(@PathVariable Long id, @RequestBody PersonalInformationDTO personalInformationDTO) {
        return personalInformationService.updatePersonalInformation(id, personalInformationMapper.PersonalInformatonDTOToPersonalInformation(personalInformationDTO))
                .map(personalInformationMapper::PersonalInformatonToPersonalInformationDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Autowired
    public void setPersonalInformationService(IPersonalInformationService personalInformationService) {
        this.personalInformationService = personalInformationService;
    }

    @Autowired
    public void SetPersonalInformationMapper(PersonalInformationMapper personalInformationMapper) {
        this.personalInformationMapper = personalInformationMapper;
    }

}
