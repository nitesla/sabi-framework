package com.sabi.framework.service;


import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.models.AuditTrail;
import com.sabi.framework.repositories.AuditTrailRepository;
import com.sabi.framework.utils.AuditTrailFlag;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
@SuppressWarnings("ALL")
public class AuditTrailService {



    private AsyncService asyncService;
    private AuditTrailRepository auditTrailRepository;
    private final ModelMapper mapper;

    public AuditTrailService(AsyncService asyncService,AuditTrailRepository auditTrailRepository, ModelMapper mapper) {
        this.asyncService = asyncService;
        this.auditTrailRepository = auditTrailRepository;
        this.mapper = mapper;
    }




    public void logEvent(String username, String event, AuditTrailFlag flag, String request,
                         int status, String ipAddress) {
        log.info(":::::: PROCESSING AUDIT TRAIL ::");
        asyncService.processAudit(username, event, flag, request, status, ipAddress);
    }



    public AuditTrail getAudit(Long id){
        AuditTrail auditTrail  = auditTrailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested id does not exist!"));
        return auditTrail;
    }



    public Page<AuditTrail> findAll(String username, String event, String flag, LocalDateTime startDate, LocalDateTime endDate, PageRequest pageRequest ){
        Page<AuditTrail> auditTrails = auditTrailRepository.audits(username,event,flag,startDate,endDate,pageRequest);
        if(auditTrails == null){
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, " No record found !");
        }
        return auditTrails;

    }



}
