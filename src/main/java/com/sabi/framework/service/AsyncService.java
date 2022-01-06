package com.sabi.framework.service;



import com.sabi.framework.models.AuditTrail;
import com.sabi.framework.repositories.AuditTrailRepository;
import com.sabi.framework.utils.AuditTrailFlag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class AsyncService {



 @Autowired
 private AuditTrailRepository auditTrailRepository;


 @Async
 public void sendQueuedSms() {

 }

 @Async
 public void sendQueuedEmails() {

 }




 @Async
 public void processAudit(String username, String event, AuditTrailFlag flag, String request,
                          int status, String ipAddress) {

  AuditTrail logs = new AuditTrail();
  logs.setEvent(event);
  logs.setUsername(username);
  logs.setFlag(flag.name());
  logs.setRequest(request);
  logs.setStatus(status);
  logs.setIpAddress(ipAddress);
  auditTrailRepository.save(logs);
  log.info(":ASYNC  END OF RUNNING log AUDIT:",  request);



 }

}
