package com.ssms.company.service;

import com.ssms.common.auditlog.LogEntry;
import com.ssms.common.auth.AuthContext;
import com.ssms.common.error.ServiceException;
import com.ssms.company.dto.AdminEntries;
import com.ssms.company.dto.AdminOfList;
import com.ssms.company.dto.CompanyDto;
import com.ssms.company.dto.DirectoryEntryDto;
import com.ssms.company.model.Admin;
import com.ssms.company.repo.AdminRepo;
import com.ssms.company.service.helper.ServiceHelper;
import com.github.structlog4j.ILogger;
import com.github.structlog4j.SLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    static final ILogger logger = SLoggerFactory.getLogger(AdminService.class);

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    CompanyService companyService;

    @Autowired
    DirectoryService directoryService;

    @Autowired
    ServiceHelper serviceHelper;

    public AdminEntries listAdmins(String companyId) {
        // validate and will throw exception if not found
        companyService.getCompany(companyId);

        AdminEntries adminEntries = AdminEntries.builder()
                .companyId(companyId)
                .build();
        List<Admin> admins = adminRepo.findByCompanyId(companyId);

        for(Admin admin : admins) {
            DirectoryEntryDto directoryEntryDto = directoryService.getDirectoryEntry(companyId, admin.getUserId());
            adminEntries.getAdmins().add(directoryEntryDto);
        }

        return adminEntries;
    }

    public DirectoryEntryDto getAdmin(String companyId, String userId) {
        // validate and will throw exceptions if not found
        companyService.getCompany(companyId);

        Admin admin = adminRepo.findByCompanyIdAndUserId(companyId, userId);
        if (admin == null) {
            return null;
        }

        DirectoryEntryDto directoryEntryDto = directoryService.getDirectoryEntry(companyId, userId);
        return directoryEntryDto;
    }

    public void deleteAdmin(String companyId, String userId) {
        // validate and will throw exception if not found
        this.getAdmin(companyId, userId);

        try {
            adminRepo.deleteAdmin(companyId, userId);
        } catch (Exception ex) {
            String errMsg = "could not delete the admin";
            serviceHelper.handleErrorAndThrowException(logger, ex, errMsg);
        }

        LogEntry auditLog = LogEntry.builder()
                .currentUserId(AuthContext.getUserId())
                .authorization(AuthContext.getAuthz())
                .targetType("admin")
                .targetId(userId)
                .companyId(companyId)
                .teamId("")
                .build();

        logger.info("removed admin", auditLog);

        serviceHelper.trackEventAsync("admin_deleted");
    }

    public DirectoryEntryDto createAdmin(String companyId, String userId) {
        Admin existing = adminRepo.findByCompanyIdAndUserId(companyId, userId);
        if (existing != null) {
            throw new ServiceException("user is already an admin");
        }

        DirectoryEntryDto directoryEntryDto = directoryService.getDirectoryEntry(companyId, userId);

        try {
            Admin admin = Admin.builder()
                    .companyId(companyId)
                    .userId(userId)
                    .build();
            adminRepo.save(admin);
        } catch (Exception ex) {
            String errMsg = "could not create the admin";
            serviceHelper.handleErrorAndThrowException(logger, ex, errMsg);
        }

        LogEntry auditLog = LogEntry.builder()
                .currentUserId(AuthContext.getUserId())
                .authorization(AuthContext.getAuthz())
                .targetType("admin")
                .targetId(userId)
                .companyId(companyId)
                .teamId("")
                .build();

        logger.info("added admin", auditLog);

        serviceHelper.trackEventAsync("admin_created");

        return directoryEntryDto;
    }

    public AdminOfList getAdminOf(String userId) {
        AdminOfList adminOfList = AdminOfList.builder()
                .userId(userId)
                .build();

        List<Admin> admins = adminRepo.findByUserId(userId);

        for(Admin admin : admins) {
            CompanyDto companyDto = companyService.getCompany(admin.getCompanyId());
            adminOfList.getCompanies().add(companyDto);
        }

        return adminOfList;
    }
}
