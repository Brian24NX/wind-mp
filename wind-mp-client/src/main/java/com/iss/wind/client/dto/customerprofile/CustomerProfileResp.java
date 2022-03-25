package com.iss.wind.client.dto.customerprofile;

import com.iss.wind.client.dto.sechedule.RoutingFinderResp;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerProfileResp {
    private Customer customer;
    private List<Profileright> profilerights;

    @Data
    @Builder
    public static class Customer{
        private String ccgId;
        private String title;
        private String firstName;
        private String lastName;
        private String email;
        private Language language;
        private String phone;
        private String company;
        private Status status;
        private CompanyType companyType;
        private Department department;
        private boolean internalAccountFlag;
        private boolean notificationAccountFlag;
        private String lastLoginDate;
        private String createdDate;
        private String updatedDate;
        private String updatedBy;
        private Location location;
        private RegionalOffice regionalOffice;
    }

    @Data
    @Builder
    public static class Profileright{
        private Partner partner;
        private String shipcomp;
        private List<Right> rights;
    }

    @Data
    @Builder
    public static class Language{
        private String key;
        private String value;
    }

    @Data
    @Builder
    public static class Status{
        private String code;
        private String value;
    }

    @Data
    @Builder
    public static class CompanyType{
        private String key;
        private String value;
    }

    @Data
    @Builder
    public static class Department{
        private String key;
        private String value;
    }

    @Data
    @Builder
    public static class Location{
        private String addressLine1;
        private String addressLine2;
        private Integer zipCode;
        private String city;
        private String country;
        private String state;
    }

    @Data
    @Builder
    public static class RegionalOffice{
        private String id;
        private String value;
    }

    @Data
    @Builder
    public static class Partner{
        private String code;
        private AssociatedPackage associatedPackage;
        private String createdDate;
        private String createdBy;
        private RegionalOffice regionalOffice;
        private String updatedDate;
        private String updatedBy;
    }

    @Data
    @Builder
    public static class AssociatedPackage{
        private String packageId;
        private String name;
        private String description;
        private String createdDate;
        private String createdBy;
    }

    @Data
    @Builder
    public static class Right{
        private String id;
        private String code;
        private String label;
        private Module module;
    }

    @Data
    @Builder
    public static class Module{
        private String moudleId;
        private String name;
    }

}
