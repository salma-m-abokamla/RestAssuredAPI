package com.apitesting.enums;

import lombok.Getter;

public enum MVA10APIS {

    APP_CONFIG("AppConfig"),
    HANS_SOLO("HansSolo"),
    SOFT_TOKEN("SoftToken"),
    SEGMENT("Segment"),
    SUBS_CONFIG_WITHOUT_SEGMENT("SubsConfigWithoutSegment"),
    DASHBOARD("Dashboard"),
    SUBS_CONFIG_WITH_SEGMENT("SubsConfigWithSegment"),
    VERY_ME("VeryMe"),
    DISCOVER("Discover"),
    PRODUCTS_AND_SERVICES("ProductsAndServices"),
    BILL_HISTORY("BillHistory"),
    PLAN("Plan"),
    USAGES("Usages"),
    EXTRAS("Extras"),
    CURRENT_CHARGES("CurrentCharges"),
    ADDITIONAL_CHARGES("AdditionalCharges"),
    UPGRADES("Upgrades"),
    VOV("VOV"),
    PASSWORD_LOGIN("PasswordLogin"),
    GET_ACCOUNTS("GetAccounts"),
    GET_SUBSCRIPTION_LIST("GetSubscriptionsList"),
    SUBSCRIPTION_SWITCH("SubscriptionSwitch"),
    RESET_PASSWORD("ResetPassword"),
    CHANGE_PIN("ChangePin");


    private MVA10APIS(String name) {
        this.name = name;
    }

    @Getter
    private String name;


}
