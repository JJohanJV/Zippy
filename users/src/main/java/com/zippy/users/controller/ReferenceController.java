package com.zippy.users.controller;

import com.zippy.users.dto.ReferenceDTO;
import com.zippy.users.mappers.ReferenceMapper;
import com.zippy.users.service.interfaces.IReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/references")
public class ReferenceController {
    private IReferenceService referenceService;
    private ReferenceMapper referenceMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ReferenceDTO> getReferenceById(@PathVariable Long id) {
        return referenceService.getReferenceById(id)
                .map(referenceMapper::ReferenceToReferenceDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReferenceDTO> updateReference(@PathVariable Long id, @RequestBody ReferenceDTO referenceDTO) {
        return referenceService.updateReference(id, referenceMapper.ReferenceDTOtoReference(referenceDTO))
                .map(referenceMapper::ReferenceToReferenceDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Autowired
    public void setReferenceService(IReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @Autowired
    public void setReferenceMapper(ReferenceMapper referenceMapper) {
        this.referenceMapper = referenceMapper;
    }
}
